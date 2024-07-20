package sk.stuba.fei.uim.oop.board;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class House extends JPanel {
    @Getter
    private int startX;
    @Getter
    private int startY;
    @Getter
    private int endX;
    @Getter
    private int endY;
    private int width;
    private int height;
    private Color color;

    public House(int x, int y, int endX, int endY, Color color){
        this.startX = x;
        this.startY = y;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
    }

    public void setNewEndPoint(int x, int y){
        this.endX = x;
        this.endY = y;
        this.updateWidthHeight();
    }

    public void setNewStartEndPoint(int x, int y, int endX, int endY) {
        this.startX = x;
        this.startY = y;
        this.endX = endX;
        this.endY = endY;
    }

    public void updateWidthHeight(){
        this.width = Math.abs(this.startX - this.endX);
        this.height = Math.abs(this.startY - this.endY);
    }

    public void correctPoints(){
        if(startX > endX){
            int tmp = endX;
            endX = startX;
            startX = tmp;
        }
        if(startY > endY){
            int tmp = endY;
            endY = startY;
            startY = tmp;
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(this.color);

        if(startX < endX && startY < endY) {
            int[] xPoints = {this.startX + this.width / 4, this.startX + this.width / 4 * 3, this.startX + this.width / 2};
            int[] yPoints = {this.startY + this.height / 2, this.startY + this.height / 2, this.startY};
            g.fillPolygon(xPoints, yPoints, 3);
            g.fillRect(this.startX + this.width / 4, this.startY + this.height / 2, this.width / 2, this.height / 2);
            g.drawRect(this.startX, this.startY, this.width, this.height);
        }
        if(startX < endX && startY > endY){
            int[] xPoints = {this.startX + this.width / 4, this.startX + this.width / 4 * 3, this.startX + this.width / 2};
            int[] yPoints = {this.endY + this.height / 2, this.endY + this.height / 2, this.endY};
            g.fillPolygon(xPoints, yPoints, 3);
            g.fillRect(this.startX + this.width / 4, this.startY - this.height / 2, this.width / 2, this.height / 2);
            g.drawRect(this.startX, this.endY, this.width, this.height);
        }
        if(startX > endX && startY > endY){
            int[] xPoints = {this.endX + this.width / 4, this.endX + this.width / 4 * 3, this.endX + this.width / 2};
            int[] yPoints = {this.endY + this.height / 2, this.endY + this.height / 2, this.endY};
            g.fillPolygon(xPoints, yPoints, 3);
            g.fillRect(this.endX + this.width / 4, this.endY + this.height / 2, this.width / 2, this.height / 2);
            g.drawRect(this.endX, this.endY, this.width, this.height);
        }
        if(startX > endX && startY < endY){
            int[] xPoints = {this.endX + this.width / 4, this.endX + this.width / 4 * 3, this.endX + this.width / 2};
            int[] yPoints = {this.startY + this.height / 2, this.startY + this.height / 2, this.startY};
            g.fillPolygon(xPoints, yPoints, 3);
            g.fillRect(this.endX + this.width / 4, this.startY + this.height / 2, this.width / 2, this.height / 2);
            g.drawRect(this.endX, this.startY, this.width, this.height);
        }
    }
}
