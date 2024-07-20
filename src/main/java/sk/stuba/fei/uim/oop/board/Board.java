package sk.stuba.fei.uim.oop.board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    private ArrayList<House> houses;
    private House currentHouse;
    private int xOffset;
    private int yOffset;

    public Board(){
        this.houses = new ArrayList<>();
        this.currentHouse = null;
        this.xOffset = 0;
        this.yOffset = 0;
    }

    public void startDrawing(int x, int y, Color color) {
        this.currentHouse = new House(x,y,x+50,y+50, color);
        this.houses.add(currentHouse);
    }

    public void drawing(int x, int y) {
        this.currentHouse.setNewEndPoint(x, y);
        repaint();
    }

    public void endDrawing(int x, int y) {
        this.currentHouse.setNewEndPoint(x, y);
        repaint();
        this.currentHouse.correctPoints();
    }

    public boolean isHouseThere(int x, int y) {
        for (House h : this.houses){
            if(h.getStartX() < x && h.getEndX() > x && h.getStartY() < y && h.getEndY() > y){
                return true;
            }
        }
        return false;
    }

    public void getHouse(int x, int y) {
        int i = this.houses.size();
        while(true) {
            House h = this.houses.get(i-1);
                if(h.getStartX() < x && h.getEndX() > x && h.getStartY() < y && h.getEndY() > y){
                    this.currentHouse = h;
                    this.xOffset = x - h.getStartX();
                    this.yOffset = y - h.getStartY();
                    return;
                }
            i--;
            if(i < 0){
                this.currentHouse = null;
                return;
            }
        }
    }

    public void moveHouse(int x, int y) {
        if(this.currentHouse == null) {
            return;
        }
        this.currentHouse.setNewStartEndPoint(x - this.xOffset, y - this.yOffset, x + this.xOffset, y + this.yOffset);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(House h : this.houses){
            h.paintComponent(g);
        }
        if(this.currentHouse != null){
            this.currentHouse.paintComponent(g);
        }
    }
}
