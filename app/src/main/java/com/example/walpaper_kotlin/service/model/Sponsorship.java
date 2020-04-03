package com.example.walpaper_kotlin.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Sponsorship {

    @SerializedName("impression_urls")
    @Expose
    private List<String> impressionUrls = new ArrayList<String>();
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("sponsor")
    @Expose
    private Sponsor sponsor;

    public List<String> getImpressionUrls() {
        return impressionUrls;
    }

    public void setImpressionUrls(List<String> impressionUrls) {
        this.impressionUrls = impressionUrls;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

}
