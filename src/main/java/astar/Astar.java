package astar;

import java.util.Collections;
import java.util.LinkedList;

import astar.datastruct.Cell;
import astar.datastruct.Location;

public class Astar {
    private int height;
    private int width;
    private int startY;
    private int startX;
    private int endY;
    private int endX;

    private Cell[][] cell;
    // private int[][] mask = { { 0, 1, 0 }, { 1, 0, 1 }, { 0, 1, 0 }, };
    private int[][] mask = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 }, };
    private int maskSize = 3;

    private LinkedList<Location> searched = new LinkedList<>();
    private LinkedList<Location> visited = new LinkedList<>();
    private LinkedList<Location> path = new LinkedList<>();

    public void init() {
        generate();
    }

    public void proc() {
        while (searched.size() != 0) {
            Location node = setVisited();
            if (node.getX() == endX && node.getY() == endY)
                break;
        }
        setPath();
    }

    // console service
    public void render() {

        for (int i = 0; i < width * 5; i++) {
            System.out.print("→ ");
        }
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.print("↓ ");
            for (int j = 0; j < width; j++) {
                System.out.print(cell[i][j].getStatus().name() + "\t\t");
            }
            System.out.println();
        }

        for (Location location : path) {
            if (location == null) {
                break;
            }
            System.out.println(location.toString());
        }
    }

    public Location setVisited() {
        Location node = searchNode();
        searched.remove(node);
        visited.add(node);
        setSearched(node);              
        return node;
    }

    public void setPath() {
        if (visited.size() == 0) {
            return;
        }
        Location location = visited.removeLast();
        path.add(location);

        while (true) {
            location = location.getPrev();
            if(location == null)
                break;
            path.add(location);
        }
        Collections.reverse(path);
    }

    private Location searchNode() {
        searched.sort((p1, p2) -> p1.getF() - p2.getF());
        return searched.pop();
    }

    // 마스크 search
    private void setSearched(Location node) {
        for (int i = 0; i < maskSize; i++) {
            for (int j = 0; j < maskSize; j++) {
                if (mask[i][j] == 0) {
                    continue;
                }
                int y = node.getY() - (i - maskSize / 2);
                int x = node.getX() - (j - maskSize / 2);

                if (!checkLocation(y, x, height, width)) {
                    continue;
                }

                if (!checkBarrier(y, x)) {
                    continue;
                }

                if (!checkVisited(y, x)) {
                    continue;
                }

                int h = getH(y, x, endY, endX);
                int g = getG(y, x, node);
                int f = g + h;

                if (!checkSearched(y, x, f)) {
                    continue;
                }

                Location location = new Location(y, x);
                location.setH(h);
                location.setG(g);
                location.setF(f);
                location.setPrev(node);
                searched.add(location);
            }
        }
    }

    private int getH(int y1, int x1, int y2, int x2) {
        return (Math.abs(x1 - x2) + Math.abs(y1 - y2)) * 10;
    }

    private int getG(int y, int x, Location node) {
        int g = node.getG();
        g += (node.getX() - x == 0 || node.getY() - y == 0) ? 10 : 14;
        return g;
    }

    private Boolean checkSearched(int y, int x, int f) {
        for (Location location : searched) {
            if (location.getY() == y && location.getX() == x) {
                if (location.getF() < f) {
                    return false;
                } else {
                    searched.remove(location);
                    return true;
                }
            }
        }
        return true;
    }

    private Boolean checkBarrier(int y, int x) {
        if (cell[y][x].getStatus() == Cell.Status.BARRIER) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean checkVisited(int y, int x) {
        for (Location location : visited) {
            if (location.getY() == y && location.getX() == x) {
                return false;
            }
        }
        return true;
    }

    private Boolean checkLocation(int y, int x, int height, int width) {
        if (y >= height) {
            return false;
        } else if (x >= width) {
            return false;
        } else if (x < 0) {
            return false;
        } else if (y < 0) {
            return false;
        }
        return true;
    }

    private void generate() {
        cell = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cell[i][j] = new Cell();
                cell[i][j].setStatus(Cell.Status.EMPTY);
            }
        }

        for (int i = 0; i < 150; i++) {
            int y = (int) (Math.random() * height);
            int x = (int) (Math.random() * width);
            cell[y][x].setStatus(Cell.Status.BARRIER);
        }

        cell[startY][startX].setStatus(Cell.Status.START);
        cell[endY][endX].setStatus(Cell.Status.END);

        searched = new LinkedList<>();
        visited = new LinkedList<>();
        path = new LinkedList<>();
        
        Location start = new Location(startY, startX);
        visited.add(start);
        setSearched(start);
        
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

    public Cell[][] getCell() {
        return cell;
    }

    public int[][] getMask() {
        return mask;
    }

    public LinkedList<Location> getSearched(){
        return searched;
    }
    public LinkedList<Location> getVisited(){
        return visited;
    }
    public LinkedList<Location> getPath(){
        return path;
    }
}