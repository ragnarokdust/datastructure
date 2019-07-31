package astar.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import astar.Astar;
import astar.datastruct.Cell;
import astar.manage.Factory;

public class MaskPanel extends JPanel {
    private static final long serialVersionUID = -6350215516919553939L;
    Astar astar = Factory.getAstar();
    private int unitH = 66;
    private int unitW = 66;

    public MaskPanel() {
        super();
        this.setFont(new Font("Consola",Font.BOLD,10));
    }

    public void init() {

    }

    public void proc() {
        
    }

    public void render() {
        
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int[][] mask = astar.getMask();
        String checkStr;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                g.setColor(new Color(0,0,0));
                g.drawRect(j*unitW+1, i*unitH+1, unitW, unitH);

                if(mask[i][j]!=1){
                    g.setColor(new Color(50,50,50));
                    checkStr = "Possible";
                }else{
                    g.setColor(new Color(200,200,200));
                    checkStr = "Impossible ";
                }
                g.fillRect(j*unitW+2, i*unitH+2, unitW-1, unitH-1);

                g.setColor(new Color(255,255,255));

                
                g.drawString(checkStr, j*unitW+15, i*unitH+25);
                g.drawString(String.format("(%3d,%3d)",j,i), j*unitW+15, i*unitH+40);
            }
        }
    }

    public Color setColorCell(Cell cell) {
        if(cell.getStatus() == Cell.Status.EMPTY){
            return new Color(255, 255, 255);
        }else if(cell.getStatus() == Cell.Status.BARRIER){
            return new Color(0,0,0);
        }else if(cell.getStatus() == Cell.Status.START){
            return new Color(200,200,0);
        }else if(cell.getStatus() == Cell.Status.END){
            return new Color(255,0,0);
        }
        return new Color(255, 255, 255);
    }
}