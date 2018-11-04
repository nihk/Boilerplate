package ca.nick.boilerplate.utils

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentTime @Inject constructor() {

    fun timeInMillis() = System.currentTimeMillis()
}