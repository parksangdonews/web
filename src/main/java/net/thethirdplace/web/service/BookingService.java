package net.thethirdplace.web.service;

import net.thethirdplace.web.domain.Booking;
import net.thethirdplace.web.dto.BookingDto;
import net.thethirdplace.web.dto.SlackMsg;
import net.thethirdplace.web.repository.BookingRepository;
import net.thethirdplace.web.support.SlackSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@Transactional
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    SlackSender slackSender;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Booking save(Booking booking){
        slackSender.send("사용자 : " + booking.getUsername() + ", 시작 날짜 : " + booking.getStart() + ", 종료일시 : " + booking.getEnd() + ", 인원수 : " + booking.getPeople());
        return bookingRepository.save(booking);
    }

    public List<BookingDto> find(String start, String end) {
        Assert.isTrue(start!=null, "Start Must has value");
        Assert.isTrue(end!=null, "End Must has value");

        LocalDateTime startTime = LocalDate.parse(start, formatter).atTime(0,0);
        LocalDateTime endTime = LocalDate.parse(end, formatter).atTime(23, 59);
        return mongoTemplate.find(Query.query(where("start").gt(startTime).and("end").lt(endTime)), Booking.class)
                .stream().map(BookingDto::new).collect(Collectors.toList());
    }

    @PostConstruct
    public void setup(){
        /*mongoTemplate.dropCollection(Booking.class);
        mongoTemplate.createCollection(Booking.class);*/
    }
}
