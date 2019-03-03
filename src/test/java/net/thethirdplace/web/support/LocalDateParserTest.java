package net.thethirdplace.web.support;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Locale;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class LocalDateParserTest {

    LocalDateParser parser;

    @Before
    public void setup(){
        parser = new LocalDateParser();
    }

    @Test
    public void parse() {
        String a = "2019-03-02";
        final LocalDate parse = parser.parse(a, Locale.KOREA);
        assertThat(parse).isNotNull();
    }
}