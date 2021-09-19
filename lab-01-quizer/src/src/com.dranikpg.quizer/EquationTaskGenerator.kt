package com.dranikpg.quizer

class EquationTaskGenerator (private val minNumber: Int, private val maxNumber: Int, private val ops: Set<Operation>)
    : TaskGenerator {
    override fun generate(): Task = Pair(List(2) { (minNumber..maxNumber).random() }.generateExpr(ops), (0..1).random())
            .let { (expr, idx) -> IntAcceptTask(
                    expr.nums.map(Int::toString).toMutableList().also { it[idx] = "x" }
                            .joinToString(expr.op.c.toString()) + "=" + expr.res, expr.nums[idx]) }
}