package com.karumi.maxibonkata

import io.kotlintest.properties.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.ShouldSpec
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table

class DevelopersTest : ShouldSpec() {
    init {
        "Developers" {
            should("always grab a positive number of maxibons") {
                forAll { numberOfMaxibons: Int ->
                    val dev = Developer(numberOfMaxibons = numberOfMaxibons)

                    dev.maxibonsToGrab >= 0
                }
            }

            should("assign the name of the developer in construction") {
                forAll { name: String ->
                    val dev = Developer(name)

                    dev.name == name
                }
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
                io.kotlintest.tables.forAll(developers) { developer: Developer, expectedMaxibonsToGrab: Int ->
                    developer.maxibonsToGrab shouldBe expectedMaxibonsToGrab
                }
            }
        }
    }
}