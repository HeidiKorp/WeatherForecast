package dataParser;

import com.google.gson.Gson;
import dataContainer.DataContainer;

public class FromJson {

    public DataContainer parseFromJson(String jsonString) {
        return new Gson().fromJson(jsonString, DataContainer.class);
    }
}
