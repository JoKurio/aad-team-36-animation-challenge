package io.github.jokurio.aqa.trivia.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTriviaApi {

    @GET("api.php")
    fun fetchQuestions(@Query("amount") count: Int = 10,
                       @Query("category") category: Int = CATEGORY_COMPUTERS): Call<OpenTriviaResults>
}