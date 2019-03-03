package net.thethirdplace.web.api;

import lombok.extern.slf4j.Slf4j;
import net.thethirdplace.web.domain.Booking;
import net.thethirdplace.web.dto.BookingDto;
import net.thethirdplace.web.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/booking")
public class BookingApi {
    @Autowired BookingService bookingService;

    /**
     * TODO : Localdate 로 받는 방법 알아보자?!
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/list")
    public List<BookingDto> list(String start,
                                 String end){
        return bookingService.find(start, end);
    }

    @PostMapping("/new")
    public BookingDto newBooking(@RequestBody Booking booking){
        log.info("new booking : {}", booking);
        // TODO : validation
        final Booking save = bookingService.save(booking);
        return new BookingDto(save);
    }

    @GetMapping("/test")
    public Booking test(Booking booking){
        return booking;
    }

}
