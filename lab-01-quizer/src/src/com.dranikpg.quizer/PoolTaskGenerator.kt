package com.dranikpg.quizer

import java.lang.RuntimeException

class PoolTaskGenerator private constructor(private val duplicates: Boolean,
                                            private val gs: MutableList<Task> = mutableListOf())
    : TaskGenerator {
    constructor(duplicates: Boolean, cgs: Collection<Task>) : this(duplicates, cgs.toMutableList())
    constructor(duplicates: Boolean, vararg vgs: Task) : this(duplicates, vgs.toMutableList())
    override fun generate(): Task = if (gs.size == 0) throw RuntimeException("No more tasks left")
        else (0 until gs.size).random().let { Pair(it, gs[it]) }
            .also { if (!duplicates) gs.removeAt(it.first) }
            .let { it.second }

}