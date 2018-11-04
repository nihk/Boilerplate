package ca.nick.boilerplate.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.nick.boilerplate.di.annotations.ViewModelKey
import ca.nick.boilerplate.vm.MainViewModel
import ca.nick.boilerplate.vm.VmFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [RemoteDataModule::class, LocalDataModule::class])
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: VmFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel) : ViewModel
}