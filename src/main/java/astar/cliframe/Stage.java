package astar.cliframe;

import astar.Astar;

public class Stage {

    Astar astar;
    
    public void init() {
        astar = new Astar();
        astar.setHeight(10);
        astar.setWidth(10);
        astar.setStartY(0);
        astar.setStartX(0);
        astar.setEndY(9);
        astar.setEndX(9);
        astar.init();
    }    

    public void proc() {
        astar.proc();
    }
    
    public void render() {
        astar.render();
    }
    public static void main(String[] args) {
        Stage stage = new Stage();
        stage.init();
        stage.proc();
        stage.render();
    }
}