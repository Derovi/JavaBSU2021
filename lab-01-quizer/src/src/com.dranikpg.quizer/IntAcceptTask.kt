package com.dranikpg.quizer

data class Expr(val nums: List<Int>, val op: Operation, val res: Int = nums.reduce(op.f) );

fun List<Int>.generateExpr(ops: Set<Operation>): Expr = generateSequence { ops.random() }
            .filter { !(it == Operation.DIVIDE && this[1] == 0 && this[0] % this[1] == 0) }
            .first().let { Expr(this, it) }

class IntAcceptTask(override val text: String, private val expected: Int) : Task {
    override fun validate(answer: String): Result =
            answer.toIntOrNull()?.let {if (it == expected) Result.OK else Result.WRONG} ?: Result.INCORRECT_INPUT
}