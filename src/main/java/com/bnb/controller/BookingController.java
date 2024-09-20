package com.bnb.controller;

import com.bnb.entity.Booking;
import com.bnb.entity.Room;
import com.bnb.repositery.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {


   private  RoomRepository roomRepository;

    public BookingController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    @PostMapping("/create_booking")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {


        LocalDate checkInDate = booking.getCheckInDate();
        LocalDate checkOutDate = booking.getCheckOutDate();

        List<LocalDate> localDates = this.checkDate(checkInDate, checkOutDate);
        for (LocalDate localDate : localDates) {
            Room room = roomRepository.findByDate(localDate).get();
            if (room.getAvailability() == 0) {
                return new ResponseEntity<>("room not avaliable ", HttpStatus.INTERNAL_SERVER_ERROR);

            }
            //booking saving concept

            Integer roomAvailability = room.getAvailability();
            room.setAvailability(roomAvailability - 1);
            roomRepository.save(room);


        }

            return new ResponseEntity<>("everthing fine",HttpStatus.OK);
    }

    private List<LocalDate> checkDate(LocalDate checkInDate, LocalDate checkOutDate) {
             List<LocalDate> localDates = new ArrayList<>();
        while (!checkInDate.isAfter(checkOutDate)){

            localDates.add(checkInDate);
           checkInDate= checkInDate.plusDays(1);
        }

        return localDates;
    }


}
