package com.dranikpg.quizer

class Quiz(private val gen: TaskGenerator, private val taskCount: Int) {
    private var current: Task? = null
    @get:JvmName("getCorrectAnswersNumber") // LetsNotMakeSoAwfulGetterNamesSoIDon'tHaveToUseThisAnnotation
    var correct = 0
        private set
    @get:JvmName("getWrongAnswersNumber") // LetsNotMakeSoAwfulGetterNamesSoIDon'tHaveToUseThisAnnotation
    var wrong = 0
        private set
    @get:JvmName("getIncorrectInputNumber") // LetsNotMakeSoAwfulGetterNamesSoIDon'tHaveToUseThisAnnotation
    var incorrectInputs = 0
        private set
    fun nextTask() = current ?: gen.generate().also {current = it}
    fun provideAnswer(answer: String) = current!!.validate(answer)
            .also { current = if (it == Result.INCORRECT_INPUT) current.also {incorrectInputs ++} else null}
            .also {if (it == Result.OK) correct++ else if (it == Result.WRONG) wrong ++}
    fun isFinished() = (wrong + correct) == taskCount
    fun getMark() = correct.toDouble() / taskCount * 10
}