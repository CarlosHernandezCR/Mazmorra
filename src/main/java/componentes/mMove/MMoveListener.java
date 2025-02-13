package componentes.mMove;

import model.Room;

import java.util.EventListener;

public interface MMoveListener extends EventListener {
    void roomUpdated(Room room);
}
