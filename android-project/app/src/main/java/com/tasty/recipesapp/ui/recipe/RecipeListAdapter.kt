package com.tasty.recipesapp.ui.recipe

import android.util.Log
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

class RecipeListAdapter(var recipes: Array<RecipeModel>):RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    var onClickListener: ((RecipeModel) -> Unit)? = null
    var favoritesOnClickListener: ((RecipeModel) -> Unit)? = null
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val thumbnailImage: ImageView
        val description:TextView
        val addToFavorites:Button
        init {
            title = itemView.findViewById(R.id.title)
            thumbnailImage = itemView.findViewById(R.id.thumbnailImage)
            description = itemView.findViewById(R.id.description)
            addToFavorites = itemView.findViewById(R.id.FavoritesButton)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = recipes[position].title
        Glide.with(holder.itemView)
            .load(recipes[position].thumbnail)
            .into(holder.thumbnailImage)
        holder.description.text = recipes[position].description
        Log.d("RecipeListAdapter" , recipes[position].thumbnail.toString())
        holder.itemView.setOnClickListener{
                onClickListener?.invoke(recipes[position])
        }

        holder.addToFavorites.setOnClickListener {
            favoritesOnClickListener?.invoke(recipes[position])
        }

    }

    override fun getItemCount(): Int {
        return recipes.size
    }

}