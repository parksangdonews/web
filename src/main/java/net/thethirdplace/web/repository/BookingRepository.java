package net.thethirdplace.web.repository;

import net.thethirdplace.web.domain.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {

    

}
