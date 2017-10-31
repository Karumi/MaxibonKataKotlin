package com.karumi.maxibonkata.developers

import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.specs.ShouldSpec
import com.karumi.maxibonkata.Developer

class DevelopersTest : ShouldSpec() {
    init {

        "Developers" {
            should("always grab a positive number of maxibons") {
                forAll({ numberOfMaxibons: Int ->
                    val dev = Developer(numberOfMaxibons = numberOfMaxibons)

                    dev.maxibonsToGrab >= 0
                })
            }

            should("assign the name of the developer in construction") {
                forAll({ name: String ->
                    val dev = Developer(name)

                    dev.name == name
                })
            }

            should("assign the number of maxibons specified to every developer") {
                Developer.pedro.maxibonsToGrab shouldBe 3
                Developer.fran.maxibonsToGrab shouldBe 1
                Developer.davide.maxibonsToGrab shouldBe 0
                Developer.sergio.maxibonsToGrab shouldBe 2
                Developer.jorge.maxibonsToGrab shouldBe 1
            }
        }

    }
}