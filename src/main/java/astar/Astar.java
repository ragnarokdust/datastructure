package astar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import astar.datastruct.Cell;
import astar.datastruct.Position;

public class Astar {
    private int height;
    private int width;
    private int startY;
    private int startX;
    private int endY;
    private int endX;

    private Cell[][] cell;

    private LinkedList<Position> open = new LinkedList<>();
    private LinkedList<Position> close = new LinkedList<>();
    private LinkedList<Position> path = new LinkedList<>();

    private int[][] mask = {
        {0,1,0},
        {1,1,1},
        {0,1,0}
    };
    private int mheight = 3;
    private int mwidth = 3;

    public void init() {
        // #region generate Cell
        generate();
        // #endregion generate
        
    }

    public void proc() {
        findPath();
    }

    public void render() {
        
    }

    private void generate() {
        cell = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cell[i][j].setStatus(Cell.Status.EMPTY);
            }
        }

        for (int i = 0; i < 10; i++) {
            int y = (int)(Math.random()*height);
            int x = (int)(Math.random()*width);

            cell[y][x].setStatus(Cell.Status.BARRIER);
        }

        cell[startY][startX].setStatus(Cell.Status.START);
        cell[endY][endX].setStatus(Cell.Status.END);

        Position start = new Position();
        start.setF(0);
        start.setG(0);
        start.setH(0);
        start.setY(startY);
        start.setX(startX);
        start.setPrevY(startY);
        start.setPrevX(startX);
        start.setStatus(Position.Status.CLOSED);

        close.add(start);
    }


    private int heuristic(int x1, int y1, int x2, int y2) {
        return (Math.abs(x1-x2) + Math.abs(y1-y2)) * 10;
    }
    
    public void findPath() {
        int y1 = startY;
        int x1 = startX;
        int f = 0;
        int g = 0;
        int h = 0;

        while(true){
            Position search = close.get(close.size() - 1);
            y1 = search.getY();
            x1 = search.getX();
            if(y1 == endY && x1 == endX){
                break;
            }
            for (int y2 = 0; y2 < mheight; y2++) {
                for (int x2 = 0; x2 < mwidth; x2++) {
                    if(mask[y2][x2] == 0){
                        continue;
                    }

                    if(checkRange(y1 - y2, x1 - x2, height, width) == false){
                        continue;
                    }
                    
                    Position pos = new Position();
                    g = search.getG() + 1;
                    h = heuristic(x1, y1, x2, y2);
                    f = g + h;
                    pos.setY(y1 + y2);
                    pos.setX(x1 + x2);
                    pos.setG(g);
                    pos.setH(h);
                    pos.setF(f);
                    pos.setPrevY(y1);
                    pos.setPrevX(x1);
                    
                    Position openSearch = new Position();

                    for (Position item : open) {
                        if(item.getX() == x1 && item.getY() == y1){
                            openSearch = item;
                            break;
                        }
                    }

                    if(openSearch.getF() > pos.getF()){
                        open.remove(openSearch);
                        open.add(pos);
                    }else{
                        open.add(openSearch);
                    }
                }
            }

            open.sort((p1,p2)->{
                if(p1.getF() - p2.getF() > 0){
                    return -1;
                }else if(p1.getH() - p2.getH() > 0){
                    return -1;
                }
                return 0;
            });

            close.add(open.pop());
        }

        Position current = close.pop();
        path.add(current);

        Position prev = current;

        while(true){
            current = close.pop();
            if( prev.getY() != current.getPrevY() || 
                prev.getX() != current.getPrevX() ){
                continue;
            }
            path.add(prev);
            current = prev;
        }
    }

    public Boolean checkRange(int y, int x, int height, int width) {
        if(y > height){
            return false;
        }else if(x > width){
            return false;
        }else if(x < 0){
            return false;
        }else if(y < 0){
            return false;
        }
        return true;
    }


    public int getHeight() {
        return height;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    

}