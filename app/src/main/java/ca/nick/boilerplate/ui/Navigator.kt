package ca.nick.boilerplate.ui

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import javax.inject.Inject

class Navigator @Inject constructor(
    @IdRes private val containerId: Int,
    private val fragmentManager: FragmentManager
) {

    fun pop() {
        fragmentManager.popBackStack()
    }

    private fun navigate(tag: String, addToBackStack: Boolean = true, producer: () -> Fragment) =
        fragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .add(containerId, producer(), tag)
            .run {
                return@run if (addToBackStack) addToBackStack(tag)
                else this
            }
            .commit()

    fun navigateToMainFragment() = navigate(MainFragment.TAG) { MainFragment.create() }
}