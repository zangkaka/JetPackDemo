package com.zang.jetpackdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zang.jetpackdemo.entity.Note
import kotlinx.android.synthetic.main.note_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NoteAdapter internal constructor(context: Context, val noteRoomDatabase: NoteRoomDatabase) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>()

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    internal fun setNotes(note: List<Note>) {
        this.notes = note
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.titleNote.text = currentNote.title
        holder.contentNote.text = currentNote.content
        holder.deleteNote.setOnClickListener {
            uiScope.launch {
                noteRoomDatabase.noteDao().deleteNote(currentNote)
                noteRoomDatabase.noteDao().getAllNotes()
                notifyDataSetChanged()
            }
        }
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleNote = itemView.note_item_title_txt
        val contentNote = itemView.note_item_content_txt
        val deleteNote = itemView.note_item_delete_btn
    }
}