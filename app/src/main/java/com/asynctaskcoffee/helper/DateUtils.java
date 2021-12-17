package com.asynctaskcoffee.helper;

import android.annotation.SuppressLint;

import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    @NotNull
    public static final String SIMPLE_FORMAT = "dd-MM-yy";
    @NotNull
    public static final String DATE_FORMAT_5 = "MMM dd, yyyy";
    @NotNull
    public static final String DATE_FORMAT_6 = "dd MMM";
    @NotNull
    public static final String DATE_FORMAT_7 = "MM-dd-yyyy";
    @NotNull
    public static final String HOUR_FORMAT_1 = "h:mm a";
    @NotNull
    public static final String CALENDAR_DEFAULT_FORMAT = "EE MMM dd HH:mm:ss z yyyy";
    @NotNull
    public static final String CAMERA_FORMAT = "yyyyMMdd_HHmmss";
    @NotNull
    public static final String FILE_NAME = "yyyyMMdd_HHmmssSSS";
    @NotNull
    public static final String UTC_TIME = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    @NotNull
    public static final String UTC_TIME_MILLIS_1 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    @NotNull
    public static final String UTC_TIME_MILLIS_2 = "yyyy-MM-dd'T'HH:mm:ss";
    @NotNull
    public static final String EARNING_TIME_FORMAT = "MMM dd, yyyy";
    @NotNull
    public static final String JOB_FORMAT = "dd MMM, h:mm a";
    @NotNull
    public static final String CHAT_TIME_FORMAT = "dd MMM, hh:mm a";
    @NotNull
    public static final String DATE_FORMAT_CARD = "MM-yyyy";
    @NotNull
    public static final DateUtils INSTANCE;

    @NotNull
    public static Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setFirstDayOfWeek(1);
        return calendar;
    }

    @SuppressLint({"SimpleDateFormat"})
    @NotNull
    public static String getCurrentDate(@Nullable String format) {
        Calendar c = getCalendar();
        SimpleDateFormat df = new SimpleDateFormat(format);
        String date = df.format(c.getTime());
        return date;
    }

    @Nullable
    public static String oneFormatToAnother(@Nullable String date, @Nullable String oldFormat, @Nullable String newFormat) {
        String var4;
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat(oldFormat, Locale.getDefault());
            SimpleDateFormat targetFormat = new SimpleDateFormat(newFormat, Locale.getDefault());
            Date d = originalFormat.parse(date);
            var4 = targetFormat.format(d);
        } catch (Exception var7) {
            var7.printStackTrace();
            var4 = null;
        }

        return var4;
    }

    @Nullable
    public final Date stringToDate(@Nullable String date, @Nullable String format) {
        Date var3;
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat(format, Locale.getDefault());
            var3 = originalFormat.parse(date);
        } catch (Exception var5) {
            var3 = null;
        }

        return var3;
    }

    @RequiresApi(26)
    @NotNull
    public final String calDaysDiff(@Nullable String bookingData) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        Date todayDate = sdf.parse(this.getCurrentDate("dd-MM-yy"));
        Date bookingDate = sdf.parse(bookingData);
        long diff = bookingDate.getTime() - todayDate.getTime();
        return String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }

    private DateUtils() {
    }

    static {
        DateUtils var0 = new DateUtils();
        INSTANCE = var0;
    }
}
