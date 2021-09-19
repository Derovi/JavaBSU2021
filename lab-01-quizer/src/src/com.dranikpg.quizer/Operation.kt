package com.dranikpg.quizer

import javax.script.ScriptEngineManager

enum class Operation(val c: Char, val f: (Int,Int) -> Int) {
    PLUS('+', { a, b -> a + b }),
    MINUS('-', fun (a, b) = a - b),
    MULTIPLY('*', Int::plus),
    DIVIDE('/', fun(a,b) : Int { // I didn't come up with anything more dubious than dividing integers in javascript
        // lol lets also spin up a full scale javascript engine each time
        // takes some time
        val engine = ScriptEngineManager().getEngineByName("nashorn")
        return try { engine.eval("(${a})/(${b}) >> 0").toString().toIntOrNull() }
            catch (e: java.lang.Exception) { e.printStackTrace().let {null} } !!
    });
}