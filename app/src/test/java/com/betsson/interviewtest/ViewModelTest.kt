package com.betsson.interviewtest

import BetRepositoryImpl
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.junit.Assert.*

class BetViewModelInjectionTest : KoinTest {

    private val viewModel: BetViewModel by inject()

    @Before
    fun setup() {
        val json = """
            [
              {
                "type": "Winning team",
                "sellIn": 10,
                "odds": 20,
                "image": "https://i.imgur.com/mx66SBD.jpeg"
              },
              {
                "type": "Total score",
                "sellIn": 2,
                "odds": 0,
                "image": "https://i.imgur.com/VnPRqcv.jpeg"
              },
              {
                "type": "Player performance",
                "sellIn": 5,
                "odds": 7,
                "image": "https://i.imgur.com/Urpc00H.jpeg"
              },
              {
                "type": "First goal scorer",
                "sellIn": 0,
                "odds": 80,
                "image": "https://i.imgur.com/Wy94Tt7.jpeg"
              },
              {
                "type": "Number of fouls",
                "sellIn": 5,
                "odds": 49,
                "image": "https://i.imgur.com/NMLpcKj.jpeg"
              },
              {
                "type": "Corner kicks",
                "sellIn": 3,
                "odds": 6,
                "image": "https://i.imgur.com/TiJ8y5l.jpeg"
              }
            ]
        """.trimIndent()

        val testModule = module {
            single<BetRepository> { BetRepositoryImpl(json) }
            single { BetViewModel(get()) }
        }

        startKoin {
            modules(testModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should inject BetViewModel with dependencies`() {
        assertNotNull(viewModel)
    }

    @Test
    fun `should load bets from mocked JSON`() {
        val repository: BetRepository by inject()
        val bets = repository.fetchBets()
        assertEquals(6, bets.size)
        assertEquals("Winning team", bets.first().type)
    }

}
