package ca.nick.boilerplate.di.module

import dagger.Module

@Module(includes = [ViewModelModule::class, RemoteDataModule::class, LocalDataModule::class])
class AppModule