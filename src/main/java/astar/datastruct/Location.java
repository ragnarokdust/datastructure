package astar.datastruct;

public class Location {
    private int y;
    private int x;
    private Location prev;

    private int f;
    private int g;
    private int h;

    public int getY() {
        return y;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Location getPrev() {
        return prev;
    }

    public void setPrev(Location prev) {
        this.prev = prev;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Location(int y, int x){
        setX(x);
        setY(y);
        setF(0);
        setG(0);
        setH(0);
        prev = null;
    }

    @Override
    public String toString() {
        return "y : " + y + " x : " + x;
    }
}