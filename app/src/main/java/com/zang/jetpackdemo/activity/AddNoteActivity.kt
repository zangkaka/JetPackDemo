package com.zang.jetpackdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zang.jetpackdemo.NoteRoomDatabase
import com.zang.jetpackdemo.R
import com.zang.jetpackdemo.entity.Note
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddNoteActivity : AppCompatActivity(), CoroutineScope {
    private var noteDB: NoteRoomDatabase? = null
    private lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        mJob = Job()
        noteDB =
            NoteRoomDatabase.getDatabase(this)
        add_note_btn.setOnClickListener {
            launch {
                val title: String = add_title_edt.text.toString()
                val content: String = add_content_edt.text.toString()
                noteDB!!.noteDao().insertNote(Note(title = title, content = content))
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }
}
