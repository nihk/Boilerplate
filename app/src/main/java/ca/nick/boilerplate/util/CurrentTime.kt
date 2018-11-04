package ca.nick.boilerplate.util

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentTime @Inject constructor() {

    fun timeInMillis() = System.currentTimeMillis()
}