package ui;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame() {

        setVisible(true);
        setBounds(10, 0, 800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new Panel());
    }
}
