package com.karumi.maxibonkata

import io.kotlintest.properties.Gen

object Generators {
    open class DeveloperGenerator(
        private val nameGenerator: Gen<String>,
        private val numbersOfMaxibonsGenerator: Gen<Int>
    ) : Gen<Developer> {
        override fun constants(): Iterable<Developer> = emptyList()

        override fun random(): Sequence<Developer> =
            nameGenerator.random()
                .zip(numbersOfMaxibonsGenerator.random())
                .map { Developer(it.first, it.second) }
    }

    class AnyDeveloperGenerator : DeveloperGenerator(
        nameGenerator = Gen.string(),
        numbersOfMaxibonsGenerator = Gen.int()
    )

    class HungryDeveloperGenerator : DeveloperGenerator(
        nameGenerator = Gen.string(),
        numbersOfMaxibonsGenerator = Gen.choose(8, Int.MAX_VALUE)
    )

    class NotSoHungryDeveloperGenerator : DeveloperGenerator(
        nameGenerator = Gen.string(),
        numbersOfMaxibonsGenerator = Gen.choose(0, 7)
    )

    class DeveloperForMaxGenerator(maxMaxibonsToGrab: Int) : DeveloperGenerator(
        nameGenerator = Gen.string(),
        numbersOfMaxibonsGenerator = Gen.choose(0, maxMaxibonsToGrab + 1)
    )

    class ListOf<T>(
        private val min: Int,
        private val max: Int,
        private val gen: Gen<T>
    ) : Gen<List<T>> {
        override fun constants(): Iterable<List<T>> = emptyList()

        override fun random(): Sequence<List<T>> = generateSequence {
            val size = Gen.choose(min, max + 1).random().first()
            gen.random().take(size).toList()
        }
    }
}