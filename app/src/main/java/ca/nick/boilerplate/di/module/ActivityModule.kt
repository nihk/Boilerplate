package ca.nick.boilerplate.di.module

import ca.nick.boilerplate.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun mainActivityInjector(): MainActivity
}