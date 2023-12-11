package com.tasty.recipesapp.ui.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.model.ComponentsModel

class IngredientListAdapter(context: Context, var ingredients: Array<ComponentsModel>) :
    ArrayAdapter<ComponentsModel>(context, R.layout.ingredients_list_item, ingredients) {

    private class ViewHolder {
        lateinit var ingredientText: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val holder: ViewHolder
        val rowView: View

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            rowView = inflater.inflate(R.layout.instructions_list_item, parent, false)

            holder = ViewHolder()
            holder.ingredientText = rowView.findViewById(R.id.instructionText)

            rowView.tag = holder
        } else {
            rowView = convertView
            holder = rowView.tag as ViewHolder
        }

        holder.ingredientText.text = StringBuilder()
            .append(ingredients[position].position)
            .append(". ")
            .append(ingredients[position].ingredient.name)

        return rowView
    }
}
