package ca.nick.boilerplate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ca.nick.boilerplate.R
import ca.nick.boilerplate.vm.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {

    private lateinit var viewModel: MainViewModel
    private val adapter = DummyListAdapter()

    companion object {
        val TAG: String = MainFragment::class.java.simpleName
        fun create() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        items.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)

        viewModel.errorMessage.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                Snackbar.make(root, it, Snackbar.LENGTH_SHORT).show()
            }
        })

        viewModel.progressBarState.observe(this, Observer {
            progress_bar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.items.observe(this, Observer {
            adapter.submitList(it)
        })

        if (savedInstanceState == null) {
            viewModel.fetchThenPersistLocally()
        }
    }
}