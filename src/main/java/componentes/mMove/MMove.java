package componentes.mMove;

import model.Room;

import javax.swing.*;
import java.awt.*;

import static common.Constantes.*;

public class MMove extends JPanel implements MMoveInt {
    private java.util.List<Room> rooms;
    private MMoveListener listener;

    private JButton north;
    private JButton south;
    private JButton weast;
    private JButton east;

    private JLabel actualRoomDescription;

    public MMove(MMoveListener listener) {
        this.listener = listener;
        initComponente();
    }

    private void initComponente() {
        actualRoomDescription = new JLabel(COMENZAR);
        actualRoomDescription.setHorizontalAlignment(SwingConstants.CENTER);
        actualRoomDescription.setVerticalAlignment(SwingConstants.CENTER);

        north = new JButton(NORTE);
        north.setEnabled(false);
        south = new JButton(SUR);
        south.setEnabled(false);
        weast = new JButton(OESTE);
        weast.setEnabled(false);
        east = new JButton(ESTE);
        east.setEnabled(false);

        setLayout(new BorderLayout());
        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
        add(weast, BorderLayout.WEST);
        add(east, BorderLayout.EAST);
        add(actualRoomDescription, BorderLayout.CENTER);
    }

    @Override
    public void setRooms(java.util.List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void loadRoom(Room room) {
        north.setEnabled(false);
        south.setEnabled(false);
        weast.setEnabled(false);
        east.setEnabled(false);

        actualRoomDescription.setText(room.getDescription());

        room.getDoors().forEach(door -> {
            if (door.getName().equalsIgnoreCase(NORTE)) {
                north.setEnabled(true);
                north.removeActionListener(north.getActionListeners().length > 0 ? north.getActionListeners()[0] : null);
                north.addActionListener(e -> {
                    Room nextRoom = rooms.stream()
                            .filter(r -> r.getId()
                                    .equalsIgnoreCase(room.getDoors().stream().filter(d -> d.getName().equalsIgnoreCase(NORTE))
                                            .findFirst().get()
                                            .getDest())
                            ).findFirst().get();
                    listener.roomUpdated(nextRoom);
                    loadRoom(nextRoom);
                });
            } else if (door.getName().equalsIgnoreCase(SUR)) {
                south.setEnabled(true);
                south.removeActionListener(south.getActionListeners().length > 0 ? south.getActionListeners()[0] : null);
                south.addActionListener(e -> {
                    Room nextRoom = rooms.stream()
                            .filter(r -> r.getId()
                                    .equalsIgnoreCase(room.getDoors().stream().filter(d -> d.getName().equalsIgnoreCase(SUR))
                                            .findFirst().get()
                                            .getDest())
                            ).findFirst().get();
                    listener.roomUpdated(nextRoom);
                    loadRoom(nextRoom);
                });
            } else if (door.getName().equalsIgnoreCase(OESTE)) {
                weast.setEnabled(true);
                weast.removeActionListener(weast.getActionListeners().length > 0 ? weast.getActionListeners()[0] : null);
                weast.addActionListener(e -> {
                    Room nextRoom = rooms.stream()
                            .filter(r -> r.getId()
                                    .equalsIgnoreCase(room.getDoors().stream().filter(d -> d.getName().equalsIgnoreCase(OESTE))
                                            .findFirst().get()
                                            .getDest())
                            ).findFirst().get();
                    listener.roomUpdated(nextRoom);
                    loadRoom(nextRoom);
                });
            } else if (door.getName().equalsIgnoreCase(ESTE)) {
                east.setEnabled(true);
                east.removeActionListener(east.getActionListeners().length > 0 ? east.getActionListeners()[0] : null);
                east.addActionListener(e -> {
                    Room nextRoom = rooms.stream()
                            .filter(r -> r.getId()
                                    .equalsIgnoreCase(room.getDoors().stream().filter(d -> d.getName().equalsIgnoreCase(ESTE))
                                            .findFirst().get()
                                            .getDest())
                            ).findFirst().get();
                    listener.roomUpdated(nextRoom);
                    loadRoom(nextRoom);
                });
            }
        });
    }
}