package com.example.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Sponsorship {
    @SerializedName("impression_urls")
    @Expose
    var impressionUrls: List<String> =
        ArrayList()

    @SerializedName("tagline")
    @Expose
    var tagline: String? = null

    @SerializedName("sponsor")
    @Expose
    var sponsor: Sponsor? = null

}