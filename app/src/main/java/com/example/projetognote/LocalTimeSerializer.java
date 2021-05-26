package com.example.projetognote;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class LocalTimeSerializer implements JsonDeserializer<LocalTime>, JsonSerializer<LocalTime>
{

    //        private static final DateTimeFormatter TIME_FORMAT = ISODateTimeFormat.timeNoMillis();
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern ("HH:mm:ss");

    @Override
    public LocalTime deserialize(final JsonElement je, final Type type,
                                 final JsonDeserializationContext jdc) throws JsonParseException
    {
        String dateAsString = je.toString();

        dateAsString = dateAsString.replaceAll("\"", "");

        System.out.println("dateAsString " + dateAsString);

        if (je.isJsonNull() || dateAsString.length() == 0)
        {
            return null;
        }
        else
        {
            return LocalTime.parse (dateAsString);
//                return TIME_FORMAT.parseLocalTime(dateAsString);

        }
    }

    @Override
    public JsonElement serialize(final LocalTime src, final Type typeOfSrc,
                                 final JsonSerializationContext context)
    {
        String retVal;
        if (src == null)
        {
            retVal = "";
        }
        else
        {
            retVal = TIME_FORMAT.format(src);
        }
        return new JsonPrimitive(retVal);
    }

}
