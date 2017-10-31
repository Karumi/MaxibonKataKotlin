package com.karumi.maxibonkata

import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.specs.ShouldSpec
import com.karumi.maxibonkata.generators.DeveloperGenerator
import com.karumi.maxibonkata.generators.HungryDeveloperGenerator
import com.karumi.maxibonkata.generators.NotSoHungryDeveloperGenerator
import io.kotlintest.properties.Gen

class KarumiHQsTest : ShouldSpec() {
    init {
        "KarumiHQs" {
            should("start the day with 10 maxibons") {
                val office = KarumiHQs()

                office.maxibonsLeft shouldBe 10
            }

            should("always has more than two maxibons in the fridge") {
                forAll(DeveloperGenerator(), { developer ->
                    val office = KarumiHQs()

                    office.openFridge(developer)

                    office.maxibonsLeft > 2
                })
            }

            should("buy 10 more maxibons if there are less than 3 in the fridge") {
                forAll(HungryDeveloperGenerator(), { developer ->
                    val office = KarumiHQs()
                    val initialMaxibons = office.maxibonsLeft

                    office.openFridge(developer)

                    val expectedMaxibons = calculateMaxibonsLeft(initialMaxibons, developer)
                    office.maxibonsLeft == expectedMaxibons
                })
            }

            should("never request more maxibons to the team using the chat if there are more than 2 in the fridge") {
                forAll(NotSoHungryDeveloperGenerator(), { developer ->
                    val chat = MockChat()
                    val office = KarumiHQs(chat)
                    office.openFridge(developer)

                    chat.messageSent == null
                })
            }

            should("always has more than two maxibons in the fridge even if some karumies grab maxibons in group") {
                forAll(Gen.list(DeveloperGenerator()), { developers ->
                    val office = KarumiHQs()

                    office.openFridge(developers)

                    office.maxibonsLeft > 2
                })
            }

            should("buy 10 more maxibons if there are less than 2 in the fridge when grabbing maxibons in group") {
                forAll(Gen.list(DeveloperGenerator()), { developers ->
                    val office = KarumiHQs()
                    val initialMaxibons = office.maxibonsLeft

                    office.openFridge(developers)

                    val expectedMaxibons = calculateMaxibonsLeft(initialMaxibons, developers)
                    office.maxibonsLeft == expectedMaxibons
                })
            }
        }
    }

    private fun calculateMaxibonsLeft(initialMaxibons: Int, dev: Developer): Int =
            calculateMaxibonsLeft(initialMaxibons, listOf(dev))

    private fun calculateMaxibonsLeft(initialMaxibons: Int, developers: List<Developer>): Int {
        var maxibonsLeft = initialMaxibons
        developers.forEach { developer ->
            maxibonsLeft = Math.max(0, maxibonsLeft - developer.maxibonsToGrab)
            if (maxibonsLeft <= 2) {
                maxibonsLeft += 10
            }
        }
        return maxibonsLeft
    }

    private class MockChat : Chat {
        var messageSent: String? = null

        override fun sendMessage(message: String) {
            messageSent = message
        }

    }
}