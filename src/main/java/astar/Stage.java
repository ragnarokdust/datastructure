package astar;

public class Stage {

    Astar astar;
    
    public void init() {
        astar = new Astar();
        astar.setHeight(120);
        astar.setWidth(120);
        astar.setEndY(100);
        astar.setEndX(100);
        
        astar.init();
    }    

    public void proc() {
        astar.proc();
    }
    
    public void render() {
        
    }
    public static void main(String[] args) {
    }
}