import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.ArrayList;

public class GameLife {

    static JFrame window;

    static JButton startBUTTON = new JButton("Start simulation.");

    static JButton clearBUTTON = new JButton("Clear.");

    static JButton gridBUTTON = new JButton("Grid: OFF");

    static boolean gridAct = false;

    static boolean isRunning = false;

    static SwingWorker threadSim;

    static JPanel[][] map = new JPanel[50][50];

    static int gen = 0;

    static void mapDraw(){

        for(int i = 0; i < map.length; i++){

            for(int j = 0; j < map[0].length; j++){

                map[i][j] = new JPanel();

                int rand = (int)Math.floor(1 + Math.random() * 2);

                map[i][j].setBackground(Color.BLACK);

                int finalI = i;
                int finalJ = j;

                map[i][j].addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(!isRunning) {
                            if (map[finalI][finalJ].getBackground() == Color.BLACK) {
                                map[finalI][finalJ].setBackground(Color.GREEN);
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

                map[i][j].setBounds(i * 10, j * 10, 10, 10);

                window.getContentPane().add(map[i][j]);

            }
        }
    }



    static void gridDraw(boolean what){

        if(what){


            for(int i = 0; i < map.length; i++) {

                for (int j = 0; j < map[0].length; j++) {

                    map[i][j].setBorder(BorderFactory.createLineBorder(new Color(20, 20, 20), 1));

                }

            }
        }
        else{

            for(int i = 0; i < map.length; i++) {

                for (int j = 0; j < map[0].length; j++) {

                    map[i][j].setBorder(null);

                }

            }



        }

    }

    static void clear(){

        for(int i = 0; i < map.length; i++) {

            for (int j = 0; j < map[0].length; j++) {

                map[i][j].setBackground(Color.BLACK);

            }
        }

    }

    static void simulation(){

        
    }







    public static void main(String[] args) {

        window = new JFrame();

        window.setTitle("Game of Life");

        window.setSize(505, 590);
        window.setLayout(null);
        window.setResizable(false);

        window.getContentPane().setBackground(new Color(120, 120, 120));

        startBUTTON.setBounds(150, 520, 150, 25);
        startBUTTON.setFocusable(false);

        startBUTTON.setForeground(new Color(220, 220, 220));
        startBUTTON.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        startBUTTON.setBackground(new Color(150, 150 ,150));
        startBUTTON.setFont(new Font("Calibri", Font.BOLD, 15));
        window.getContentPane().add(startBUTTON);

        clearBUTTON.setBounds(325, 520, 50, 25);
        clearBUTTON.setFocusable(false);

        clearBUTTON.setForeground(new Color(220, 220, 220));
        clearBUTTON.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        clearBUTTON.setBackground(new Color(150, 150 ,150));
        clearBUTTON.setFont(new Font("Calibri", Font.BOLD, 15));
        window.getContentPane().add(clearBUTTON);

        gridBUTTON.setBounds(400, 520, 75, 25);
        gridBUTTON.setFocusable(false);

        gridBUTTON.setForeground(new Color(220, 220, 220));
        gridBUTTON.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        gridBUTTON.setBackground(new Color(150, 150 ,150));
        gridBUTTON.setFont(new Font("Calibri", Font.BOLD, 15));
        window.getContentPane().add(gridBUTTON);

        JPanel border = new JPanel();

        JLabel title3 = new JLabel("by aswentw.");
        JLabel title4 = new JLabel("Just click. ↑↑↑");
        JLabel genLabel = new JLabel("Gen: 0");

        title3.setForeground(new Color(169, 169, 169));

        title4.setForeground(new Color(169, 169, 169));

        genLabel.setForeground(new Color(169, 169, 169));

        title3.setFont(new Font("Times new roman", Font.BOLD, 12));

        title4.setFont(new Font("Times new roman", Font.BOLD, 12));

        genLabel.setFont(new Font("Times new roman", Font.BOLD, 12));

        title3.setBounds(5, 530, 100, 25);

        title4.setBounds(5, 510, 100, 25);

        genLabel.setBounds(75, 530, 100, 25);

        window.getContentPane().add(title3);

        window.getContentPane().add(title4);

        window.getContentPane().add(genLabel);





        border.setBackground(new Color(150, 150, 150));
        border.setBounds(0, 500, 500, 4);

        window.getContentPane().add(border);

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

        startBUTTON.addActionListener(e -> {

            if(isRunning){

                isRunning = false;

                gen = 0;

                startBUTTON.setBackground(new Color(150, 150 ,150));
                startBUTTON.setFont(new Font("Calibri", Font.BOLD, 15));
                startBUTTON.setText("Start simulation.");

            }
            else{

                isRunning = true;

                SwingWorker threadSim = new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {

                        //Цикл симуляции

                        while(isRunning) {


                            ArrayList<Integer> finalI = new ArrayList<>();
                            ArrayList<Integer> finalK = new ArrayList<>();

                            ArrayList<Integer> finalI2 = new ArrayList<>();
                            ArrayList<Integer> finalK2 = new ArrayList<>();

                            int birth = 0;

                            int death = 0;

                            int birthAmount = 0;

                            int deathAmount = 0;

                            //Цикл пробегающий по высоте строки.

                            for (int i = 0; i < map.length; i++) {

                                //Цикл пробегающий по ширине строки.

                                for (int k = 0; k < map[0].length; k++) {

                                    //Проверка цвета клетки: ЧЕРНАЯ - ПРОВЕРКА РОЖДЕНИЯ.

                                    if (map[i][k].getBackground() == Color.BLACK) {

                                        birth = 0;

                                        //Циклы проверки соседних клеток.

                                        for (int y = 0; y < 3; y++) {

                                            for (int o = 0; o < 3; o++) {

                                                try {
                                                    if (map[i + (y - 1)][k + (o - 1)].getBackground() == Color.GREEN) {

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

                                    //Проверка смерти.

                                    else if (map[i][k].getBackground() == Color.GREEN) {

                                        death = 0;

                                        for (int y = 0; y < 3; y++) {

                                            for (int o = 0; o < 3; o++) {

                                                try {
                                                    if (map[i + (y - 1)][k + (o - 1)].getBackground() == Color.GREEN) {

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

                            for (int s = 0; s < birthAmount; s++) {
                                map[finalI.get(s)][finalK.get(s)].setBackground(Color.GREEN);
                            }

                            for (int s = 0; s < deathAmount; s++) {
                                map[finalI2.get(s)][finalK2.get(s)].setBackground(Color.BLACK);
                            }

                            try {
                                Thread.sleep(150);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            gen++;

                            genLabel.setText("Gen: " + Integer.toString(gen));

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




        });

        mapDraw();

        window.setVisible(true);
    }
}
