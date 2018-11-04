package ca.nick.boilerplate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ca.nick.boilerplate.R
import ca.nick.boilerplate.data.Dummy
import ca.nick.boilerplate.utils.visibleOrGone
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
            event.getContentIfNotHandled()?.let { message ->
                Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show()
                // Fallback to locally cached data, if any
                viewModel.items.value?.let { items ->
                    submitList(items)
                }
            }
        })

        viewModel.progressBarState.observe(this, Observer {
            progress_bar.visibleOrGone(it)
        })

        viewModel.items.observe(this, Observer { items ->
            if (items.isEmpty() || (savedInstanceState == null && viewModel.isPersistedDataStale())) {
                viewModel.fetchThenPersistLocally()
            } else {
                submitList(items)
            }
        })

        if (savedInstanceState == null) {
            viewModel.fetchThenPersistLocally()
        }
    }

    private fun submitList(items: List<Dummy>) {
        adapter.submitList(items)
    }
}