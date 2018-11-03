package ca.nick.boilerplate.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.nick.boilerplate.data.Dummy.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Dummy(
    @PrimaryKey
    val id: Long,
    val data: String
) {

    companion object {
        const val TABLE_NAME = "todo"
    }
}