package ca.nick.boilerplate.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ca.nick.boilerplate.R
import ca.nick.boilerplate.data.Dummy
import ca.nick.boilerplate.utils.DummyDiffCallback

class DummyListAdapter : ListAdapter<Dummy, DummyViewHolder>(DummyDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
            .let { DummyViewHolder(it) }

    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}