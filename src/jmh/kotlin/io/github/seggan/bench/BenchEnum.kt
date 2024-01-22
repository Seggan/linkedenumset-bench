package io.github.seggan.bench

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
open class BenchEnum {

    private enum class TestEnum {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N
    }

    private lateinit var set: LinkedEnumSet<TestEnum>
    private lateinit var mutableSet: MutableSet<TestEnum>
    private lateinit var enumSet: EnumSet<TestEnum>

    private lateinit var random: TestEnum

    @Setup(Level.Iteration)
    fun setup() {
        val randomList = TestEnum.entries.shuffled()
        set = LinkedEnumSet.copyOf(randomList.take(randomList.size / 2))
        mutableSet = set.toMutableSet()
        enumSet = EnumSet.copyOf(set)
        random = TestEnum.entries.random()
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun addEnum(blackhole: Blackhole) {
        blackhole.consume(set.add(random))
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun addMutableSet(blackhole: Blackhole) {
        blackhole.consume(mutableSet.add(random))
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun addEnumSet(blackhole: Blackhole) {
        blackhole.consume(enumSet.add(random))
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun removeEnum(blackhole: Blackhole) {
        blackhole.consume(set.remove(random))
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun removeMutableSet(blackhole: Blackhole) {
        blackhole.consume(mutableSet.remove(random))
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun removeEnumSet(blackhole: Blackhole) {
        blackhole.consume(enumSet.remove(random))
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun containsEnum(blackhole: Blackhole) {
        blackhole.consume(set.contains(random))
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun containsMutableSet(blackhole: Blackhole) {
        blackhole.consume(mutableSet.contains(random))
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun containsEnumSet(blackhole: Blackhole) {
        blackhole.consume(enumSet.contains(random))
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun iteratorEnum(blackhole: Blackhole) {
        for (e in set) {
            blackhole.consume(e)
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun iteratorMutableSet(blackhole: Blackhole) {
        for (e in mutableSet) {
            blackhole.consume(e)
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    fun iteratorEnumSet(blackhole: Blackhole) {
        for (e in enumSet) {
            blackhole.consume(e)
        }
    }
}