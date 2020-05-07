package com.example.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProfileImage_ {
    @SerializedName("small")
    @Expose
    var small: String? = null

    @SerializedName("medium")
    @Expose
    var medium: String? = null

    @SerializedName("large")
    @Expose
    var large: String? = null

}