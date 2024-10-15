package com.android.multisys.exam.di

import com.android.multisys.exam.repository.RedditRepository
import com.android.multisys.exam.repository.RedditRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun providesRedditRepository(repository: RedditRepositoryImpl): RedditRepository
}