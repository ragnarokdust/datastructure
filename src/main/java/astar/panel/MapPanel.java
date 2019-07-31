package astar.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import astar.Astar;
import astar.datastruct.Cell;
import astar.datastruct.Location;
import astar.manage.Factory;

public class MapPanel extends JPanel{
    private static final long serialVersionUID = 4281684008324231498L;
    Astar astar = Factory.getAstar();

    int unitH = 800/astar.getHeight();
    int unitW = 800/astar.getWidth();

    public MapPanel() {
        super();
        this.setFont(new Font("consola",Font.BOLD,10));
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
        Cell[][] cell = astar.getCell();
        for (int i = 0; i < astar.getHeight(); i++) {
            for (int j = 0; j < astar.getWidth(); j++) {
                
                g.setColor(setColorCell(cell[i][j]));
                g.fillRect(j*unitW+2, i*unitH+2, unitW-2, unitH-2);

                g.setColor(new Color(0,0,0));
                g.drawRect(j*unitW+1, i*unitH+1, unitW-2, unitH-2);
                
                g.setColor(new Color(255,255,255));
                g.drawString(cell[i][j].getStatus().name(), j*unitW+3, i*unitH+15);
                g.drawString(String.format("y:%3d", i), j*unitW+3, i*unitH+25);
                g.drawString(String.format("x:%3d", j), j*unitW+3, i*unitH+35);
            }
        }

        for (Location loc : astar.getSearched()) {
            g.setColor(new Color(0,200,0));
            g.fillRect(loc.getX()*unitW + 2, loc.getY()*unitH + 2, unitW-2, unitH-2);                
        }

        for (Location loc : astar.getVisited()) {
            g.setColor(new Color(0,200,200));
            g.fillRect(loc.getX()*unitW + 2, loc.getY()*unitH + 2, unitW-2, unitH-2);                
        }

        // LinkedList<Location> list = astar.getPath();
        // for(Location loc : list){
        for (Location loc : astar.getPath()) {
            g.setColor(new Color(255,0,0));
            g.fillRect(loc.getX()*unitW + 2, loc.getY()*unitH + 2, unitW-2, unitH-2);    
        }

        //글 넣기
        for (int i = 0; i < astar.getHeight(); i++) {
            for (int j = 0; j < astar.getWidth(); j++) {
                g.setColor(new Color(255,255,255));
                g.drawString(cell[i][j].getStatus().name(), j*unitW+3, i*unitH+15);
                g.drawString(String.format("y:%3d", i), j*unitW+3, i*unitH+25);
                g.drawString(String.format("x:%3d", j), j*unitW+3, i*unitH+35);
            }
        }
        
    }

    public Color setColorCell(Cell cell) {
        if(cell.getStatus() == Cell.Status.EMPTY){
            return new Color(170, 170, 170);
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