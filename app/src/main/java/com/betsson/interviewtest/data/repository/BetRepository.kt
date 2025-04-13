package com.betsson.interviewtest.data.repository

import com.betsson.interviewtest.data.model.Bet

interface BetRepository {
    fun fetchBets(): List<Bet>
}