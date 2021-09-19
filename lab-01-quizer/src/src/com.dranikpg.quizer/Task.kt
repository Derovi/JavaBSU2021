package com.dranikpg.quizer;

interface Task {
    val text: String
    fun validate(answer: String): Result
}