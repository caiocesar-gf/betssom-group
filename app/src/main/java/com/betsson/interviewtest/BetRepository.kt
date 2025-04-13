package com.betsson.interviewtest

interface BetRepository {
    fun fetchBets(): List<Bet>
}