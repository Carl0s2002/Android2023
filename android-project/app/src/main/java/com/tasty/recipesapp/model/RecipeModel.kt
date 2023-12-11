package com.tasty.recipesapp.model

import android.os.Parcel
import android.os.Parcelable

data class RecipeModel(
    val id: Long ,
    val title: String? ,
    val description: String? ,
    val video: String? ,
    val thumbnail: String? ,
    val instructions: Array<InstructionModel> ,
    val sections: Array<SectionsModel>
):Parcelable {
    constructor(parcel: Parcel) : this( parcel.readLong() , parcel.readString() , parcel.readString() , parcel.readString() , parcel.readString() , parcel.readArray(RecipeModel::class.java.classLoader) as Array<InstructionModel> ,
        parcel.readArray(SectionsModel::class.java.classLoader) as Array<SectionsModel>
    ) {
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
