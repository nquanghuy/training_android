package com.example.myapplication.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
//support parsing json to object
public class LocalDateDeserializer implements JsonDeserializer< LocalDate > {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            return LocalDate.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.ENGLISH));
        }
        catch (Exception e){
            return LocalDate.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy").withLocale(Locale.ENGLISH));
        }
    }
}
