package ru.mephi.lab.utils.idHelper;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IdHandler {

    @SerializedName("idInfosArray")
    ArrayList<IdInfo> idInfosArray;

    public IdHandler(ArrayList<IdInfo> idInfosArray) {
        this.idInfosArray = idInfosArray;
    }

    public String getGamePathById(String id) {
        for (IdInfo info : idInfosArray) {
            if (info.getId().equals(id)) {
                return info.getGamePath();
            }
        }
        return "";
    }
}
