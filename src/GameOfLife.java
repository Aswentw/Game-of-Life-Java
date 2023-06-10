import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameOfLife {

    static JFrame window;

    static JFrame settings;
    static ImageIcon appIcon = new ImageIcon("src/appICON.png");

    static JButton startBUTTON = new JButton("Start simulation");

    static JButton clearBUTTON = new JButton("Clear");

    static JButton gridBUTTON = new JButton("Grid: OFF");

    static JButton settingsBUTTON = new JButton();

    static boolean gridAct = false;

    static boolean isRunning = false;

    static Color userColor = Color.GREEN;
    static JPanel[][] map = new JPanel[100][80];

    static int gen = 0;

    static int speed = 1;

    static void dbmapDraw(){

        window.setSize(1414, 650);

        map = new JPanel[200][80];

        mapDraw();
    }
    static void mapClear(){

        window.setSize(714, 650);

        map = new JPanel[100][80];

        mapDraw();
    }

    static void mapDraw(){

        for(int i = 0; i < map.length; i++){

            for(int j = 0; j < map[0].length; j++){

                if(j != 0) {

                    map[i][j] = new JPanel();

                    map[i][j].setBackground(Color.BLACK);

                    map[i][j].setBounds(i * 7, j * 7 - 7, 7, 7);

                    int finalI = i;
                    int finalJ = j;

                    map[i][j].addMouseListener(new MouseListener() {                       
                        @Override
                        public void mouseClicked(MouseEvent e) {
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (!isRunning) {
                                if (map[finalI][finalJ].getBackground() == Color.BLACK) {
                                    map[finalI][finalJ].setBackground(userColor);                                   
                                } else {
                                    map[finalI][finalJ].setBackground(Color.BLACK);
                                }
                            }
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                        }

                    });


                    window.getContentPane().add(map[i][j]);
                }

            }
        }
    }

    static void gridDraw(boolean what){

        if(what){


            for (JPanel[] jPanels : map) {

                for (int j = 0; j < map[0].length; j++) {

                    if(j != 0) {

                        jPanels[j].setBorder(BorderFactory.createLineBorder(new Color(20, 20, 20), 1));

                    }

                }

            }
        }
        else{

            for (JPanel[] jPanels : map) {

                for (int j = 0; j < map[0].length; j++) {

                    if(j != 0) {

                        jPanels[j].setBorder(null);

                    }

                }

            }



        }

    }

    static void clear(){

        gen = 0;

        for (JPanel[] jPanels : map) {

            for (int j = 0; j < map[0].length; j++) {

                if (j != 0) {

                    jPanels[j].setBackground(Color.BLACK);

                }

            }
        }

    }

    static void paint(){


        for (JPanel[] jPanels : map) {

            for (int j = 0; j < map[0].length; j++) {

                if (j != 0) {

                    if (jPanels[j].getBackground() != Color.BLACK) {


                        jPanels[j].setBackground(userColor);

                    }

                }
            }
        }

    }

    public static void main(String[] args) {

        window = new JFrame();

        window.setSize(714, 650);

        window.setTitle("Game of Life");
        window.setIconImage(appIcon.getImage());

        window.setLayout(null);
        window.setResizable(false);

        window.getContentPane().setBackground(new Color(120, 120, 120));

        startBUTTON.setBounds(150, 580, 150, 25);
        startBUTTON.setFocusable(false);

        startBUTTON.setForeground(new Color(220, 220, 220));
        startBUTTON.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        startBUTTON.setBackground(new Color(150, 150 ,150));
        startBUTTON.setFont(new Font("Calibri", Font.BOLD, 15));
        window.getContentPane().add(startBUTTON);

        clearBUTTON.setBounds(325, 580, 50, 25);
        clearBUTTON.setFocusable(false);

        clearBUTTON.setForeground(new Color(220, 220, 220));
        clearBUTTON.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        clearBUTTON.setBackground(new Color(150, 150 ,150));
        clearBUTTON.setFont(new Font("Calibri", Font.BOLD, 15));
        window.getContentPane().add(clearBUTTON);

        gridBUTTON.setBounds(400, 580, 75, 25);
        gridBUTTON.setFocusable(false);

        gridBUTTON.setForeground(new Color(220, 220, 220));
        gridBUTTON.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        gridBUTTON.setBackground(new Color(150, 150 ,150));
        gridBUTTON.setFont(new Font("Calibri", Font.BOLD, 15));
        window.getContentPane().add(gridBUTTON);


        settingsBUTTON.setBounds(500, 580, 80, 25);
        settingsBUTTON.setFocusable(false);
        settingsBUTTON.setText("Settings");

        settingsBUTTON.setFont(new Font("Calibri", Font.BOLD, 15));
        settingsBUTTON.setForeground(new Color(220, 220, 220));

        settingsBUTTON.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        settingsBUTTON.setBackground(new Color(150, 150 ,150));

        window.getContentPane().add(settingsBUTTON);

        JPanel border = new JPanel();

        JLabel title3 = new JLabel("by aswentw");
        JLabel title4 = new JLabel("Just click ↑↑↑");
        JLabel genLabel = new JLabel("Gen: 0");

        title3.setForeground(new Color(169, 169, 169));

        title4.setForeground(new Color(169, 169, 169));

        genLabel.setForeground(new Color(169, 169, 169));

        title3.setFont(new Font("Times new roman", Font.BOLD, 12));

        title4.setFont(new Font("Times new roman", Font.BOLD, 12));

        genLabel.setFont(new Font("Times new roman", Font.BOLD, 12));

        title3.setBounds(5, 590, 100, 25);

        title4.setBounds(5, 570, 100, 25);

        genLabel.setBounds(75, 590, 100, 25);

        border.setBounds(0 ,554, 700, 7);

        border.setBackground(new Color(140, 140, 140));

        window.getContentPane().add(title3);

        window.getContentPane().add(title4);

        window.getContentPane().add(genLabel);

        window.getContentPane().add(border);

        settings = new JFrame();
        settings.setSize(400,400);

        settings.setTitle("Settings");

        settings.setResizable(false);
        settings.setLayout(null);

        settings.getContentPane().setBackground(new Color(120, 120, 120));


        JButton submitSettings = new JButton("Submit");

        JButton cancelSettings = new JButton("Cancel");

        JLabel infoColors = new JLabel("Pick the color of cells:");
        JLabel infoScale = new JLabel("Pick the width of window:");
        JLabel infoSpeed = new JLabel("Pick the speed of simulation:");

        infoColors.setBounds(10, 185 , 150, 25);
        infoScale.setBounds(10, 35 , 200, 25);
        infoSpeed.setBounds(10, 125 , 200, 25);

        infoColors.setForeground(new Color(220, 220, 220));
        infoColors.setFont(new Font("Calibri", Font.BOLD, 15));

        infoScale.setForeground(new Color(220, 220, 220));
        infoScale.setFont(new Font("Calibri", Font.BOLD, 15));

        infoSpeed.setForeground(new Color(220, 220, 220));
        infoSpeed.setFont(new Font("Calibri", Font.BOLD, 15));

        submitSettings.setFocusable(false);

        submitSettings.setBounds(280, 335, 100, 25);

        submitSettings.setForeground(new Color(220, 220, 220));
        submitSettings.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        submitSettings.setBackground(new Color(150, 150 ,150));
        submitSettings.setFont(new Font("Calibri", Font.BOLD, 15));

        cancelSettings.setFocusable(false);

        cancelSettings.setBounds(5, 335, 100, 25);

        cancelSettings.setForeground(new Color(220, 220, 220));
        cancelSettings.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        cancelSettings.setBackground(new Color(150, 150 ,150));
        cancelSettings.setFont(new Font("Calibri", Font.BOLD, 15));

        settings.getContentPane().add(submitSettings);
        settings.getContentPane().add(cancelSettings);
        settings.getContentPane().add(infoColors);
        settings.getContentPane().add(infoScale);
        settings.getContentPane().add(infoSpeed);

        Color[] settingColors = {Color.BLUE, Color.RED, Color.ORANGE, Color.PINK, Color.WHITE, Color.YELLOW, Color.CYAN, new Color(73, 7, 82), Color.GREEN};

        JCheckBox[] scalePanel = {new JCheckBox("1x"), new JCheckBox("2x")};

        JCheckBox[] speedPanel = {new JCheckBox("1"), new JCheckBox("2"), new JCheckBox("3"), new JCheckBox("4")};

        JPanel[] settingColorsPanel = new JPanel[settingColors.length];

        final Color[] pickedColor = {Color.GREEN};

        for(int i = 0; i < speedPanel.length; i++){

            speedPanel[i].setBounds(i * 100 + 10, 150 , 100, 25);

            speedPanel[i].setFocusable(false);

            speedPanel[i].setForeground(new Color(220, 220, 220));
            speedPanel[i].setBackground(new Color(160, 160, 160));
            speedPanel[i].setFont(new Font("Calibri", Font.BOLD, 15));

            settings.getContentPane().add(speedPanel[i]);

            int finalI = i;

            speedPanel[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                    for(int t = 0; t < speedPanel.length; t++){

                        if(t != finalI){

                            speedPanel[t].setSelected(false);

                        }

                    }

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        for(int i = 0; i < scalePanel.length; i++){

            scalePanel[i].setBounds(i * 100 + 10, 60 , 100, 25);

            scalePanel[i].setFocusable(false);

            scalePanel[i].setForeground(new Color(220, 220, 220));
            scalePanel[i].setBackground(new Color(160, 160, 160));
            scalePanel[i].setFont(new Font("Calibri", Font.BOLD, 15));

            settings.getContentPane().add(scalePanel[i]);

            int finalI = i;

            scalePanel[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                    for(int t = 0; t < scalePanel.length; t++){

                        if(t != finalI){

                            scalePanel[t].setSelected(false);

                        }

                    }

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });


        }

        for(int i = 0; i < settingColors.length; i++){

            settingColorsPanel[i] = new JPanel();

            settingColorsPanel[i].setBackground(settingColors[i]);

            settingColorsPanel[i].setBounds(i * 25 + 10, 210 , 20, 20);

            settings.add(settingColorsPanel[i]);

            int finalI = i;

            settingColorsPanel[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    settingColorsPanel[finalI].setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 3));

                    pickedColor[0] = settingColorsPanel[finalI].getBackground();

                    for(int t = 0; t < settingColorsPanel.length; t++){

                        if(t != finalI){


                            settingColorsPanel[t].setBorder(null);


                        }

                    }



                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });






        }

        submitSettings.addActionListener(e -> {

            if(scalePanel[0].isSelected()) {

                int result = JOptionPane.showOptionDialog(null,
                        "Your simulation will be erased, continue?", "",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, new String[]{"Yes", "No"}, "Yes");

                if (result == JOptionPane.YES_OPTION) {
                    clear();
                    map = null;
                    border.setBounds(0, 554, 700, 7);
                    window.revalidate();
                    gen = 0;
                    mapClear();
                    window.revalidate();
                }

            }
            else if(scalePanel[1].isSelected()) {

                int result = JOptionPane.showOptionDialog(null,
                        "Your simulation will be erased, continue?", "",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, new String[]{"Yes", "No"}, "Yes");

                if (result == JOptionPane.YES_OPTION) {
                    clear();
                    map = null;
                    border.setBounds(0, 554, 1400, 7);
                    window.revalidate();
                    gen = 0;
                    dbmapDraw();
                    window.revalidate();
                }


            }

            userColor = pickedColor[0];

            if(!scalePanel[0].isSelected() && !scalePanel[0].isSelected()) {
                paint();
            }

            if(speedPanel[0].isSelected()){
                speed = 1;
            }
            else if(speedPanel[1].isSelected()){
                speed = 2;
            }
            else if(speedPanel[2].isSelected()){
                speed = 3;
            }
            else if(speedPanel[3].isSelected()){
                speed = 4;
            }

            settings.setVisible(false);

            });

        cancelSettings.addActionListener(e -> settings.setVisible(false));


        gridBUTTON.addActionListener(e -> {

            if(gridAct)
            {
                gridAct = false;
                gridBUTTON.setBackground(new Color(150, 150 ,150));
                gridBUTTON.setFont(new Font("Calibri", Font.BOLD, 15));
                gridBUTTON.setText("Grid: OFF");

                gridDraw(false);
            }
            else
            {
                gridAct = true;
                gridBUTTON.setBackground(new Color(120, 120 ,120));
                gridBUTTON.setFont(new Font("Calibri", Font.ITALIC, 15));
                gridBUTTON.setText("Grid: ON");

                gridDraw(true);

            }
        });

        clearBUTTON.addActionListener(e -> {

            if(isRunning) {
                JOptionPane.showMessageDialog(null, "The simulation is now running!", "Error", JOptionPane.WARNING_MESSAGE);
            }
            else{
                clear();
            }

        });

        settingsBUTTON.addActionListener(e -> {

            if(!isRunning) {


                settings.setVisible(true);


            }
            else{

                JOptionPane.showMessageDialog(null, "The simulation is now running!", "Error", JOptionPane.WARNING_MESSAGE);

            }



        });

        startBUTTON.addActionListener(e -> {

            if(isRunning){

                isRunning = false;

                startBUTTON.setBackground(new Color(150, 150 ,150));
                startBUTTON.setFont(new Font("Calibri", Font.BOLD, 15));
                startBUTTON.setText("Start simulation.");

            }
            else if(!settings.isVisible()){

                isRunning = true;

                SwingWorker threadSim = new SwingWorker() {
                    @Override
                    protected Object doInBackground() {

                        while(isRunning) {


                            ArrayList<Integer> finalI = new ArrayList<>();
                            ArrayList<Integer> finalK = new ArrayList<>();

                            ArrayList<Integer> finalI2 = new ArrayList<>();
                            ArrayList<Integer> finalK2 = new ArrayList<>();

                            int birth;

                            int death;

                            int birthAmount = 0;

                            int deathAmount = 0;

                            for (int i = 0; i < map.length; i++) {

                                for (int k = 0; k < map[0].length; k++) {

                                    if (k != 0) {

                                        if (map[i][k].getBackground() == Color.BLACK) {

                                            birth = 0;

                                            for (int y = 0; y < 3; y++) {

                                                for (int o = 0; o < 3; o++) {

                                                    try {
                                                        if (map[i + (y - 1)][k + (o - 1)].getBackground() == userColor) {

                                                            birth++;

                                                        }
                                                    } catch (Exception e) {

                                                        break;
                                                    }

                                                }
                                            }

                                            if (birth == 3) {

                                                birthAmount++;

                                                finalI.add(i);
                                                finalK.add(k);


                                            }

                                        }

                                        else if (map[i][k].getBackground() == userColor) {

                                            death = 0;

                                            for (int y = 0; y < 3; y++) {

                                                for (int o = 0; o < 3; o++) {

                                                    try {
                                                        if (map[i + (y - 1)][k + (o - 1)].getBackground() == userColor) {

                                                            death++;

                                                        }
                                                    } catch (Exception e) {
                                                        break;
                                                    }

                                                }
                                            }

                                            if (death <= 2 || death >= 5) {

                                                deathAmount++;

                                                finalI2.add(i);
                                                finalK2.add(k);


                                            }


                                        }

                                    }
                                }
                                }

                                for (int s = 0; s < birthAmount; s++) {
                                    map[finalI.get(s)][finalK.get(s)].setBackground(userColor);
                                }

                                for (int s = 0; s < deathAmount; s++) {
                                    map[finalI2.get(s)][finalK2.get(s)].setBackground(Color.BLACK);
                                }

                                try {
                                    Thread.sleep(150 / speed);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                gen++;

                                genLabel.setText("Gen: " + gen);

                                window.validate();
                        }

                        return null;

                    }
                };


                threadSim.execute();

                startBUTTON.setBackground(new Color(120, 120 ,120));
                startBUTTON.setFont(new Font("Calibri", Font.ITALIC, 15));
                startBUTTON.setText("Stop simulation.");

            }
            else{
                JOptionPane.showMessageDialog(null, "The settings is now open!", "Error", JOptionPane.WARNING_MESSAGE);
            }




        });

        mapDraw();

        window.setVisible(true);
    }
}
