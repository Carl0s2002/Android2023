package com.tasty.recipesapp.ui.recipe

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.model.RecipeModel

class RecipeListAdapter(var recipes: Array<RecipeModel>):RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    var onClickListener: ((RecipeModel) -> Unit)? = null
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val thumbnailImage: ImageView
        init {
            title = itemView.findViewById(R.id.title)
            thumbnailImage = itemView.findViewById(R.id.thumbnailImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = recipes[position].title
        holder.thumbnailImage.setImageURI(Uri.parse(recipes[position].thumbnail))
        Log.d("RecipeListAdapter" , recipes[position].thumbnail.toString())
        holder.itemView.setOnClickListener{
                onClickListener?.invoke(recipes[position])
        }

    }

    override fun getItemCount(): Int {
        return recipes.size
    }

}