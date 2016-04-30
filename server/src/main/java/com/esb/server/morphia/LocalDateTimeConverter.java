//package com.esb.server.morphia;
//
//import org.mongodb.morphia.converters.DateConverter;
//import org.mongodb.morphia.converters.SimpleValueConverter;
//import org.mongodb.morphia.converters.TypeConverter;
//import org.mongodb.morphia.mapping.MappedField;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//
//
//public class LocalDateTimeConverter extends TypeConverter implements SimpleValueConverter {
//
//    public LocalDateTimeConverter()
//    {
//        super(LocalDateTime.class);
//    }
//
//    @Override
//    public Object decode(Class<?> aClass, Object o, MappedField mappedField) {
//        Object result = null;
//        if (o != null)
//        {
//            Date date = (Date)o;
//            Instant instant = Instant.ofEpochMilli(date.getTime());
//            result = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//        }
//        return result;
//    }
//
//    @Override
//    public Object encode(Object value, MappedField optionalExtraInfo) {
//        if(value == null) return null;
//
//        LocalDateTime localDateTime = (LocalDateTime)value;
//        final Date from = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        DateConverter dateConverter = new DateConverter();
//        final Object encode = dateConverter.encode(from, optionalExtraInfo);
//
//        return encode;
//
//    }
//}
