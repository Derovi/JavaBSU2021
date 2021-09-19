package com.dranikpg.quizer

val quizMap =  hashMapOf("default"
        to Quiz (ExpressionTaskGenerator(-10, 10, Operation.values().toHashSet()), 3))

fun main() {
    val qz = quizMap["default"]!!
    while (!qz.isFinished()) {
        val t = qz.nextTask()
        print("The question is ${t.text}")
        val ans = readLine()
        qz.provideAnswer(ans!!)
    }
    println("your mark ${qz.getMark()}")
}