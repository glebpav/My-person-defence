package ru.mephi.lab.utils.idHelper;

import com.google.gson.annotations.SerializedName;

public class IdInfo {

    @SerializedName("id")
    private final String id;
    @SerializedName("gamePath")
    private final String gamePath;

    public IdInfo(String id, String gamePath) {
        this.id = id;
        this.gamePath = gamePath;
    }

    public String getId() {
        return id;
    }

    public String getGamePath() {
        return gamePath;
    }
}
