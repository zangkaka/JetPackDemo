package com.zang.jetpackdemo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zang.jetpackdemo.NoteAdapter
import com.zang.jetpackdemo.NoteRoomDatabase
import com.zang.jetpackdemo.R
import com.zang.jetpackdemo.entity.Note
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NoteActivity : AppCompatActivity(), CoroutineScope, View.OnClickListener {

    private var noteDB: NoteRoomDatabase? = null
    private var adapter: NoteAdapter? = null
    private lateinit var mJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        mJob = Job()
        noteDB =
            NoteRoomDatabase.getDatabase(this)
        adapter = NoteAdapter(this, noteDB!!)
        note_rcl_view.adapter = adapter
        note_rcl_view.layoutManager = LinearLayoutManager(this)

        note_add_btn.setOnClickListener(this)
        note_find_btn.setOnClickListener(this)
    }

    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    override fun onResume() {
        super.onResume()
        getAllNotes()
    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            note_add_btn.id -> {
                val intent = Intent(this, AddNoteActivity::class.java)
                startActivity(intent)
            }
            note_find_btn.id -> {
                findNote()
            }
        }
    }

    private fun getAllNotes() {
        launch {
            val notes: List<Note>? = noteDB!!.noteDao().getAllNotes()
            if (notes != null) {
                adapter!!.setNotes(notes)
            }
        }
    }

    private fun findNote() = launch {
        val strFind = note_find_edt.text.toString()
        if (!TextUtils.isEmpty(strFind)) {
            val note: Note? = noteDB!!.noteDao().findNoteById(strFind)
            if (note != null) {
                val notes: List<Note> = mutableListOf(note)
                adapter!!.setNotes(notes)
            }
        } else {
            getAllNotes()
        }
    }

}
