package com.bnb.repositery;

import com.bnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.Optional;


public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByDate(LocalDate localDate);
}