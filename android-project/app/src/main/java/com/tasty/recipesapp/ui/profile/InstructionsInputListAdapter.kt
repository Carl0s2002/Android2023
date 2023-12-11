package com.tasty.recipesapp.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R

class InstructionsInputListAdapter(var instructions: MutableList<String>) : RecyclerView.Adapter<InstructionsInputListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val editText: EditText

        init {
            editText = itemView.findViewById(R.id.instructions_input_list_item)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.instructions_input_list_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.editText.setHint(instructions[position])
        holder.editText.doOnTextChanged { text, start, before, count ->
            instructions[position] = text.toString()
        }
    }

    override fun getItemCount(): Int = instructions.size

}