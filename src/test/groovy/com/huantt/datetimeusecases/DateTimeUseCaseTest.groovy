package com.huantt.datetimeusecases

import groovy.transform.CompileStatic
import org.junit.Test
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

/**
 * @author huantt on 2019-05-30
 */
@CompileStatic
class DateTimeUseCaseTest {

    final Date DATE_20190101 = new Date(119, 00, 01)
    final Date DATE_20190130 = new Date(119, 00, 30)
    final Date DATE_20190131 = new Date(119, 00, 31)
    final Date DATE_20190201 = new Date(119, 01, 01)
    final Date DATE_20190228 = new Date(119, 01, 28)

    @Test
    public void convertTimeUnit() {
        assert TimeUnit.DAYS.toHours(1) == 24
        assert TimeUnit.HOURS.toMinutes(1) == 60
        assert TimeUnit.MINUTES.toSeconds(1) == 60
        assert TimeUnit.MINUTES.toMillis(1) == 60000
    }

    @Test
    public void parseDateToStringFormat() {
        // Groovy way
        assert DATE_20190130.format("yyyyMMdd") == "20190130"
        assert DATE_20190130.format("yyyy-MM-dd hh:mm:ss aaa") == "2019-01-30 12:00:00 AM"

        // Java way
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd")
        assert dateFormat.format(DATE_20190130) == "20190130"

        dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aaa")
        assert dateFormat.format(DATE_20190130) == "2019-01-30 12:00:00 AM"
    }

    @Test
    void parseStringToDate() {
        // Groovy way
        assert new Date().parse("yyyy-MM-dd hh:mm:ss aaa", "2019-01-30 12:00:00 AM") == DATE_20190130

        //Java way
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aaa")
        dateFormat.parse("2019-01-30 12:00:00 AM") == DATE_20190130
    }

    @Test
    void addDate() {
        Calendar calendar = DATE_20190130.toCalendar()
        calendar.add(Calendar.DATE, 1)
        assert calendar.getTime() == DATE_20190131

        calendar.add(Calendar.DATE, 1)
        assert calendar.getTime() == DATE_20190201

        calendar.add(Calendar.DATE, -1)
        assert calendar.getTime() == DATE_20190131
    }

    @Test
    void subtractDate() {
        Calendar calendar_20190130 = DATE_20190130.toCalendar()
        Calendar calendar_20190201 = DATE_20190201.toCalendar()

        // Groovy way
        assert calendar_20190201 - calendar_20190130 == 2

        // Java way
        assert calendar_20190201.minus(calendar_20190130) == 2

    }

    @Test
    void getFirstDateOfMonth() {
        Calendar calendar = DATE_20190201.toCalendar()

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        assert calendar.getTime() == DATE_20190228

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimum(Calendar.DAY_OF_MONTH))
        assert calendar.getTime() == DATE_20190201
    }

    @Test
    void getFirstDateOfYear(){
        Calendar calendar = DATE_20190201.toCalendar()
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getMinimum(Calendar.DAY_OF_YEAR))
        assert calendar.getTime() == DATE_20190101
    }
}
