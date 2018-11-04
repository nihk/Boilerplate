package ca.nick.boilerplate.di.module

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import ca.nick.boilerplate.R
import ca.nick.boilerplate.ui.MainActivity
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @Provides
    fun fragmentManager(mainActivity: MainActivity): FragmentManager = mainActivity.supportFragmentManager

    @Provides
    @IdRes
    fun containerId() = R.id.container
}