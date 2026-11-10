
package com.example.iscip

import com.example.iscip.data.FruitDatabase
import com.example.iscip.data.FruitRepository
import com.example.iscip.factivity.AddFruitViewModel
import com.example.iscip.factivity.MainActivityViewModel
import org.koin.android.ext.koin.androidContext

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val koinModule = module {

    single<FruitRepository> {
        FruitDatabase(androidContext())
    }

    viewModel {
        MainActivityViewModel(get())
    }

    viewModel {
        AddFruitViewModel(get())
    }
}