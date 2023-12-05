package com.tasty.recipesapp.model

import android.os.Parcel
import android.os.Parcelable

data class RecipeModel(
    val title: String? ,
    val video: String? ,
    val instructions: Array<InstructionModel>
):Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString() , parcel.readString() , parcel.readArray(RecipeModel::class.java.classLoader) as Array<InstructionModel> ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeModel> {
        override fun createFromParcel(parcel: Parcel): RecipeModel {
            return RecipeModel(parcel)
        }

        override fun newArray(size: Int): Array<RecipeModel?> {
            return arrayOfNulls(size)
        }
    }
}
