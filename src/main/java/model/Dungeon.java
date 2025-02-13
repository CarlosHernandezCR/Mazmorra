package model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@XmlRootElement
public class Dungeon {
    private List<Room> rooms;
    public Dungeon() {
        this.rooms = new ArrayList<>(); // Inicializa la lista aquí
    }
}
