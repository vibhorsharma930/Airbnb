package com.bnb.service;

import com.bnb.entity.AppUser;
import com.bnb.entity.Booking;
import com.bnb.entity.Property;
import com.bnb.entity.Room;
import com.bnb.repositery.BookingRepository;
import com.bnb.repositery.PropertyRepository;
import com.bnb.repositery.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookingService {

   private BookingRepository bookingRepository;
   private RoomRepository roomRepository;
   private PropertyRepository propertyRepository;

    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository, PropertyRepository propertyRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;
    }


    public Boolean bookingConfirmation(Booking booking, AppUser appUser){
        Property property = propertyRepository.findById(booking.getProperty().getId()).get();

        List<Room> roomsByType = roomRepository.findRoomsByType(booking.getRoomType());
        System.out.println(roomsByType);


        LocalDate checkInDate = booking.getCheckInDate();
        LocalDate checkOutDate = booking.getCheckOutDate();

        String roomType = booking.getRoomType();

        List<Room> roomList=new ArrayList<>();



        List<LocalDate> bookingdates= this.calculateBtdates(checkInDate,checkOutDate);

        for (LocalDate date: bookingdates){
            Room byBookingDateAndPropertyAndRoomType = roomRepository.findBYBookingDateAndPropertyAndRoomType(property,booking.getRoomType(),date);

            if (byBookingDateAndPropertyAndRoomType.getAvailability()==0){

                return false;

            }
            roomList.add(byBookingDateAndPropertyAndRoomType);

        }

            booking.setAppUser(appUser);
        Booking booking1 = bookingRepository.save(booking);

        for (Room room : roomList){
            room.setAvailability(room.getAvailability()-1);

            roomRepository.save(room);

        }


        return true;

    }

    private List<LocalDate> calculateBtdates(LocalDate checkInDate, LocalDate checkOutDate) {

        List<LocalDate> dates =new ArrayList<>();

           while(checkInDate.isBefore(checkOutDate)){

               dates.add(checkInDate);

               checkInDate=checkInDate.plusDays(1);


           }

        return dates;
    }
}
