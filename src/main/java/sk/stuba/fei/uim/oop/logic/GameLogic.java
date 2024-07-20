package sk.stuba.fei.uim.oop.logic;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameLogic extends UniversalAdapter{
    private JFrame mainGame;
    private Board currentBoard;
    @Getter
    private JPanel buttonMenu;
    @Getter
    private JButton buttonHouse;
    @Getter
    private JButton buttonMove;
    @Getter
    private JButton buttonNextColor;
    @Getter
    private JLabel labelText;
    private int colorIndex;
    private ArrayList<Color> colors;
    private Color color;
    private int currentAction;

    public GameLogic(JFrame mainGame) {
        this.mainGame = mainGame;
        this.currentBoard = new Board();
        this.currentBoard.addMouseListener(this);
        this.currentBoard.addMouseMotionListener(this);
        this.mainGame.add(this.currentBoard);
        this.colorIndex = 0;
        this.colors = new ArrayList<>();
        this.colors.add(Color.RED);
        this.colors.add(Color.BLUE);
        this.colors.add(Color.GREEN);
        this.color = this.colors.get(this.colorIndex);
        this.currentAction = 1;

        this.buttonMenu = new JPanel();
        this.buttonHouse = new JButton("House");
        this.buttonMove = new JButton("Move");
        this.buttonNextColor = new JButton("Next color");
        this.labelText = new JLabel("Nothing");
        this.labelText.setOpaque(true);
        this.labelText.setBackground(this.color);
        this.buttonHouse.addActionListener(this);
        this.buttonHouse.setFocusable(false);
        this.buttonMove.addActionListener(this);
        this.buttonMove.setFocusable(false);
        this.buttonNextColor.addActionListener(this);
        this.buttonNextColor.setFocusable(false);
        this.rewriteLabel();
    }

    private void rewriteLabel(){
        switch (this.currentAction){
            case 1:
                this.labelText.setText("Drawing");
                break;
            case 2:
                this.labelText.setText("Moving");
                break;
            case 3:
                this.nextColor();
                break;
        }
    }

    private void nextColor() {
        this.colorIndex = this.colorIndex + 1;
        if(this.colorIndex >= this.colors.size()){
            this.colorIndex = 0;
        }
        this.color = this.colors.get(this.colorIndex);
        this.labelText.setBackground(this.color);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(this.currentAction == 1){
            this.currentBoard.startDrawing(e.getX(), e.getY(), this.color);
        }
        if(this.currentAction == 2 && this.currentBoard.isHouseThere(e.getX(), e.getY())){
            this.currentBoard.getHouse(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(this.currentAction == 1){
            this.currentBoard.drawing(e.getX(), e.getY());
        }
        if(this.currentAction == 2 && this.currentBoard.isHouseThere(e.getX(), e.getY())){
            this.currentBoard.moveHouse(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.currentAction == 1){
            this.currentBoard.endDrawing(e.getX(), e.getY());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.buttonHouse){
            this.currentAction = 1;
            rewriteLabel();
        }
        if(e.getSource() == this.buttonMove){
            this.currentAction = 2;
            rewriteLabel();
        }
        if(e.getSource() == this.buttonNextColor){
            this.nextColor();
        }
    }
}
