package com.betsson.interviewtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BetViewModel : ViewModel() {

    private val _bets = MutableLiveData<List<Bet>>()
    val bets: LiveData<List<Bet>> = _bets

    init {
        _bets.value = getInitialBets().sortedBy { it.sellIn }
    }

    private fun getInitialBets(): List<Bet> = listOf(
        Bet("Winning team", 10, 20, "https://i.imgur.com/mx66SBD.jpeg"),
        Bet("Total score", 2, 0, "https://i.imgur.com/VnPRqcv.jpeg"),
        Bet("Player performance", 5, 7, "https://i.imgur.com/Urpc00H.jpeg"),
        Bet("First goal scorer", 0, 80, "https://i.imgur.com/Wy94Tt7.jpeg"),
        Bet("Number of fouls", 5, 49, "https://i.imgur.com/NMLpcKj.jpeg"),
        Bet("Corner kicks", 3, 6, "https://i.imgur.com/TiJ8y5l.jpeg")
    )

    fun updateOdds() {
        val updated = _bets.value?.map { bet ->
            val newBet = bet.copy()
            if (bet.type != "Total score" && bet.type != "Number of fouls") {
                if (newBet.odds > 0 && bet.type != "First goal scorer") newBet.odds--
            } else {
                if (newBet.odds < 50) newBet.odds++
                if (bet.type == "Number of fouls") {
                    if (newBet.sellIn < 11 && newBet.odds < 50) newBet.odds++
                    if (newBet.sellIn < 6 && newBet.odds < 50) newBet.odds++
                }
            }
            if (bet.type != "First goal scorer") newBet.sellIn--
            if (newBet.sellIn < 0) {
                when (bet.type) {
                    "Total score" -> if (newBet.odds < 50) newBet.odds++
                    "Number of fouls" -> newBet.odds = 0
                    else -> if (newBet.odds > 0 && bet.type != "First goal scorer") newBet.odds--
                }
            }
            newBet
        }?.sortedBy { it.sellIn }

        _bets.value = updated
    }
}
