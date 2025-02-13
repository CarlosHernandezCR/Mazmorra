package componentes.mTree;

import model.Door;
import model.Dungeon;
import model.Room;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;

import static common.Constantes.*;

public class MTree extends JPanel implements MTreeInterface{
    public void createJTree(Dungeon dungeon){
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(MAZMORRA);
        List<Room> rooms = dungeon.getRooms();
        for (Room room : rooms) {
            DefaultMutableTreeNode roomNode = new DefaultMutableTreeNode(HABITACION + room.getId());
            rootNode.add(roomNode);

            DefaultMutableTreeNode descriptionNode = new DefaultMutableTreeNode(DESCRIPCION + room.getDescription());
            roomNode.add(descriptionNode);

            List<Door> doors = room.getDoors();
            for (Door door : doors) {
                DefaultMutableTreeNode doorNode = new DefaultMutableTreeNode(PUERTA + door.getName() + A + door.getDest());
                roomNode.add(doorNode);
            }
        }
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

        this.add(new JTree(treeModel), BorderLayout.CENTER);
    }
}


