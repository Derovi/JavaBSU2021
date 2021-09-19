package com.dranikpg.quizer

class GroupTaskGenerator constructor(private val gs: Collection<TaskGenerator>) : TaskGenerator {
    constructor(vararg vgs: TaskGenerator) : this(vgs.toList())
    // wtf nobody throws exceptions in generate
    override fun generate(): Task = gs.random().generate()
}