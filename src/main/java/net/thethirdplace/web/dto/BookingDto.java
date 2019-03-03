package net.thethirdplace.web.dto;

import lombok.Data;
import net.thethirdplace.web.domain.Booking;

import java.time.format.DateTimeFormatter;

@Data
public class BookingDto {

    String username;
    String day;
    String time;

    public BookingDto(Booking booking) {
        this.username = booking.getUsername();
        this.day  = booking.getStart().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.time = booking.getStart().toLocalTime().toString() + "-" + booking.getEnd().toLocalTime().toString();
    }
}


