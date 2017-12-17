/*
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2017 Gilles Landel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package fr.landel.utils.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Useful date constants.
 *
 * @since Dec 11, 2017
 * @author Gilles Landel
 *
 */
class DateUtilsConstants extends org.apache.commons.lang3.time.DateUtils {

    /**
     * Number of days in January
     */
    public static final int DAYS_IN_JANUARY = 31;
    /**
     * Number of days in February
     */
    public static final int DAYS_IN_FEBRUARY = 28;
    /**
     * Number of days in February in a leap year
     */
    public static final int DAYS_IN_FEBRUARY_LEAP = 29;
    /**
     * Number of days in March
     */
    public static final int DAYS_IN_MARCH = 31;
    /**
     * Number of days in April
     */
    public static final int DAYS_IN_APRIL = 30;
    /**
     * Number of days in May
     */
    public static final int DAYS_IN_MAY = 31;
    /**
     * Number of days in June
     */
    public static final int DAYS_IN_JUNE = 30;
    /**
     * Number of days in July
     */
    public static final int DAYS_IN_JULY = 31;
    /**
     * Number of days in August
     */
    public static final int DAYS_IN_AUGUST = 31;
    /**
     * Number of days in September
     */
    public static final int DAYS_IN_SEPTEMBER = 30;
    /**
     * Number of days in October
     */
    public static final int DAYS_IN_OCTOBER = 31;
    /**
     * Number of days in November
     */
    public static final int DAYS_IN_NOVEMBER = 30;
    /**
     * Number of days in December
     */
    public static final int DAYS_IN_DECEMBER = 31;
    /**
     * List of days by month
     */
    public static final List<Integer> DAYS_BY_MONTH = Arrays.asList(DAYS_IN_JANUARY, DAYS_IN_FEBRUARY, DAYS_IN_MARCH, DAYS_IN_APRIL,
            DAYS_IN_MAY, DAYS_IN_JUNE, DAYS_IN_JULY, DAYS_IN_AUGUST, DAYS_IN_SEPTEMBER, DAYS_IN_OCTOBER, DAYS_IN_NOVEMBER,
            DAYS_IN_DECEMBER);
    /**
     * List of days by month in a leap year
     */
    public static final List<Integer> DAYS_BY_MONTH_LEAP = Arrays.asList(DAYS_IN_JANUARY, DAYS_IN_FEBRUARY_LEAP, DAYS_IN_MARCH,
            DAYS_IN_APRIL, DAYS_IN_MAY, DAYS_IN_JUNE, DAYS_IN_JULY, DAYS_IN_AUGUST, DAYS_IN_SEPTEMBER, DAYS_IN_OCTOBER, DAYS_IN_NOVEMBER,
            DAYS_IN_DECEMBER);
    /**
     * Map of months: key = name of month, value = number of days
     */
    public static final Map<String, Integer> MONTHS;
    /**
     * Map of months (leap year): key = name of month, value = number of days
     */
    public static final Map<String, Integer> MONTHS_LEAP;
    static {
        Map<String, Integer> months = new LinkedHashMap<>();
        months.put("January", DAYS_IN_JANUARY);
        months.put("February", DAYS_IN_FEBRUARY);
        months.put("March", DAYS_IN_MARCH);
        months.put("April", DAYS_IN_APRIL);
        months.put("May", DAYS_IN_MAY);
        months.put("June", DAYS_IN_JUNE);
        months.put("July", DAYS_IN_JULY);
        months.put("August", DAYS_IN_AUGUST);
        months.put("September", DAYS_IN_SEPTEMBER);
        months.put("October", DAYS_IN_OCTOBER);
        months.put("November", DAYS_IN_NOVEMBER);
        months.put("December", DAYS_IN_DECEMBER);
        MONTHS = Collections.unmodifiableMap(months);
        months = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : MONTHS.entrySet()) {
            months.put(entry.getKey(), entry.getValue());
        }
        months.put("February", DAYS_IN_FEBRUARY_LEAP);
        MONTHS_LEAP = Collections.unmodifiableMap(months);
    }

    /**
     * Number of seconds in a standard minute.
     */
    public static final long SECONDS_PER_MINUTE = 60L;
    /**
     * Number of minutes in a standard hour.
     */
    public static final long MINUTES_PER_HOUR = 60L;
    /**
     * Number of hours in a standard day.
     */
    public static final long HOURS_PER_DAY = 24L;
    /**
     * Number of days in a standard week.
     */
    public static final long DAYS_PER_WEEK = 7L;
    /**
     * Number of days in a standard year.
     */
    public static final long DAYS_PER_YEAR = 365L;
    /**
     * Number of days in a leap year.
     */
    public static final long DAYS_PER_YEAR_LEAP = 366L;
    /**
     * Number of nanoseconds per microsecond
     */
    public static final long NANOS_PER_MICRO = 1_000L;
    /**
     * Number of nanoseconds per millisecond
     */
    public static final long NANOS_PER_MILLI = 1_000_000L;
    /**
     * Number of nanoseconds per second
     */
    public static final long NANOS_PER_SECOND = 1_000_000_000L;
    /**
     * Number of nanoseconds per minute
     */
    public static final long NANOS_PER_MINUTE = NANOS_PER_SECOND * DateUtilsConstants.SECONDS_PER_MINUTE;
    /**
     * Number of nanoseconds per hour
     */
    public static final long NANOS_PER_HOUR = NANOS_PER_SECOND * DateUtilsConstants.SECONDS_PER_HOUR;
    /**
     * Number of nanoseconds per day
     */
    public static final long NANOS_PER_DAY = NANOS_PER_SECOND * DateUtilsConstants.SECONDS_PER_DAY;
    /**
     * Number of microseconds per millisecond
     */
    public static final long MICROS_PER_MILLI = 1_000L;
    /**
     * Number of nanoseconds per second
     */
    public static final long MICROS_PER_SECOND = 1_000_000L;
    /**
     * Number of nanoseconds per minute
     */
    public static final long MICROS_PER_MINUTE = MICROS_PER_SECOND * DateUtilsConstants.SECONDS_PER_MINUTE;
    /**
     * Number of nanoseconds per hour
     */
    public static final long MICROS_PER_HOUR = MICROS_PER_SECOND * DateUtilsConstants.SECONDS_PER_HOUR;
    /**
     * Number of nanoseconds per day
     */
    public static final long MICROS_PER_DAY = MICROS_PER_SECOND * DateUtilsConstants.SECONDS_PER_DAY;
    /**
     * Number of milliseconds in a standard week.
     */
    public static final long MILLIS_PER_WEEK = MILLIS_PER_DAY * DAYS_PER_WEEK;
    /**
     * Number of milliseconds in a standard year.
     */
    public static final long MILLIS_PER_YEAR = MILLIS_PER_DAY * DAYS_PER_YEAR;
    /**
     * Number of milliseconds in a leap year.
     */
    public static final long MILLIS_PER_YEAR_LEAP = MILLIS_PER_DAY * DAYS_PER_YEAR_LEAP;
    /**
     * Number of seconds in a standard hour.
     */
    public static final long SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    /**
     * Number of seconds in a standard day.
     */
    public static final long SECONDS_PER_DAY = SECONDS_PER_HOUR * HOURS_PER_DAY;
    /**
     * Number of seconds in a standard week.
     */
    public static final long SECONDS_PER_WEEK = SECONDS_PER_DAY * DAYS_PER_WEEK;
    /**
     * Number of seconds in a standard year.
     */
    public static final long SECONDS_PER_YEAR = SECONDS_PER_DAY * DAYS_PER_YEAR;
    /**
     * Number of seconds in a leap year.
     */
    public static final long SECONDS_PER_YEAR_LEAP = SECONDS_PER_DAY * DAYS_PER_YEAR_LEAP;
    /**
     * Number of minutes in a standard day.
     */
    public static final long MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;
    /**
     * Number of minutes in a standard week.
     */
    public static final long MINUTES_PER_WEEK = MINUTES_PER_DAY * DAYS_PER_WEEK;
    /**
     * Number of minutes in a standard year.
     */
    public static final long MINUTES_PER_YEAR = MINUTES_PER_DAY * DAYS_PER_YEAR;
    /**
     * Number of minutes in a leap year.
     */
    public static final long MINUTES_PER_YEAR_LEAP = MINUTES_PER_DAY * DAYS_PER_YEAR_LEAP;
    /**
     * Number of hours in a standard week.
     */
    public static final long HOURS_PER_WEEK = HOURS_PER_DAY * DAYS_PER_WEEK;
    /**
     * Number of hours in a standard year.
     */
    public static final long HOURS_PER_YEAR = HOURS_PER_DAY * DAYS_PER_YEAR;
    /**
     * Number of hours in a leap year.
     */
    public static final long HOURS_PER_YEAR_LEAP = HOURS_PER_DAY * DAYS_PER_YEAR_LEAP;
    /**
     * Number of months in a year
     */
    public static final long MONTHS_PER_YEAR = 12L;
    /**
     * Number of years per decade
     */
    public static final long YEARS_PER_DECADE = 10L;
    /**
     * Number of months per decade
     */
    public static final long MONTHS_PER_DECADE = MONTHS_PER_YEAR * YEARS_PER_DECADE;
    /**
     * Average number of days per decade (use 2 leap years)
     */
    public static final long DAYS_PER_DECADE_AVG = DAYS_PER_YEAR * YEARS_PER_DECADE + DateUtilsConstants.DAYS_PER_DECADE_LEAP_AVG;
    /**
     * Average number of weeks per decade (use 2 leap years)
     */
    public static final long WEEKS_PER_DECADE_AVG = DAYS_PER_DECADE_AVG / DAYS_PER_WEEK;
    /**
     * Average number of hours per decade (use 2 leap years)
     */
    public static final long HOURS_PER_DECADE_AVG = HOURS_PER_DAY * DAYS_PER_DECADE_AVG;
    /**
     * Average number of minutes per decade (use 2 leap years)
     */
    public static final long MINUTES_PER_DECADE_AVG = MINUTES_PER_DAY * DAYS_PER_DECADE_AVG;
    /**
     * Average number of seconds per decade (use 2 leap years)
     */
    public static final long SECONDS_PER_DECADE_AVG = SECONDS_PER_DAY * DAYS_PER_DECADE_AVG;
    /**
     * Average number of milliseconds per decade (use 2 leap years)
     */
    public static final long MILLIS_PER_DECADE_AVG = MILLIS_PER_DAY * DAYS_PER_DECADE_AVG;
    /**
     * Number of years per century
     */
    public static final long DECADES_PER_CENTURY = 10L;
    /**
     * Average number of years per century
     */
    public static final long YEARS_PER_CENTURY = YEARS_PER_DECADE * DECADES_PER_CENTURY;
    /**
     * Average number of months per century
     */
    public static final long MONTHS_PER_CENTURY = MONTHS_PER_DECADE * DECADES_PER_CENTURY;
    /**
     * Average number of days per century (use 24 leap years)
     */
    public static final long DAYS_PER_CENTURY_AVG = DAYS_PER_YEAR * YEARS_PER_CENTURY + DateUtilsConstants.DAYS_PER_CENTURY_LEAP_AVG;
    /**
     * Average number of weeks per century (use 24 leap years)
     */
    public static final long WEEKS_PER_CENTURY_AVG = DAYS_PER_CENTURY_AVG / DAYS_PER_WEEK;
    /**
     * Average number of hours per century (use 24 leap years)
     */
    public static final long HOURS_PER_CENTURY_AVG = HOURS_PER_DAY * DAYS_PER_CENTURY_AVG;
    /**
     * Average number of minutes per century (use 24 leap years)
     */
    public static final long MINUTES_PER_CENTURY_AVG = MINUTES_PER_DAY * DAYS_PER_CENTURY_AVG;
    /**
     * Average number of seconds per century (use 24 leap years)
     */
    public static final long SECONDS_PER_CENTURY_AVG = SECONDS_PER_DAY * DAYS_PER_CENTURY_AVG;
    /**
     * Average number of milliseconds per century (use 24 leap years)
     */
    public static final long MILLIS_PER_CENTURY_AVG = MILLIS_PER_DAY * DAYS_PER_CENTURY_AVG;
    /**
     * Number of centuries per millennium
     */
    public static final long CENTURIES_PER_MILLENNIUM = 10L;
    /**
     * Number of years per millennium
     */
    public static final long YEARS_PER_MILLENNIUM = YEARS_PER_CENTURY * CENTURIES_PER_MILLENNIUM;
    /**
     * Number of months per millennium
     */
    public static final long MONTHS_PER_MILLENNIUM = MONTHS_PER_CENTURY * CENTURIES_PER_MILLENNIUM;
    /**
     * Average number of days per millennium (use 243 leap years)
     */
    public static final long DAYS_PER_MILLENNIUM_AVG = DAYS_PER_YEAR * YEARS_PER_MILLENNIUM
            + DateUtilsConstants.DAYS_PER_MILLENNIUM_LEAP_AVG;
    /**
     * Average number of weeks per millennium (use 243 leap years)
     */
    public static final long WEEKS_PER_MILLENNIUM_AVG = DAYS_PER_MILLENNIUM_AVG / DAYS_PER_WEEK;
    /**
     * Average number of hours per millennium (use 243 leap years)
     */
    public static final long HOURS_PER_MILLENNIUM_AVG = HOURS_PER_DAY * DAYS_PER_MILLENNIUM_AVG;
    /**
     * Average number of minutes per millennium (use 243 leap years)
     */
    public static final long MINUTES_PER_MILLENNIUM_AVG = MINUTES_PER_DAY * DAYS_PER_MILLENNIUM_AVG;
    /**
     * Average number of seconds per millennium (use 243 leap years)
     */
    public static final long SECONDS_PER_MILLENNIUM_AVG = SECONDS_PER_DAY * DAYS_PER_MILLENNIUM_AVG;
    /**
     * Average number of milliseconds per millennium (use 243 leap years)
     */
    public static final long MILLIS_PER_MILLENNIUM_AVG = MILLIS_PER_DAY * DAYS_PER_MILLENNIUM_AVG;

    /**
     * Average leap year period.
     * 
     * <pre>
     * (year % 4 == 0 &amp;&amp; year % 100 != 0) || (year % 400 == 0)
     * </pre>
     */
    public static final float LEAP_YEAR_PERIOD_AVG = 0.2425f;

    /**
     * Average days by month
     */
    public static final float DAYS_PER_MONTH_AVG = (DAYS_PER_YEAR + LEAP_YEAR_PERIOD_AVG) / MONTHS_PER_YEAR;

    /**
     * ROUND ({@link #YEARS_PER_DECADE} * {@link #LEAP_YEAR_PERIOD_AVG})
     */
    private static final long DAYS_PER_DECADE_LEAP_AVG = 2L;
    /**
     * ROUND ({@link #YEARS_PER_CENTURY} * {@link #LEAP_YEAR_PERIOD_AVG})
     */
    private static final long DAYS_PER_CENTURY_LEAP_AVG = 24L;
    /**
     * ROUND ({@link #YEARS_PER_MILLENNIUM} * {@link #LEAP_YEAR_PERIOD_AVG})
     */
    private static final long DAYS_PER_MILLENNIUM_LEAP_AVG = 243L;

    protected static final List<Integer> CALENDAR_FIELDS;
    static {
        List<Integer> list = new ArrayList<>();
        list.add(Calendar.ERA);
        list.add(Calendar.YEAR);
        list.add(Calendar.MONTH);
        list.add(Calendar.WEEK_OF_YEAR);
        list.add(Calendar.WEEK_OF_MONTH);
        list.add(Calendar.DATE);
        list.add(Calendar.DAY_OF_MONTH);
        list.add(Calendar.DAY_OF_YEAR);
        list.add(Calendar.DAY_OF_WEEK);
        list.add(Calendar.DAY_OF_WEEK_IN_MONTH);
        list.add(Calendar.AM_PM);
        list.add(Calendar.HOUR);
        list.add(Calendar.HOUR_OF_DAY);
        list.add(Calendar.MINUTE);
        list.add(Calendar.SECOND);
        list.add(Calendar.MILLISECOND);
        CALENDAR_FIELDS = Collections.unmodifiableList(list);
    }
}