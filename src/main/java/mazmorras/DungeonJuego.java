package mazmorras;

import componentes.mLoad.MLoad;
import componentes.mLog.MLog;
import componentes.mMove.MMove;
import componentes.mTree.MTree;
import model.Dungeon;
import model.Room;

import javax.swing.*;
import java.awt.*;

public class DungeonJuego extends JFrame {

    private MLoad mLoad;
    private MTree mTree;
    private MMove mMove;
    private MLog mLog;
    private Dungeon dungeon;
    private Room currentRoom;
    private static String ENTRADO ="Has entrado a ";

    public DungeonJuego() {
        mLoad = new MLoad();
        mTree = new MTree();
        mMove = new MMove(this::handleMovement);
        mLog = new MLog();
        initUI();
    }

    private void handleMovement(Room newRoom) {
        currentRoom = newRoom;
        mMove.loadRoom(currentRoom);
        mLog.addLogMessage(ENTRADO + currentRoom.getId() + "\n");
    }


    private void initUI() {
        createMenuBar();

        setLayout(new BorderLayout());

        JScrollPane treeScrollPane = new JScrollPane(mTree);
        treeScrollPane.setPreferredSize(new Dimension(250, getHeight()));
        treeScrollPane.setMinimumSize(new Dimension(250, 0));
        add(treeScrollPane, BorderLayout.WEST);

        mMove.setPreferredSize(new Dimension(550, getHeight()));
        add(mMove, BorderLayout.EAST);

        JScrollPane logScrollPane = new JScrollPane(mLog);
        logScrollPane.setPreferredSize(new Dimension(getWidth(), 150));
        add(logScrollPane, BorderLayout.SOUTH);

        setTitle("Juego de Mazmorras");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Cargar");
        JMenuItem loadItem = new JMenuItem("Cargar mapa");

        loadItem.addActionListener(e -> loadDungeon());

        fileMenu.add(loadItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }

    private void loadDungeon() {
        mLoad.loadXMLFile();
        dungeon = mLoad.getDungeon();
        if (dungeon != null) {
            mTree.createJTree(dungeon);
            mMove.setRooms(dungeon.getRooms());
            if (!dungeon.getRooms().isEmpty()) {
                currentRoom = dungeon.getRooms().get(0);
                mMove.loadRoom(currentRoom);
                mLog.addLogMessage(ENTRADO + currentRoom.getId() + "\n");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al cargar la mazmorra.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DungeonJuego juego = new DungeonJuego();
            juego.setVisible(true);
        });
    }
}
