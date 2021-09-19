package com.dranikpg.quizer

class ExpressionTaskGenerator(private val minNumber: Int, private val maxNumber: Int, private val ops: Set<Operation>)
    : TaskGenerator {
    override fun generate(): Task = List(2) { (minNumber..maxNumber).random() }.generateExpr(ops)
            .let {IntAcceptTask(it.nums.joinToString(it.op.c.toString()) + "=", it.res)}
}