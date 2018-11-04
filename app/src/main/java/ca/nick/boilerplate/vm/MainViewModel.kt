package ca.nick.boilerplate.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.nick.boilerplate.data.Dummy
import ca.nick.boilerplate.data.Repository
import ca.nick.boilerplate.utils.Event
import ca.nick.boilerplate.utils.applySchedulers
import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _progressBarState = MutableLiveData<Boolean>()
    private val _errorMessage = MutableLiveData<Event<String?>>()
    private val _items = MediatorLiveData<List<Dummy>>()

    val progressBarState: LiveData<Boolean> get() = _progressBarState
    val errorMessage: LiveData<Event<String?>> get() = _errorMessage
    val items: LiveData<List<Dummy>> get() = _items

    init {
        _items.addSource(repository.items) {
            _items.value = it
        }
    }

    fun fetchThenPersistLocally() =
        repository.fetchThenPersistLocally()
            .applySchedulers()
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                    _progressBarState.value = true
                }

                override fun onComplete() {
                    _progressBarState.value = false
                    setLastTimeDataFetchedSuccessfully()
                }

                override fun onError(e: Throwable) {
                    _progressBarState.value = false
                    _errorMessage.value = Event(e.message)
                }
            })

    fun isPersistedDataStale() = repository.isPersistedDataStale()

    fun setLastTimeDataFetchedSuccessfully() = repository.setLastTimeDataFetchedSuccessfully()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}