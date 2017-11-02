/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.controller.RpnCalculator;

/**
 *
 * @author johnny
 */
public class CalculatorFrame extends JFrame {

    private JTextField szovegmezo;
    private JButton[] szamGombok = new JButton[10]; //számgombok tömb
    private JButton[] operatorGombok = new JButton[6];//operátor tömb
    private String[] muveletiJel = new String[]{"+", "-", "*", "=", ".", "/"}; //műveleti jelek

    public CalculatorFrame() { //a JFrame beállításai

        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Számológép");
        this.setSize(250,300);
        this.setLocation(400, 250);
	this.setResizable(false);
        getRootPane().setBorder(
        BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));
        this.setLayout(new BorderLayout()); //ablak layout beállítása
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        initComp();
    }

    private void initComp() { //inicializálja a felületet
        szovegmezo = new JTextField("");
        szovegmezo.setBackground(Color.WHITE);
        szovegmezo.setHorizontalAlignment(SwingConstants.RIGHT);
        szovegmezo.setEditable(false);

        initSzamgombok(); //számgombok inicializálása
        initMuveletigombok(); //operátor (műveleti) gombok inicializálása
        this.add(szovegmezo, BorderLayout.NORTH); //szövegmező hozzáadása és igazítása
        this.add(addComponent(), BorderLayout.CENTER); //gombok hozzáadása és igazítása
    }

    private void initSzamgombok() { //gombok létrehozása és ActionListener beállítása
        for (int i = 0; i < 10; i++) {
            szamGombok[i] = new JButton(String.valueOf(i));
            szamGombok[i].setBorder(BorderFactory.createEtchedBorder(0));
            szamGombok[i].addActionListener(this::szamAction); //actionListener
        }
    }

    private void szamAction(ActionEvent event) { //számgombok eseménykezelője
        szovegmezo.setText((szovegmezo.getText() + ((JButton) event.getSource()).getText()));
    }

    private void initMuveletigombok() { //gombok létrehozása és ActionListener beállítása
        for (int i = 0; i < muveletiJel.length; i++) {
            operatorGombok[i] = new JButton(String.valueOf(muveletiJel[i]));
            operatorGombok[i].setBorder(BorderFactory.createEtchedBorder(0));
            operatorGombok[i].setForeground(Color.blue);
            operatorGombok[i].addActionListener(this::operatorAction);
        }
    }

    private void operatorAction(ActionEvent e) { //operátorgombok eseménykezelője

        char operator = (((JButton) e.getSource()).getText()).charAt(0);
        switch (operator) {
            case '+':
                szovegmezo.setText((szovegmezo.getText() + " " + ((JButton) e.getSource()).getText()) + " ");
                break;
            case '-':
                szovegmezo.setText((szovegmezo.getText() + " " + ((JButton) e.getSource()).getText()) + " ");
                break;
            case '*':
                szovegmezo.setText((szovegmezo.getText() + " " + ((JButton) e.getSource()).getText()) + " ");
                break;
            case '/':
                szovegmezo.setText((szovegmezo.getText() + " " + ((JButton) e.getSource()).getText()) + " ");
                break;
            case '.':
                szovegmezo.setText((szovegmezo.getText() + ((JButton) e.getSource()).getText()));
                break;
            case '=':
                szovegmezo.setText(RpnCalculator.infixToResult(szovegmezo.getText()).toString());
                break;
        }
    }

    private JPanel addComponent() { //gombok hozzáadása és layout beállítása
        JPanel panel = new JPanel(new GridLayout(4, 4));

        panel.add(szamGombok[7]);
        panel.add(szamGombok[8]);
        panel.add(szamGombok[9]);
        panel.add(operatorGombok[0]);
        panel.add(szamGombok[4]);
        panel.add(szamGombok[5]);
        panel.add(szamGombok[6]);
        panel.add(operatorGombok[1]);
        panel.add(szamGombok[1]);
        panel.add(szamGombok[2]);
        panel.add(szamGombok[3]);
        panel.add(operatorGombok[2]);
        panel.add(szamGombok[0]);
        panel.add(operatorGombok[3]);
        panel.add(operatorGombok[4]);
        panel.add(operatorGombok[5]);

        return panel;
    }
}
