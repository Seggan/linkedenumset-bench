package io.github.seggan.bench

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole

@State(Scope.Benchmark)
open class Bench {

    @Benchmark
    fun bench(blackhole: Blackhole) {
        val a = 1
        val b = 2
        val c = a + b
        blackhole.consume(c)
    }
}