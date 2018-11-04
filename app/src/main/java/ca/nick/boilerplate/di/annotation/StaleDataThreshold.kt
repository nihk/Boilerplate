package ca.nick.boilerplate.di.annotation

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class StaleDataThreshold