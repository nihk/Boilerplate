package ca.nick.boilerplate.ui

import android.os.Bundle
import ca.nick.boilerplate.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, MainFragment.create(), MainFragment.TAG)
                .commit()
        }
    }
}
