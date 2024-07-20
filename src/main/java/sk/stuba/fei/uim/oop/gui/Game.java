package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.logic.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {

    public Game() {
        JFrame frame = new JFrame("Exam OT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        GameLogic logic = new GameLogic(frame);
        frame.addKeyListener(logic);

        logic.getButtonMenu().setLayout(new GridLayout(1, 4));
        logic.getButtonMenu().add(logic.getButtonHouse());
        logic.getButtonMenu().add(logic.getButtonMove());
        logic.getButtonMenu().add(logic.getButtonNextColor());
        logic.getButtonMenu().add(logic.getLabelText());
        frame.add(logic.getButtonMenu(), BorderLayout.PAGE_END);

        frame.setVisible(true);
    }
}
