package com.karumi.maxibonkata

import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.properties.headers
import io.kotlintest.properties.row
import io.kotlintest.properties.table
import io.kotlintest.specs.ShouldSpec

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

            val developers = table(
                    headers("developer", "expected maxibons to grab"),
                    row(Developer.pedro, 3),
                    row(Developer.fran, 1),
                    row(Developer.davide, 0),
                    row(Developer.sergio, 2),
                    row(Developer.jorge, 1)
            )

            should("assign the number of maxibons specified to every developer") {
                forAll(developers) { developer, expectedMaxibonsToGrab ->
                    developer.maxibonsToGrab shouldBe expectedMaxibonsToGrab
                }
            }
        }

    }
}