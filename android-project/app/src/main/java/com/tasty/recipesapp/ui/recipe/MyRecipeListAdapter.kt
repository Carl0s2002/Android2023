package com.tasty.recipesapp.ui.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.model.RecipeModel

class MyRecipeListAdapter(var myRecipes: Array<RecipeModel>):RecyclerView.Adapter<MyRecipeListAdapter.ViewHolder>() {

    var onClickListener: ((RecipeModel) -> Unit)? = null
    var onClickListenerDelete: ((RecipeModel) -> Unit)? = null
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val thumbnailImage: ImageView
        val description:TextView
        val deleteButton:Button
        init {
            title = itemView.findViewById(R.id.title)
            thumbnailImage = itemView.findViewById(R.id.thumbnailImage)
            description = itemView.findViewById(R.id.description)
            deleteButton = itemView.findViewById(R.id.deleteButton)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_recipe_list_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = myRecipes[position].title
        Glide.with(holder.itemView)
            .load(myRecipes[position].thumbnail)
            .into(holder.thumbnailImage)
        holder.description.text = myRecipes[position].description


        holder.deleteButton.setOnClickListener{
            onClickListenerDelete?.invoke(myRecipes[position])
        }
        holder.itemView.setOnClickListener{
            onClickListener?.invoke(myRecipes[position])
        }

    }

    override fun getItemCount(): Int {
        return myRecipes.size
    }

}