package com.tasty.recipesapp.ui.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.model.InstructionModel

class InstructionsListAdapter(context: Context, var instructions: Array<InstructionModel>) :
    ArrayAdapter<InstructionModel>(context, R.layout.instructions_list_item, instructions) {

    private class ViewHolder {
        lateinit var instructionText: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val holder: ViewHolder
        val rowView: View

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            rowView = inflater.inflate(R.layout.instructions_list_item, parent, false)

            holder = ViewHolder()
            holder.instructionText = rowView.findViewById(R.id.instructionText)

            rowView.tag = holder
        } else {
            rowView = convertView
            holder = rowView.tag as ViewHolder
        }

        holder.instructionText.text = StringBuilder()
            .append(instructions[position].position)
            .append(". ")
            .append(instructions[position].display_text)

        return rowView
    }
}
