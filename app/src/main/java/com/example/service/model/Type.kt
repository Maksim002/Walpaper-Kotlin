package com.example.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Type {
    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("pretty_slug")
    @Expose
    var prettySlug: String? = null

}