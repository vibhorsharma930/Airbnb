package com.bnb.repositery;

import com.bnb.entity.Property;
import com.bnb.entity.Room;
import jakarta.transaction.Synchronization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface RoomRepository extends JpaRepository<Room, Serializable> {
    @Query("SELECT r FROM Room r WHERE  r.property = :propertyId AND r.type=:type AND r.date=:date")
    Room findBYBookingDateAndPropertyAndRoomType(@Param("propertyId") Property property,@Param("type") String type,@Param("date") LocalDate date);

    @Query("SELECT r FROM Room r WHERE r.type =:type")
    List<Room> findRoomsByType(@Param("type") String s);

}
