package com.example.noteslivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteslivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NotesRVAdapterI {

    private lateinit var viewModel: NoteViewModel
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : NotesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNotesList.layoutManager = LinearLayoutManager(this)
        adapter = NotesRecyclerViewAdapter(this)
        binding.rvNotesList.adapter = adapter


        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        viewModel.getAllNotes().observe(this, Observer {it1->
            it1?.let {
                adapter.updateList(it)
            }
        })

        binding.btnAddNote.setOnClickListener{
            if(binding.tvUserInput.text!!.isNotEmpty()){
                val newNote = Note(binding.tvUserInput.text.toString())
                viewModel.insert(newNote)
                Toast.makeText(baseContext, newNote.text + " inserted" , Toast.LENGTH_SHORT ).show()
                binding.tvUserInput.setText("")
            }
        }
    }

    override fun onItemClicked(note: Note) {
        viewModel.delete(note)
        Toast.makeText(baseContext, note.text +" deleted", Toast.LENGTH_SHORT ).show()
    }
}