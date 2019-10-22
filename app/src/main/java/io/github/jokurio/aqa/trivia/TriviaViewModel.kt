package io.github.jokurio.aqa.trivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonParseException
import io.github.jokurio.aqa.trivia.api.BASE_URL_OPEN_TDB
import io.github.jokurio.aqa.trivia.api.OpenTriviaApi
import io.github.jokurio.aqa.trivia.api.OpenTriviaResults
import io.github.jokurio.aqa.trivia.api.SAMPLE_RESPONSE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.*

/**
 * All game decisions, retrieving questions, creating a new game and a new player will be done in this viewModel
 *
 * The activity displays whatever screen based on the state of the trivia.
 *
 * Also, all user inputs are forwarded here for decision making
 */
class TriviaViewModel : ViewModel() {

    private val openTrivia: OpenTriviaApi by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL_OPEN_TDB)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenTriviaApi::class.java)
    }

    private var _currentQuestion: Question? = null
    private val _triviaState: MutableLiveData<TriviaState> = MutableLiveData(TriviaState.Default)

    private var _currentPlayer: Player? = null
    private var _nextQuestionIndex = 0

    /**
     * Observe this to receive the current state of the game
     */
    val triviaState: LiveData<TriviaState> = _triviaState

    fun startNewGame(playerName: String) {

        fetchNewQuestions({ questions ->
            val newGame = Game(
                    id = UUID.randomUUID().toString(),
                    completed = false,
                    choices = mutableMapOf(),
                    questions = questions
            )
            _currentPlayer = Player(
                    name = playerName,
                    game = newGame,
                    score = 0
            )
            val nextQuestion = selectNextQuestion()
            if (nextQuestion != null) {
                dispatchNextQuestion(nextQuestion)
            }
        }, { error ->
            Timber.w(error, "Failed to get questions!")
        })

    }

    /**
     * Called with the answer selected by the player
     * @param answer The choice made by the player
     */
    fun selectedAnswer(answer: String) {
        _currentQuestion?.let { question ->
            _currentPlayer?.let { player ->
                val hasMore = selectNextQuestion() != null
                val nextState = if (question.correctAnswer == answer) {
                    player.score.plus(question.difficulty.points)
                    TriviaState.GameOn.Correct(question, answer, hasMore)
                } else {
                    TriviaState.GameOn.Incorrect(question, answer, hasMore)
                }
                player.game.choices[question.id] = answer
                player.game.completed = !hasMore
                _triviaState.value = nextState
            }
        }
    }

    private fun selectNextQuestion(): Question? {
        return _currentPlayer?.let { player ->
            if (_nextQuestionIndex < player.game.questions.size) player.game.questions[_nextQuestionIndex] else {
                Timber.i("No more questions to play")
                null
            }
        }
    }

    private fun dispatchNextQuestion(question: Question) {
        _currentQuestion = question
        val questionCount = _currentPlayer?.game?.questions?.count() ?: 0
        _triviaState.value = TriviaState.Play(question, _nextQuestionIndex + 1, questionCount) // 1 base index
        _nextQuestionIndex++
    }

    fun next() {
        when (val state = _triviaState.value) {
            is TriviaState.GameOn -> {
                val nextQuestion = selectNextQuestion()
                if (state.hasMore && nextQuestion != null) {
                    dispatchNextQuestion(nextQuestion)
                } else {
                    _currentPlayer?.let { player ->
                        _triviaState.value = TriviaState.Summary(player)
                    }
                }
            }
        }
    }

    /**
     * Uses retrofit to make a request to the api for new questions.
     * @param result callback for receiving the questions result
     * @param error callback for error results
     * TODO: add parameters that will allow the user choose which category and how many questions he likes
     */
    private fun fetchNewQuestions(result: (List<Question>) -> Unit, error: (Throwable) -> Unit) {
        try {
            openTrivia.fetchQuestions().enqueue(object : Callback<OpenTriviaResults> {
                override fun onFailure(call: Call<OpenTriviaResults>, t: Throwable) {
                    error(t)
                }

                override fun onResponse(call: Call<OpenTriviaResults>, response: Response<OpenTriviaResults>) {
                    val apiResult = response.body()
                    if (response.isSuccessful && apiResult != null && apiResult.responseCode == 0) {
                        val questions = apiResult.results.map { it.toQuestion() }
                        result(questions)
                    } else {
                        error(Throwable("Request not successful: ${response.code()} - ${response.message()}"))
                    }
                }

            })
        } catch (ex: Exception) {
            error(ex)
        }
    }
}