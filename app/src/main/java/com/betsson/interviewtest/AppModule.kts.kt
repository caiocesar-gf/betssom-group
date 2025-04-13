package com.betsson.interviewtest

import BetRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        val json = JsonUtils.loadJsonFromAssets(androidContext(), "bets.json")
        BetRepositoryImpl(json) as BetRepository
    }

    viewModel { BetViewModel(get()) }
}