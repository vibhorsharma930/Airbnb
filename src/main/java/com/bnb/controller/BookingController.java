package com.bnb.controller;

import com.bnb.entity.AppUser;
import com.bnb.entity.Booking;
import com.bnb.entity.Property;
import com.bnb.entity.Room;
import com.bnb.repositery.PropertyRepository;
import com.bnb.repositery.RoomRepository;
import com.bnb.service.BookingService;
import com.bnb.service.PdfService;
import com.bnb.service.SMSService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

       private PdfService pdfService;
    private BookingService bookingService;
    private SMSService smsService;

    public BookingController(BookingService bookingService, PropertyRepository propertyRepository, PdfService pdfService, SMSService smsService) {
        this.bookingService = bookingService;
        this.pdfService = pdfService;
        this.smsService = smsService;
    }

    @GetMapping("/create_booking")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking,
                                           @AuthenticationPrincipal  AppUser AppUser
                                            ,@RequestParam String to
    ) throws IOException {



        Boolean bookingStatus = bookingService.bookingConfirmation(booking,AppUser);

        if (bookingStatus==true){

            String msgBody="Dear vibhor sharma ! Thank you for booking with Laalbhag Hotel! Your reservation is confirmed";

//        smsService.sendMsg(to,msgBody);

            ByteArrayInputStream bis = pdfService.customerPDFReport();
            byte[] bytes = bis.readAllBytes();
            System.out.println(bytes.length);
            File file =new File("D:\\test\\B.pdf");
            FileOutputStream fileOutputStream =new FileOutputStream(file);
            fileOutputStream.write(bytes);

            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition", "inline; filename=customers.pdf");
          headers.add("Content-Disposition", "attachment; filename=customers.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));


        }
        return new ResponseEntity<>(bookingStatus, HttpStatus.OK);

    }
}

