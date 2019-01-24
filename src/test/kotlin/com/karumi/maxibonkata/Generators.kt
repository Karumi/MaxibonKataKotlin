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
}