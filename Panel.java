package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Panel extends JPanel {
    ImageIcon[][] cardVisible = new ImageIcon[4][6];
    JButton[][] cardHidden = new JButton[4][6];
    JLabel time;
    Timer timer;
    FlipCard flip = new FlipCard();
    int[][] showArray =flip.cards;
    int timercount = 91;
    int count = 0;
    int oneRow; int oneCol; int twoRow; int twoCol;

    public Panel(){
        setLayout(new BorderLayout());
        JPanel subpanel = new JPanel();
        subpanel.setLayout(new GridLayout(4,6));
        for(int i = 0; i<cardVisible.length; i++){
            for(int j = 0; j<cardVisible[0].length; j++){
                JButton card = new JButton(new ImageIcon("cardback.png"));
                card.addActionListener(new Listener(i,j));
                Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
                card.setBorder(border);
                cardHidden[i][j] = card;
                subpanel.add(cardHidden[i][j]);

                String number = "" + showArray[i][j];
                ImageIcon image = new ImageIcon(new ImageIcon(number + ".png").getImage().getScaledInstance(90 ,100, Image.SCALE_DEFAULT));
                cardVisible[i][j] = image;
            }
        }
        add(subpanel, BorderLayout.CENTER);

        time = new JLabel("...");
        time.setHorizontalAlignment(SwingConstants.CENTER);
        add(time, BorderLayout.NORTH);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timercount--;
                if (timercount>=0) {
                    boolean win = true;
                    for(int i = 0; i<cardVisible.length; i++) {
                        for (int j = 0; j < cardVisible[0].length; j++) {
                            if(cardHidden[i][j].getIcon()!=cardVisible[i][j]){
                                win = false;
                            }
                        }
                    }

                    if(timercount<=3){
                        time.setForeground(Color.RED);
                    }

                    time.setText("Time Remaining: " + timercount + " seconds");

                    if(win==true){
                        timercount = -1;
                    }

                } else {
                    ((Timer) (e.getSource())).stop();
                    for(int i = 0; i<cardVisible.length; i++) {
                        for (int j = 0; j < cardVisible[0].length; j++) {
                            cardHidden[i][j].setEnabled(false);
                        }
                    }

                    boolean winner = true;
                    for(int i = 0; i<cardVisible.length; i++) {
                        for (int j = 0; j < cardVisible[0].length; j++) {
                            if(cardHidden[i][j].getIcon()!=cardVisible[i][j]){
                                winner = false;
                            }
                        }
                    }

                    JFrame popup = new JFrame();
                    UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 16));
                    if(winner==true){
                        UIManager.put("OptionPane.messageForeground", Color.green);
                        JOptionPane.showMessageDialog(popup, "YOU WON!!");
                    }
                    else{
                        UIManager.put("OptionPane.messageForeground", Color.red);
                        JOptionPane.showMessageDialog(popup, "YOU LOST :(");
                    }


                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();

    }

    private class Listener implements ActionListener{
        int row;
        int col;
        public Listener(int i, int j){
            row = i;
            col = j;
        }
        public void actionPerformed(ActionEvent e){
            cardHidden[row][col].setIcon(cardVisible[row][col]);
            if(count == 0){
                oneRow = row;
                oneCol = col;
            }
            else if(count == 1){
                twoRow = row;
                twoCol = col;
            }
            else{
                count = 0;
                if(!(flip.checkCards(showArray[oneRow][oneCol], showArray[twoRow][twoCol]))){
                    cardHidden[oneRow][oneCol].setIcon(new ImageIcon("cardback.png"));
                    cardHidden[twoRow][twoCol].setIcon(new ImageIcon("cardback.png"));
                }
                oneRow = row;
                oneCol = col;
            }
            count = count + 1;

        }
    }

}

