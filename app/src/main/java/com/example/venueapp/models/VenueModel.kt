package com.example.venueapp.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * @author Marko Nikolic on 10.4.23.
 */
data class VenueModel(@SerializedName("name") val name: String,
        @SerializedName("description") val description: String,
        @SerializedName("is_open") val isOpen: Boolean,
        @SerializedName("welcome_message") val welcomeMessage: String,
        @SerializedName("address") val address: String,
        @SerializedName("image") val image: Image?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString().toString(),
            parcel.readString().toString(), parcel.readByte() != 0.toByte(),
            parcel.readString().toString(), parcel.readString().toString(),
            parcel.readParcelable(Image::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeByte(if (isOpen) 1 else 0)
        parcel.writeString(welcomeMessage)
        parcel.writeString(address)
        parcel.writeParcelable(image, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VenueModel> {
        override fun createFromParcel(parcel: Parcel): VenueModel {
            return VenueModel(parcel)
        }

        override fun newArray(size: Int): Array<VenueModel?> {
            return arrayOfNulls(size)
        }
    }
}