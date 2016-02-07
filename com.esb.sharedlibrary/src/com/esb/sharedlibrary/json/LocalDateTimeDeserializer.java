package com.esb.sharedlibrary.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String dateString = jsonParser.getValueAsString();
        LocalDateTime localDateTime = null;
        try {
            localDateTime = ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_ZONED_DATE_TIME).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
        } catch (DateTimeParseException e) {
            try {
                localDateTime = LocalDateTime.parse(dateString);
            } catch (DateTimeParseException e1) {
                try {
                    localDateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
                } catch (DateTimeParseException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return localDateTime;
    }
}
