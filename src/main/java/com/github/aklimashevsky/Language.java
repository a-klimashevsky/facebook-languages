package com.github.aklimashevsky;

import com.google.gson.annotations.SerializedName;

public class Language<K> {

    @SerializedName("id")
    private K id;

    @SerializedName("name")
    private String name;

    @SerializedName("iso3")
    private String iso3Code;

    @SerializedName("iso2")
    private String iso2Code;

    @SerializedName("iso1")
    private String iso1Code;

    public Language(K id, String iso3Code) {
        this(id, iso3Code, null, null, null);
    }

    public Language(K id, String iso3Code, String iso2Code, String iso1Code, String name) {
        this.id = id;
        this.iso3Code = iso3Code;
        this.iso2Code = iso2Code;
        this.iso1Code = iso1Code;
        this.name = name;
    }

    public K getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIso3Code() {
        return iso3Code;
    }

    public String getIso2Code() {
        return iso2Code;
    }

    public String getIso1Code() {
        return iso1Code;
    }
}
