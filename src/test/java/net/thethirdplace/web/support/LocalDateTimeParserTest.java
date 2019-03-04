package net.thethirdplace.web.support;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Locale;

public class LocalDateTimeParserTest {

    LocalDateTimeParser parser;

    @Before
    public void setup(){
        parser = new LocalDateTimeParser();
    }

    @Test
    public void name() throws Exception{
        String a = "2019-03-02 20:21";
        final LocalDateTime parse = parser.parse(a, Locale.KOREA);
        System.out.println(parse);
    }
}