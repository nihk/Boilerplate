package ca.nick.boilerplate.di.module

import ca.nick.boilerplate.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment
}