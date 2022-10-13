package com.grupo2.desafioquality.dto;

import com.grupo2.desafioquality.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomDto {
    private String name;
    private double area;

    public static RoomDto fromRoom(Room room, double area) {
        return new RoomDto(
                room.getName(),
                area
        );
    }
}
