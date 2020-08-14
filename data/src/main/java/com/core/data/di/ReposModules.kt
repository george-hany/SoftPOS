package com.core.data.di

import org.koin.dsl.module
import com.core.data.network.ApiFactory
import com.core.data.repos.MainRepo
import com.core.data.repos.SplashRepo
import com.core.network.MockedNetwork
import com.core.network.NetworkFactory
import com.core.prefrence.secured_shared_preferences.SecuredSharedPreference

val reposModules = module {

    single { ApiFactory(get()) }
    fun provideMainRepo(
        apiFactory: ApiFactory,
        sharedPreference: SecuredSharedPreference,
        mockedNetwork: MockedNetwork
    ): MainRepo {
        return MainRepo(
            apiFactory,
            sharedPreference,
            mockedNetwork
        )
    }
    factory { provideMainRepo(get(), get(), get()) }

    fun provideSplashRepo(
        apiFactory: ApiFactory,
        sharedPreference: SecuredSharedPreference,
        networkFactory: NetworkFactory
    ): SplashRepo {
        return SplashRepo(apiFactory, sharedPreference, networkFactory)
    }
    factory { provideSplashRepo(get(), get(), get()) }
}
