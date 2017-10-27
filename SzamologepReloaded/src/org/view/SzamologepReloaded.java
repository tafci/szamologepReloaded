package org.view;

import org.controller.RpnCalculator;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SzamologepReloaded extends JFrame {

    private JTextField szovegmezo;

    JButton szamGombok[] = new JButton[10]; //számgombok tömb
    JButton operatorGombok[] = new JButton[6];//operator tömb
    private String muveletiJel[] = new String[]{"+", "-", "*", "=", ".", "/"}; //műveleti jelek

    public SzamologepReloaded() { //konstruktor+ layout
        initComp();
        this.setSize(216, 288);
        this.setTitle("Számológép");
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initComp() { //komponens létrehozás +pozicionálás
        szovegmezo = new JTextField("");
        szovegmezo.setBounds(0, 0, 200, 50);
        szovegmezo.setHorizontalAlignment(SwingConstants.RIGHT);
        szovegmezo.setEditable(false);
        this.add(szovegmezo);
        int szamXPos = 0;
        int szamYPos = 150;
        for (int i = 0; i < 10; i++) {
            szamGombok[i] = new JButton(String.valueOf(i)); //
            szamGombok[i].addActionListener(this::szamAction); //actionListener
            if (i == 0) {
                szamGombok[i].setBounds(0, 200, 50, 50);
            } else {
                szamGombok[i].setBounds(szamXPos, szamYPos, 50, 50);
                szamXPos += 50;
                if (i % 3 == 0) {
                    szamYPos -= 50;
                    szamXPos = 0;
                }
            }
            this.add(szamGombok[i]);
        }
        int opXPos = 150;
        int opYPos = 50;
        for (int i = 0; i < muveletiJel.length; i++) {
            operatorGombok[i] = new JButton(String.valueOf(muveletiJel[i]));
            operatorGombok[i].addActionListener(this::operatorAction);
            if (i <= 3) {
                operatorGombok[i].setBounds(150, opYPos, 50, 50);
                opYPos += 50;
            } else {
                if (i == 4) {
                    operatorGombok[i].setBounds(50, 200, 50, 50);
                } else {
                    operatorGombok[i].setBounds(100, 200, 50, 50);
                }
            }
            this.add(operatorGombok[i]);
        }
    }

    private void szamAction(ActionEvent Event) {
        szovegmezo.setText((szovegmezo.getText() + ((JButton) Event.getSource()).getText()));
    }

    private void operatorAction(ActionEvent Event) {

        char operator = (((JButton) Event.getSource()).getText()).charAt(0);
        switch (operator) {
            case '+':
                szovegmezo.setText((szovegmezo.getText() + " " + ((JButton) Event.getSource()).getText()) + " ");
                break;
            case '-':
                szovegmezo.setText((szovegmezo.getText() + " " + ((JButton) Event.getSource()).getText()) + " ");
                break;
            case '*':
                szovegmezo.setText((szovegmezo.getText() + " " + ((JButton) Event.getSource()).getText()) + " ");
                break;
            case '/':
                szovegmezo.setText((szovegmezo.getText() + " " + ((JButton) Event.getSource()).getText()) + " ");
                break;
            case '.':
                szovegmezo.setText((szovegmezo.getText() + ((JButton) Event.getSource()).getText()));
                break;
            case '=':
                szovegmezo.setText(RpnCalculator.infixToResult(szovegmezo.getText()).toString());
                break;
        }
    }

    public static void main(String[] args) {
        new SzamologepReloaded().setVisible(true);
    }
}
