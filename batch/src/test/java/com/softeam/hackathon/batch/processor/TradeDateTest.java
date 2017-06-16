package com.softeam.hackathon.batch.processor;

import com.softeam.hackathon.batch.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Created by hackathon6 on 16/06/2017.
 */
public class TradeDateTest {

    @Test
    public void testTradeDate() {
        Assert.assertEquals("1st January 2017", DateUtils.transformDateToStringMail("01/01/2017"));
        Assert.assertEquals("2nd February 2017", DateUtils.transformDateToStringMail("02/02/2017"));
        Assert.assertEquals("3rd March 2017", DateUtils.transformDateToStringMail("03/03/2017"));
        Assert.assertEquals("4th April 2017", DateUtils.transformDateToStringMail("04/04/2017"));
        Assert.assertEquals("10th May 2017", DateUtils.transformDateToStringMail("10/05/2017"));
        Assert.assertEquals("12th June 2017", DateUtils.transformDateToStringMail("12/06/2017"));
        Assert.assertEquals("15th July 2017", DateUtils.transformDateToStringMail("15/07/2017"));
        Assert.assertEquals("19th August 2017", DateUtils.transformDateToStringMail("19/08/2017"));
        Assert.assertEquals("20th September 2017", DateUtils.transformDateToStringMail("20/09/2017"));
        Assert.assertEquals("25th October 2017", DateUtils.transformDateToStringMail("25/10/2017"));
        Assert.assertEquals("30th November 2017", DateUtils.transformDateToStringMail("30/11/2017"));
        Assert.assertEquals("31st December 2017", DateUtils.transformDateToStringMail("31/12/2017"));

        // Transformation 2

        Assert.assertEquals(DateUtils.transformStringMailToDate("1st January 2017"), LocalDate.parse("01/01/2017", DateUtils.LOCAL_DATE_FORMAT));
        Assert.assertEquals(DateUtils.transformStringMailToDate("2nd February 2017"), LocalDate.parse("02/02/2017", DateUtils.LOCAL_DATE_FORMAT));
        Assert.assertEquals(DateUtils.transformStringMailToDate("3rd March 2017"), LocalDate.parse("03/03/2017", DateUtils.LOCAL_DATE_FORMAT));
        Assert.assertEquals(DateUtils.transformStringMailToDate("4th April 2017"), LocalDate.parse("04/04/2017", DateUtils.LOCAL_DATE_FORMAT));
        Assert.assertEquals(DateUtils.transformStringMailToDate("10th May 2017"), LocalDate.parse("10/05/2017", DateUtils.LOCAL_DATE_FORMAT));
        Assert.assertEquals(DateUtils.transformStringMailToDate("12th June 2017"), LocalDate.parse("12/06/2017", DateUtils.LOCAL_DATE_FORMAT));
        Assert.assertEquals(DateUtils.transformStringMailToDate("15th July 2017"), LocalDate.parse("15/07/2017", DateUtils.LOCAL_DATE_FORMAT));
        Assert.assertEquals(DateUtils.transformStringMailToDate("19th August 2017"), LocalDate.parse("19/08/2017", DateUtils.LOCAL_DATE_FORMAT));
        Assert.assertEquals(DateUtils.transformStringMailToDate("20th September 2017"), LocalDate.parse("20/09/2017", DateUtils.LOCAL_DATE_FORMAT));
        Assert.assertEquals(DateUtils.transformStringMailToDate("25th October 2017"), LocalDate.parse("25/10/2017", DateUtils.LOCAL_DATE_FORMAT));
        Assert.assertEquals(DateUtils.transformStringMailToDate("30th November 2017"), LocalDate.parse("30/11/2017", DateUtils.LOCAL_DATE_FORMAT));
        Assert.assertEquals(DateUtils.transformStringMailToDate("31st December 2017"), LocalDate.parse("31/12/2017", DateUtils.LOCAL_DATE_FORMAT));

    }


}
