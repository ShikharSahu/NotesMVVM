package com.example.noteslivedata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteslivedata.databinding.RowItemNoteBinding


class NotesRecyclerViewAdapter(private val notesRVAdapterI: NotesRVAdapterI) : RecyclerView.Adapter<NotesRecyclerViewAdapter.NoteViewHolder> () {

    private val allNotesList = ArrayList<Note>()

    inner class NoteViewHolder(val binding: RowItemNoteBinding) : RecyclerView.ViewHolder(binding.root ) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = RowItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val noteViewHolder = NoteViewHolder(binding)
        noteViewHolder.binding.ibDeleteButton.setOnClickListener {
            notesRVAdapterI.onItemClicked(allNotesList[noteViewHolder.adapterPosition])
        }
        return noteViewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding.tvNoteText.text = allNotesList[position].text
    }

    override fun getItemCount(): Int = allNotesList.size

    fun updateList( notes : List <Note>) {
        allNotesList.clear()
        allNotesList.addAll(notes)
        notifyDataSetChanged()
    }
}

interface NotesRVAdapterI{
    fun onItemClicked(note : Note)
}
