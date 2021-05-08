package DataBase;

import androidx.room.TypeConverter;

import java.util.Date;

import Model.Book;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Book.OrderInfoEnum OrderInfoFromString(String value) {
        return value == null ? null : Book.OrderInfoEnum.fromString(value);
    }
    @TypeConverter
    public static String OrderInfoToString(Book.OrderInfoEnum orderInfo) {
        return orderInfo == null ? null : orderInfo.toString();
    }
}
