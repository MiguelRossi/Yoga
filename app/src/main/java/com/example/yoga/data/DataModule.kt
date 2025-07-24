package com.example.yoga.data

import com.example.yoga.domain.repository.YogaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsYogaRepository(
        yogaRepository: YogaRepositoryImpl
    ): YogaRepository
}
