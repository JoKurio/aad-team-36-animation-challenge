package io.github.jokurio.aqa

import android.view.Gravity
import androidx.transition.*

private val fadeIn = Fade(Fade.IN).apply {
    duration = 100
    startDelay = 10
}

private val arcMotion = ArcMotion()

private val slideIn1 = Slide(Gravity.END).apply {
    duration = 100
    startDelay = 10
    setPathMotion(arcMotion)
}

private val slideInAndFade1 = TransitionSet().apply {
    ordering = TransitionSet.ORDERING_TOGETHER
    addTransition(fadeIn)
    addTransition(slideIn1)
    addTarget("0")
}
private val slideIn2 = Slide(Gravity.END).apply {
    duration = 80
    startDelay = 10
    setPathMotion(arcMotion)
}
private val slideInAndFade2 = TransitionSet().apply {
    ordering = TransitionSet.ORDERING_TOGETHER
    addTransition(fadeIn)
    addTransition(slideIn2)
    addTarget("1")
}
private val slideIn3 = Slide(Gravity.END).apply {
    duration = 70
    startDelay = 10
    setPathMotion(arcMotion)
}
private val slideInAndFade3 = TransitionSet().apply {
    ordering = TransitionSet.ORDERING_TOGETHER
    addTransition(fadeIn)
    addTransition(slideIn3)
    addTarget("2")
}
private val slideIn4 = Slide(Gravity.END).apply {
    duration = 50
    startDelay = 10
    setPathMotion(arcMotion)
}
private val slideInAndFade4 = TransitionSet().apply {
    ordering = TransitionSet.ORDERING_TOGETHER
    addTransition(fadeIn)
    addTransition(slideIn4)
    addTarget("3")
}

val explodeTransition = Explode().apply {
    duration = 300
    mode = Explode.MODE_IN or Explode.MODE_OUT
}

val answerOptionsTransition: TransitionSet = TransitionSet().apply {
    ordering = TransitionSet.ORDERING_SEQUENTIAL
    addTransition(slideInAndFade1)
    addTransition(slideInAndFade2)
    addTransition(slideInAndFade3)
    addTransition(slideInAndFade4)
}

