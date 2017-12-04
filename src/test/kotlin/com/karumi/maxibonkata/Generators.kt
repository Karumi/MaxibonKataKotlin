package com.karumi.maxibonkata

import io.kotlintest.properties.Gen

object Generators {
    class DeveloperGenerator : Gen<Developer> {
        override fun generate(): Developer =
                Developer(Gen.string().generate(), Gen.int().generate())
    }

    class HungryDeveloperGenerator : Gen<Developer> {
        override fun generate(): Developer =
                Developer(Gen.string().generate(), Gen.choose(8, Int.MAX_VALUE).generate())
    }

    class NotSoHungryDeveloperGenerator : Gen<Developer> {
        override fun generate(): Developer =
                Developer(Gen.string().generate(), Gen.choose(0, 7).generate())
    }

    class DeveloperForMaxGenerator(val maxMaxibonsToGrab: Int) : Gen<Developer> {
        override fun generate(): Developer =
                Developer(Gen.string().generate(), Gen.choose(0, maxMaxibonsToGrab + 1).generate())
    }

    class ListOf<T>(val min: Int, val max: Int, val gen: Gen<T>) : Gen<List<T>> {
        override fun generate(): List<T> {
            val numberOfElements = Gen.choose(min, max + 1).generate()
            return (1..numberOfElements).map { gen.generate() }
        }
    }

}