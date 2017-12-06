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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * Check utility class (dates). {@link DateUtils}
 *
 * @since Nov 27, 2015
 * @author Gilles Landel
 *
 */
public class DateUtilsTest extends AbstractTest {

    private static final int YEAR = 2014;
    private static final int MONTH = 7;
    private static final int DAY = 15;

    /**
     * Test constructor for {@link DateUtils} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(DateUtils.class));
    }

    /**
     * Check {@link DateUtils#DAYS_BY_MONTH} and
     * {@link DateUtils#DAYS_BY_MONTH_LEAP}
     */
    @Test
    public void testDaysMonths() {
        final Integer[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        final Integer[] daysLeap = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        assertEquals(DateUtils.MONTHS_PER_YEAR, DateUtils.DAYS_BY_MONTH.size());
        assertEquals(DateUtils.MONTHS_PER_YEAR, DateUtils.DAYS_BY_MONTH_LEAP.size());

        int i = 0;
        int c = 0;
        int c28 = 0;
        int c30 = 0;
        int c31 = 0;
        for (Integer d : DateUtils.DAYS_BY_MONTH) {
            c += d;
            if (d == 28) {
                ++c28;
            } else if (d == 30) {
                ++c30;
            } else if (d == 31) {
                ++c31;
            }
            assertEquals(days[i++], d);
        }
        assertEquals(DateUtils.DAYS_PER_YEAR, c);
        assertEquals(1, c28);
        assertEquals(4, c30);
        assertEquals(7, c31);

        i = 0;
        c = 0;
        int c29 = 0;
        c30 = 0;
        c31 = 0;
        for (Integer d : DateUtils.DAYS_BY_MONTH_LEAP) {
            c += d;
            if (d == 29) {
                ++c29;
            } else if (d == 30) {
                ++c30;
            } else if (d == 31) {
                ++c31;
            }
            assertEquals(daysLeap[i++], d);
        }
        assertEquals(DateUtils.DAYS_PER_YEAR_LEAP, c);
        assertEquals(1, c29);
        assertEquals(4, c30);
        assertEquals(7, c31);
    }

    /**
     * Check {@link DateUtils#MONTHS} and {@link DateUtils#MONTHS_LEAP}
     */
    @Test
    public void testMONTHS() {
        final Integer[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        final Integer[] daysLeap = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        assertEquals(DateUtils.MONTHS_PER_YEAR, DateUtils.MONTHS.size());
        assertEquals(DateUtils.MONTHS_PER_YEAR, DateUtils.MONTHS_LEAP.size());

        int i = 0;
        int c = 0;
        int c28 = 0;
        int c30 = 0;
        int c31 = 0;
        for (Entry<String, Integer> entry : DateUtils.MONTHS.entrySet()) {
            c += entry.getValue();
            if (entry.getValue() == 28) {
                ++c28;
            } else if (entry.getValue() == 30) {
                ++c30;
            } else if (entry.getValue() == 31) {
                ++c31;
            }
            assertEquals(days[i++], entry.getValue());
        }
        assertEquals(DateUtils.DAYS_PER_YEAR, c);
        assertEquals(1, c28);
        assertEquals(4, c30);
        assertEquals(7, c31);

        i = 0;
        c = 0;
        int c29 = 0;
        c30 = 0;
        c31 = 0;
        for (Entry<String, Integer> entry : DateUtils.MONTHS_LEAP.entrySet()) {
            c += entry.getValue();
            if (entry.getValue() == 29) {
                ++c29;
            } else if (entry.getValue() == 30) {
                ++c30;
            } else if (entry.getValue() == 31) {
                ++c31;
            }
            assertEquals(daysLeap[i++], entry.getValue());
        }
        assertEquals(DateUtils.DAYS_PER_YEAR_LEAP, c);
        assertEquals(1, c29);
        assertEquals(4, c30);
        assertEquals(7, c31);
    }

    /**
     * Check get date wrapper
     */
    @Test
    public void testGetDate() {
        Calendar calendar = Calendar.getInstance();

        assertNull(DateUtils.cloneDate((Date) null));

        calendar.set(YEAR, MONTH, DAY);
        Date date1 = calendar.getTime();

        assertNotNull(date1);

        Date date2 = DateUtils.cloneDate(date1);

        assertEquals(date1, date2);

        assertFalse(System.identityHashCode(date1) == System.identityHashCode(date2));
    }

    /**
     * Check get default date if null
     */
    @Test
    public void testGetDefaultIfNull() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(YEAR, MONTH, DAY);
        Date date1 = calendar.getTime();

        calendar.set(YEAR, MONTH + 1, DAY);
        Date dateDefault = calendar.getTime();

        assertNotNull(date1);

        Date date2 = ObjectUtils.defaultIfNull(date1, dateDefault);
        assertEquals(date1, date2);

        date2 = ObjectUtils.defaultIfNull(null, dateDefault);
        assertEquals(dateDefault, date2);

        date2 = ObjectUtils.defaultIfNull(null, (Date) null);
        assertNull(date2);
    }

    /**
     * Check get default date if empty
     */
    @Test
    public void testGetDefaultIfEmpty() {
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        calendar.set(YEAR, MONTH, DAY, 0, 0, 0);
        Date date1 = calendar.getTime();

        calendar.set(YEAR, MONTH + 1, DAY);
        Date dateDefault = calendar.getTime();

        assertNotNull(date1);

        Date date2 = DateUtils.defaultIfEmpty(DAY + "/" + (MONTH + 1) + "/" + YEAR, df, dateDefault);
        assertEquals(date1.toString(), date2.toString());

        date2 = DateUtils.defaultIfEmpty("", df, dateDefault);
        assertEquals(dateDefault.toString(), date2.toString());

        date2 = DateUtils.defaultIfEmpty(null, df, dateDefault);
        assertEquals(dateDefault.toString(), date2.toString());

        date2 = DateUtils.defaultIfEmpty(DAY + "/" + (MONTH + 1) + "/" + YEAR, null, dateDefault);
        assertEquals(dateDefault.toString(), date2.toString());

        date2 = DateUtils.defaultIfEmpty("UNPARSEABLE", df, dateDefault);
        assertEquals(dateDefault.toString(), date2.toString());
    }

    /**
     * Check get null date if empty
     */
    @Test
    public void testGetNullIfEmpty() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date date2 = DateUtils.nullIfEmpty(DAY + "/" + (MONTH + 1) + "/" + YEAR, df);
        assertEquals(date2.toString(), date2.toString());

        date2 = DateUtils.nullIfEmpty(null, df);
        assertNull(date2);

        date2 = DateUtils.nullIfEmpty("", df);
        assertNull(date2);

        date2 = DateUtils.nullIfEmpty("UNPARSEABLE", df);
        assertNull(date2);
    }

    /**
     * Check get calendar
     */
    @Test
    public void testGetCalendar() {
        final Calendar calendar = Calendar.getInstance();
        final Date date = calendar.getTime();

        assertEquals(calendar, DateUtils.getCalendar(date));
        assertNull(DateUtils.getCalendar((Date) null));
    }

    /**
     * Check constant
     */
    @Test
    public void testConstant() {
        assertEquals(1000L * 60 * 60 * 24 * 7, DateUtils.MILLIS_PER_WEEK);
        assertEquals(1000L * 60 * 60 * 24 * 365, DateUtils.MILLIS_PER_YEAR);
        assertEquals(1000L * 60 * 60 * 24 * 366, DateUtils.MILLIS_PER_YEAR_LEAP);

        assertEquals(60, DateUtils.SECONDS_PER_MINUTE);
        assertEquals(60 * 60, DateUtils.SECONDS_PER_HOUR);
        assertEquals(60 * 60 * 24, DateUtils.SECONDS_PER_DAY);
        assertEquals(60 * 60 * 24 * 7, DateUtils.SECONDS_PER_WEEK);
        assertEquals(60 * 60 * 24 * 365, DateUtils.SECONDS_PER_YEAR);
        assertEquals(60 * 60 * 24 * 366, DateUtils.SECONDS_PER_YEAR_LEAP);

        assertEquals(60, DateUtils.MINUTES_PER_HOUR);
        assertEquals(60 * 24, DateUtils.MINUTES_PER_DAY);
        assertEquals(60 * 24 * 7, DateUtils.MINUTES_PER_WEEK);
        assertEquals(60 * 24 * 365, DateUtils.MINUTES_PER_YEAR);
        assertEquals(60 * 24 * 366, DateUtils.MINUTES_PER_YEAR_LEAP);

        assertEquals(24, DateUtils.HOURS_PER_DAY);
        assertEquals(24 * 7, DateUtils.HOURS_PER_WEEK);
        assertEquals(24 * 365, DateUtils.HOURS_PER_YEAR);
        assertEquals(24 * 366, DateUtils.HOURS_PER_YEAR_LEAP);

        assertEquals(7, DateUtils.DAYS_PER_WEEK);
        assertEquals(365, DateUtils.DAYS_PER_YEAR);
        assertEquals(366, DateUtils.DAYS_PER_YEAR_LEAP);
    }

    /**
     * Check between
     */
    @Test
    public void testBetween() {
        final int diffDays = 20;
        final int hour1 = 11;
        final int hour2 = 23;
        final int diffHours = hour2 - hour1;
        final Calendar calendar1 = new GregorianCalendar(YEAR, MONTH, DAY, hour1, 0, 0);
        final Calendar calendar2 = new GregorianCalendar(YEAR, MONTH, DAY + diffDays, hour2, 0, 0);
        final Date date1 = calendar1.getTime();
        final Date date2 = calendar2.getTime();

        assertEquals(diffDays, DateUtils.between(date1, date2, TimeUnit.DAYS));
        assertEquals(0, DateUtils.between(date1, date1, TimeUnit.DAYS));
        assertEquals(-diffDays, DateUtils.between(date2, date1, TimeUnit.DAYS));

        assertEquals(diffDays * DateUtils.HOURS_PER_DAY + diffHours, DateUtils.between(date1, date2, TimeUnit.HOURS));
        assertEquals(0, DateUtils.between(date1, date1, TimeUnit.HOURS));
        assertEquals(-diffDays * DateUtils.HOURS_PER_DAY - diffHours, DateUtils.between(date2, date1, TimeUnit.HOURS));

        assertEquals(diffDays, DateUtils.between(calendar1, calendar2, TimeUnit.DAYS));
        assertEquals(0, DateUtils.between(calendar1, calendar1, TimeUnit.DAYS));
        assertEquals(-diffDays, DateUtils.between(calendar2, calendar1, TimeUnit.DAYS));
    }

    /**
     * Check between
     */
    @Test(expected = NullPointerException.class)
    public void testBetweenDate1() {
        DateUtils.between(null, new Date(), TimeUnit.DAYS);
    }

    /**
     * Check between
     */
    @Test(expected = NullPointerException.class)
    public void testBetweenDate2() {
        DateUtils.between(new Date(), null, TimeUnit.DAYS);
    }

    /**
     * Check between
     */
    @Test(expected = NullPointerException.class)
    public void testBetweenScale() {
        DateUtils.between(new Date(), new Date(), null);
    }

    /**
     * Check between
     */
    @Test(expected = NullPointerException.class)
    public void testBetweenCalendar1() {
        DateUtils.between(null, Calendar.getInstance(), TimeUnit.DAYS);
    }

    /**
     * Check between
     */
    @Test(expected = NullPointerException.class)
    public void testBetweenCalendar2() {
        DateUtils.between(Calendar.getInstance(), null, TimeUnit.DAYS);
    }

    /**
     * Check {@link DateUtils#clearSmaller(Calendar, int)}
     */
    @Test
    public void testClearSmaller() {
        Calendar calendar = new GregorianCalendar(2017, 2, 28, 6, 3, 0);

        DateUtils.clearSmaller(calendar, Calendar.YEAR);
        assertEquals(2017, calendar.get(Calendar.YEAR));
        assertEquals(0, calendar.get(Calendar.MONTH));
        assertEquals(0, calendar.get(Calendar.MINUTE));

        calendar = new GregorianCalendar(2017, 2, 28, 13, 3, 0);
        assertEquals(1, calendar.get(Calendar.AM_PM));
        DateUtils.clearSmaller(calendar, Calendar.AM_PM);
        assertEquals(2017, calendar.get(Calendar.YEAR));
        assertEquals(1, calendar.get(Calendar.AM_PM));
        assertEquals(0, calendar.get(Calendar.HOUR));

        calendar = new GregorianCalendar(2017, 2, 28, 13, 3, 0);
        DateUtils.clearSmaller(calendar, Calendar.DATE);
        assertEquals(2017, calendar.get(Calendar.YEAR));
        assertEquals(87, calendar.get(Calendar.DAY_OF_YEAR));
        assertEquals(28, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(0, calendar.get(Calendar.HOUR));

        try {
            DateUtils.clearSmaller(null, Calendar.YEAR);
            fail();
        } catch (NullPointerException e) {
            assertNotNull(e);
        }

        try {
            DateUtils.clearSmaller(calendar, Calendar.DST_OFFSET);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals("The parameter calendarField '16' is invalid", e.getMessage());
        }
    }

    /**
     * Check {@link DateUtils#clearBigger(Calendar, int)}
     */
    @Test
    public void testClearBigger() {
        final int epochYear = 1970;

        Calendar calendar = new GregorianCalendar(2017, 2, 28, 6, 3, 0);

        DateUtils.clearBigger(calendar, Calendar.YEAR);
        assertEquals(2017, calendar.get(Calendar.YEAR));
        assertEquals(2, calendar.get(Calendar.MONTH));
        assertEquals(3, calendar.get(Calendar.MINUTE));

        calendar = new GregorianCalendar(2017, 2, 28, 13, 3, 0);
        assertEquals(1, calendar.get(Calendar.AM_PM));
        DateUtils.clearBigger(calendar, Calendar.AM_PM);
        assertEquals(epochYear, calendar.get(Calendar.YEAR));
        assertEquals(1, calendar.get(Calendar.AM_PM));
        assertEquals(13, calendar.get(Calendar.HOUR_OF_DAY));

        calendar = new GregorianCalendar(2017, 2, 28, 13, 3, 0);
        DateUtils.clearBigger(calendar, Calendar.DATE);
        assertEquals(epochYear, calendar.get(Calendar.YEAR));
        assertEquals(28, calendar.get(Calendar.DAY_OF_YEAR));
        assertEquals(28, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(13, calendar.get(Calendar.HOUR_OF_DAY));

        try {
            DateUtils.clearBigger(null, Calendar.YEAR);
            fail();
        } catch (NullPointerException e) {
            assertNotNull(e);
        }

        try {
            DateUtils.clearBigger(calendar, Calendar.DST_OFFSET);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals("The parameter calendarField '16' is invalid", e.getMessage());
        }
    }

    /**
     * Check {@link DateUtils#getDate}
     */
    @Test
    public void testGetLocalTimeDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 2, 4, 16, 59, 20); // mars
        calendar.set(Calendar.MILLISECOND, 0);

        ZoneOffset offset = OffsetDateTime.now().getOffset();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
        assertEquals(calendar.getTime(), DateUtils.getDate(localDateTime));
        assertEquals(calendar, DateUtils.getCalendar(localDateTime));

        LocalDate localDate = LocalDate.of(2017, 03, 04);
        calendar.set(2017, 02, 04, 0, 0, 0);
        assertEquals(calendar.getTime(), DateUtils.getDate(localDate));
        assertEquals(calendar, DateUtils.getCalendar(localDate));

        LocalTime localTime = LocalTime.of(1, 2, 3, 0);
        calendar.set(1970, 0, 1, 1, 2, 3);
        assertEquals(calendar, DateUtils.getCalendar(localTime));
        assertEquals(calendar.getTime(), DateUtils.getDate(localTime));

        calendar.set(2017, 02, 04, 1, 2, 3);
        assertEquals(calendar, DateUtils.getCalendar(localDate, localTime));
        assertEquals(calendar.getTime(), DateUtils.getDate(localDate, localTime));

        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
        calendar.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        calendar.set(2017, 2, 4, 16, 59, 20);
        assertEquals(calendar.getTime(), DateUtils.getCalendar(offsetDateTime).getTime());
        assertEquals(calendar.getTime(), DateUtils.getDate(offsetDateTime));

        OffsetTime offsetTime = OffsetTime.of(localTime, ZoneOffset.UTC);
        calendar.set(1970, 0, 1, 1, 2, 3);
        assertEquals(calendar.getTime(), DateUtils.getCalendar(offsetTime).getTime());
        assertEquals(calendar.getTime(), DateUtils.getDate(offsetTime));

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
        calendar.set(2017, 2, 4, 16, 59, 20);
        assertEquals(calendar.getTime(), DateUtils.getCalendar(zonedDateTime).getTime());
        assertEquals(calendar.getTime(), DateUtils.getDate(zonedDateTime));

        localDateTime = LocalDateTime.ofInstant(calendar.toInstant(), ZoneOffset.UTC);
        assertEquals(localDateTime, DateUtils.getLocalDateTime(calendar));
        assertEquals(localDateTime, DateUtils.getLocalDateTime(calendar.getTime(), ZoneOffset.UTC));
        if (ZoneOffset.UTC.equals(offset)) {
            assertEquals(localDateTime, DateUtils.getLocalDateTime(calendar.getTime(), null));
        } else {
            assertNotEquals(localDateTime, DateUtils.getLocalDateTime(calendar.getTime(), null));
        }

        // calendar.getTime() -> Calendar (UTC) => milliseconds => Date =>
        // Calendar (current time zone)
        Calendar calendar2 = Calendar.getInstance();
        localDateTime = LocalDateTime.ofInstant(calendar2.toInstant(), offset);
        assertEquals(localDateTime, DateUtils.getLocalDateTime(calendar2.getTime()));

        assertEquals(localDate, DateUtils.getLocalDate(calendar));
        assertEquals(localDate, DateUtils.getLocalDate(calendar.getTime()));
        assertEquals(localDate, DateUtils.getLocalDate(calendar.getTime(), ZoneId.systemDefault()));
        assertEquals(localDate, DateUtils.getLocalDate(calendar.getTime(), null));

        calendar.setTimeZone(TimeZone.getDefault());
        calendar.set(2017, 2, 4, 1, 2, 3);
        assertEquals(localTime, DateUtils.getLocalTime(calendar));
        assertEquals(localTime, DateUtils.getLocalTime(calendar.getTime()));
        assertEquals(localTime, DateUtils.getLocalTime(calendar.getTime(), ZoneId.systemDefault()));
        assertEquals(localTime, DateUtils.getLocalTime(calendar.getTime(), null));
    }

    /**
     * Check {@link DateUtils#getDate}
     */
    @Test
    public void testGetOffsetTimeDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 2, 4, 16, 59, 20); // mars
        calendar.set(Calendar.MILLISECOND, 0);

        ZoneOffset offset = OffsetDateTime.now().getOffset();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
        LocalTime localTime = LocalTime.of(1, 2, 3, 0);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
        OffsetTime offsetTime = OffsetTime.of(localTime, ZoneOffset.UTC);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
        calendar.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        calendar.set(2017, 2, 4, 16, 59, 20);
        localDateTime = LocalDateTime.ofInstant(calendar.toInstant(), ZoneOffset.UTC);

        // calendar.getTime() -> Calendar (UTC) => milliseconds => Date =>
        // Calendar (current time zone)
        Calendar calendar2 = Calendar.getInstance();
        localDateTime = LocalDateTime.ofInstant(calendar2.toInstant(), offset);

        calendar.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        calendar.set(2017, 2, 4, 16, 59, 20);
        offsetDateTime = OffsetDateTime.ofInstant(offsetDateTime.toInstant(), ZoneOffset.UTC);
        assertEquals(offsetDateTime, DateUtils.getOffsetDateTime(calendar));
        assertEquals(offsetDateTime, DateUtils.getOffsetDateTime(calendar.getTime(), ZoneOffset.UTC));
        if (ZoneOffset.UTC.equals(offset)) {
            assertEquals(offsetDateTime, DateUtils.getOffsetDateTime(calendar.getTime(), null));
        } else {
            assertNotEquals(offsetDateTime, DateUtils.getOffsetDateTime(calendar.getTime(), null));
        }

        // calendar.getTime() -> Calendar (UTC) => milliseconds => Date =>
        // Calendar (current time zone)
        offsetDateTime = OffsetDateTime.of(localDateTime, offset);
        assertEquals(offsetDateTime, DateUtils.getOffsetDateTime(calendar2.getTime()));

        calendar.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        offset = ZoneOffset.ofTotalSeconds(calendar.get(Calendar.ZONE_OFFSET) / (int) DateUtils.MILLIS_PER_SECOND);

        calendar.set(2017, 2, 4, 1, 2, 3);
        localTime = LocalTime.of(1, 2, 3, 0);
        offsetTime = OffsetTime.of(localTime, offset);
        assertEquals(offsetTime, DateUtils.getOffsetTime(calendar));
        assertEquals(offsetTime, DateUtils.getOffsetTime(calendar.getTime()));
        assertEquals(offsetTime, DateUtils.getOffsetTime(calendar.getTime(), ZoneId.systemDefault()));
        assertEquals(offsetTime, DateUtils.getOffsetTime(calendar.getTime(), null));

        localDateTime = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
        zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        assertEquals(zonedDateTime, DateUtils.getZonedDateTime(calendar));
        assertEquals(zonedDateTime, DateUtils.getZonedDateTime(calendar.getTime()));
        assertEquals(zonedDateTime, DateUtils.getZonedDateTime(calendar.getTime(), ZoneId.systemDefault()));
        assertEquals(zonedDateTime, DateUtils.getZonedDateTime(calendar.getTime(), null));
    }

    /**
     * Check {@link DateUtils#getInstant}, {@link DateUtils#getDate(Instant)}
     * and {@link DateUtils#getCalendar(Instant)}
     */
    @Test
    public void testInstant() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2017, 2, 4, 16, 59, 20); // mars
        calendar1.set(Calendar.MILLISECOND, 0);

        Instant instant1 = DateUtils.getInstant(calendar1);

        assertEquals(calendar1.getTimeInMillis(), instant1.getEpochSecond() * DateUtils.MILLIS_PER_SECOND);

        instant1 = DateUtils.getInstant(calendar1.getTime());

        assertEquals(calendar1.getTimeInMillis(), instant1.getEpochSecond() * DateUtils.MILLIS_PER_SECOND);

        Date date1 = DateUtils.getDate(instant1);

        assertEquals(date1.getTime(), instant1.getEpochSecond() * DateUtils.MILLIS_PER_SECOND);

        Calendar calendar2 = DateUtils.getCalendar(instant1);

        assertEquals(calendar2.getTimeInMillis(), instant1.getEpochSecond() * DateUtils.MILLIS_PER_SECOND);
    }

    /**
     * Check {@link DateUtils#max}
     */
    @Test
    public void testMax() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2017, 2, 4, 16, 59, 20); // mars
        calendar1.set(Calendar.MILLISECOND, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2017, 2, 4, 16, 59, 20); // mars
        calendar2.set(Calendar.MILLISECOND, 1);

        Calendar now = Calendar.getInstance();

        assertEquals(now, DateUtils.max(calendar1, now, calendar2));
        assertEquals(now.getTime(), DateUtils.max(calendar1.getTime(), now.getTime(), calendar2.getTime()));
        assertEquals(DateUtils.getZonedDateTime(now), DateUtils.max(DateUtils.getZonedDateTime(calendar1),
                DateUtils.getZonedDateTime(now.getTime()), DateUtils.getZonedDateTime(calendar2.getTime())));

        assertEquals(now, DateUtils.max(now));
        assertEquals(now, DateUtils.max(null, now));
        assertEquals(now, DateUtils.max(now, null));
        assertEquals(now, DateUtils.max(null, now, null));

        assertEquals(now.getTime(), DateUtils.max(now.getTime()));
        assertEquals(now.getTime(), DateUtils.max(null, now.getTime()));
        assertEquals(now.getTime(), DateUtils.max(now.getTime(), null));
        assertEquals(now.getTime(), DateUtils.max(null, now.getTime(), null));

        assertEquals(DateUtils.getZonedDateTime(now), DateUtils.max(DateUtils.getZonedDateTime(now)));
        assertEquals(DateUtils.getZonedDateTime(now), DateUtils.max(null, DateUtils.getZonedDateTime(now)));
        assertEquals(DateUtils.getZonedDateTime(now), DateUtils.max(DateUtils.getZonedDateTime(now), null));
        assertEquals(DateUtils.getZonedDateTime(now), DateUtils.max(null, DateUtils.getZonedDateTime(now), null));

        assertNull(DateUtils.max((Calendar) null));
        assertNull(DateUtils.max((Date) null));
        assertNull(DateUtils.max((ZonedDateTime) null));

        assertException(() -> DateUtils.max((Calendar[]) null), NullPointerException.class, "Dates array cannot be null");
        assertException(() -> DateUtils.max((Date[]) null), NullPointerException.class, "Dates array cannot be null");
        assertException(() -> DateUtils.max((ZonedDateTime[]) null), NullPointerException.class, "Dates array cannot be null");

        assertException(() -> DateUtils.max(new Calendar[0]), IllegalArgumentException.class, "Dates array cannot be empty");
        assertException(() -> DateUtils.max(new Date[0]), IllegalArgumentException.class, "Dates array cannot be empty");
        assertException(() -> DateUtils.max(new ZonedDateTime[0]), IllegalArgumentException.class, "Dates array cannot be empty");
    }

    /**
     * Check {@link DateUtils#min}
     */
    @Test
    public void testMin() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2017, 2, 4, 16, 59, 20); // mars
        calendar1.set(Calendar.MILLISECOND, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2017, 2, 4, 16, 59, 20); // mars
        calendar2.set(Calendar.MILLISECOND, 1);

        Calendar now = Calendar.getInstance();

        assertEquals(calendar1, DateUtils.min(calendar1, now, calendar2));
        assertEquals(calendar1.getTime(), DateUtils.min(calendar1.getTime(), now.getTime(), calendar2.getTime()));
        assertEquals(DateUtils.getZonedDateTime(calendar1), DateUtils.min(DateUtils.getZonedDateTime(calendar1),
                DateUtils.getZonedDateTime(now.getTime()), DateUtils.getZonedDateTime(calendar2.getTime())));

        assertEquals(now, DateUtils.min(now));
        assertEquals(now, DateUtils.min(null, now));
        assertEquals(now, DateUtils.min(now, null));
        assertEquals(calendar1, DateUtils.min(null, now, null, calendar1, null));

        assertEquals(now.getTime(), DateUtils.min(now.getTime()));
        assertEquals(now.getTime(), DateUtils.min(null, now).getTime());
        assertEquals(now.getTime(), DateUtils.min(now.getTime(), null));
        assertEquals(calendar1.getTime(), DateUtils.min(null, now.getTime(), null, calendar1.getTime(), null));

        assertEquals(DateUtils.getZonedDateTime(now), DateUtils.min(DateUtils.getZonedDateTime(now)));
        assertEquals(DateUtils.getZonedDateTime(now), DateUtils.min(null, DateUtils.getZonedDateTime(now)));
        assertEquals(DateUtils.getZonedDateTime(now), DateUtils.min(DateUtils.getZonedDateTime(now), null));
        assertEquals(DateUtils.getZonedDateTime(now), DateUtils.min(null, DateUtils.getZonedDateTime(now), null));
        assertEquals(DateUtils.getZonedDateTime(calendar1),
                DateUtils.min(null, DateUtils.getZonedDateTime(now), null, DateUtils.getZonedDateTime(calendar1), null));

        assertNull(DateUtils.min((Calendar) null));
        assertNull(DateUtils.min((Date) null));
        assertNull(DateUtils.min((ZonedDateTime) null));

        assertException(() -> DateUtils.min((Calendar[]) null), NullPointerException.class, "Dates array cannot be null");
        assertException(() -> DateUtils.min((Date[]) null), NullPointerException.class, "Dates array cannot be null");
        assertException(() -> DateUtils.min((ZonedDateTime[]) null), NullPointerException.class, "Dates array cannot be null");

        assertException(() -> DateUtils.min(new Calendar[0]), IllegalArgumentException.class, "Dates array cannot be empty");
        assertException(() -> DateUtils.min(new Date[0]), IllegalArgumentException.class, "Dates array cannot be empty");
        assertException(() -> DateUtils.min(new ZonedDateTime[0]), IllegalArgumentException.class, "Dates array cannot be empty");
    }
}
