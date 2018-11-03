package ca.nick.boilerplate.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.nick.boilerplate.vm.MainViewModel
import ca.nick.boilerplate.vm.VmFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [NetworkingModule::class, LocalDataModule::class])
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: VmFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel) : ViewModel
}