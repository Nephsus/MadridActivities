package kc.dcava.domain.managers.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kc.dcava.domain.model.MyActivity;

public class ActivityResponseEntity {
    @SerializedName("result") private List<ActivityEntity> result;

    public List<ActivityEntity> getResult() {
        return result;
    }
}
