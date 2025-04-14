package com.betsson.interviewtest.di

import com.betsson.interviewtest.ui.viewmodel.BetViewModel
import com.betsson.interviewtest.data.repository.BetRepository
import com.betsson.interviewtest.data.repository.BetRepositoryImpl
import com.betsson.interviewtest.data.util.JsonUtils
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