package by.gsu.epamlab.model.converter;

import by.gsu.epamlab.interfaces.IDataConverter;
import by.gsu.epamlab.model.beans.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataConverter implements IDataConverter {
    @Override
    public Integer[] getTasksFromJson(String json) {
        Gson gson = new GsonBuilder().create();
        return  gson.fromJson(json, Integer[].class);
    }
}
