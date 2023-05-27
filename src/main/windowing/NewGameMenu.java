package main.windowing;

import main.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NewGameMenu extends JPanel implements ListSelectionListener {
    String mapsDirectoryPath = Main.rootfolder+"\\files\\maps\\";

    List<String> mapFiles = getFileNames(mapsDirectoryPath);

    private List<String> getFileNames(String directoryPath){
        List<String> fileNames = new ArrayList<>();

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName().substring(0, file.getName().lastIndexOf('.')));
                }
            }
        }

        return fileNames;
    }

    JList<Object> lMapsList;
    JButton bBack, bStart;
    JPanel pList, pButtons, pCentralize;

    public NewGameMenu(Window window){
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0,180, 255));

        bBack = new JButton("Back");
        bStart = new JButton("Start");
        bStart.setEnabled(false);
        bBack.addActionListener(window);
        bStart.addActionListener(window);

        pList = new JPanel();
        pButtons = new JPanel();
        pCentralize = new JPanel();

        lMapsList = new JList<>(mapFiles.toArray());
        lMapsList.addListSelectionListener(this);
        lMapsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lMapsList.setLayoutOrientation(JList.VERTICAL);
        lMapsList.setVisibleRowCount(-1);
        lMapsList.setFont(new Font("Arial", Font.PLAIN,30 ));

        JScrollPane listScroller = new JScrollPane(lMapsList);
        listScroller.setPreferredSize(new Dimension(250, 150));

        pList.add(listScroller);

        pButtons.add(bStart);
        pButtons.add(bBack);

        pCentralize.setLayout(new BoxLayout(pCentralize, BoxLayout.Y_AXIS));
        pCentralize.add(pList);
        pCentralize.add(pButtons);

        add(pCentralize);
    }
    @Override
    public void valueChanged(ListSelectionEvent e){
        if (!e.getValueIsAdjusting())
            bStart.setEnabled(lMapsList.getSelectedIndex() != -1);
    }
}
