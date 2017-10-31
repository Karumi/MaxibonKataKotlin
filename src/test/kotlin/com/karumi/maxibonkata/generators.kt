package com.karumi.maxibonkata

import io.kotlintest.properties.Gen

object generators {
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
}