package com.betsson.interviewtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BetViewModel(private val repository: BetRepository) : ViewModel() {


    private val _bets = MutableLiveData<List<Bet>>()
    val bets: LiveData<List<Bet>> get() = _bets

    fun loadBets() {
        val items = repository.fetchBets().toMutableList()
        calculateOdds(items)
        _bets.postValue(items)
    }

    fun updateOdds() {
        val updated = _bets.value?.toMutableList() ?: return
        calculateOdds(updated)
        _bets.value = updated
    }


    private fun calculateOdds(bets: MutableList<Bet>) {
        for (bet in bets) {
            if (bet.type != "Total score" && bet.type != "Number of fouls") {
                if (bet.odds > 0 && bet.type != "First goal scorer") {
                    bet.odds -= 1
                }
            } else {
                if (bet.odds < 50) {
                    bet.odds += 1
                    if (bet.type == "Number of fouls") {
                        if (bet.sellIn < 11 && bet.odds < 50) bet.odds += 1
                        if (bet.sellIn < 6 && bet.odds < 50) bet.odds += 1
                    }
                }
            }

            if (bet.type != "First goal scorer") {
                bet.sellIn -= 1
            }

            if (bet.sellIn < 0) {
                if (bet.type == "Total score" && bet.odds < 50) {
                    bet.odds += 1
                } else if (bet.type == "Number of fouls") {
                    bet.odds = 0
                } else if (bet.type != "First goal scorer" && bet.odds > 0) {
                    bet.odds -= 1
                }
            }
        }
    }
}



