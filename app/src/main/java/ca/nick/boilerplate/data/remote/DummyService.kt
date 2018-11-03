package ca.nick.boilerplate.data.remote

import ca.nick.boilerplate.data.Dummy
import io.reactivex.Single
import retrofit2.http.GET

interface DummyService {

    companion object {
        const val BASE_URL = "http://www.todo.com/"
    }

    @GET("todo")
    fun fetch(): Single<List<Dummy>>
}