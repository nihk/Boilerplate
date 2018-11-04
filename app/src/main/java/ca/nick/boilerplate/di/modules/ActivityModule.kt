package ca.nick.boilerplate.di.modules

import ca.nick.boilerplate.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity
}