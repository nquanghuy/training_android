package com.example.myapplication.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//support parsing object to json
@RequiresApi(api = Build.VERSION_CODES.O)
public class LocalDateSerializer implements JsonSerializer<LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @Override
    public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
        try {
            return new JsonPrimitive(formatter.format(localDate));
        }
        catch (Exception e){
            return  new JsonPrimitive(formatter2.format(localDate));
        }
    }
}
