package ca.nick.boilerplate.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ca.nick.boilerplate.data.local.DummyDao
import ca.nick.boilerplate.data.local.LocalDataCachingStrategy
import ca.nick.boilerplate.data.remote.DummyService
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val service: DummyService,
    private val dao: DummyDao,
    private val localDataCachingStrategy: LocalDataCachingStrategy
) {

    private val _items = MediatorLiveData<List<Dummy>>()
    val items: LiveData<List<Dummy>> get() = _items

    init {
        _items.addSource(dao.queryAll()) {
            _items.value = it
        }
    }

    fun fetchThenInsertLocally(): Completable =
        fetch().flatMapCompletable {
            Completable.fromAction {
                dao.deleteAll()
                dao.insertEntities(it)
            }
        }

    fun fetch() = service.fetch()

    fun isPersistedDataStale() = localDataCachingStrategy.isPersistedDataStale()

    fun setLastTimeDataFetchedSuccessfully() = localDataCachingStrategy.setLastTimeDataFetchedSuccessfully()
}