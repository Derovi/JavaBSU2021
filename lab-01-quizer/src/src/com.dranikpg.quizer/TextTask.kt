package com.dranikpg.quizer

class TextTask(override val text: String, private val expected: String) : Task {
    override fun validate(answer: String): Result = if (answer.equals(expected, ignoreCase = true)) Result.OK
        else Result.WRONG
}