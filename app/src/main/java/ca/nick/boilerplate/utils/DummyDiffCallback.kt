package ca.nick.boilerplate.utils

import androidx.recyclerview.widget.DiffUtil
import ca.nick.boilerplate.data.Dummy

object DummyDiffCallback : DiffUtil.ItemCallback<Dummy>() {

    override fun areItemsTheSame(oldItem: Dummy, newItem: Dummy) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Dummy, newItem: Dummy) =
        oldItem == newItem
}