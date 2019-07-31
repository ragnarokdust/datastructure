package astar.manage;

import astar.Astar;
import astar.panel.MapPanel;
import astar.panel.MaskPanel;

public class Factory {
    private static Factory factory = null;
    private static Astar astar = null;
    private static MapPanel mapPanel = new MapPanel();
    private static MaskPanel maskPanel = new MaskPanel();

    public static Factory getInst() {
        if(factory == null){
            factory = new Factory();
        }
        return factory;
    }

    public static Astar getAstar(){
        if(astar == null){
            astar = new Astar();
            astar.setHeight(20);
            astar.setWidth(20);
            astar.setStartY(0);
            astar.setStartX(0);
            astar.setEndY(19);
            astar.setEndX(19);
            astar.init();
        }
        return astar;
    }

    public static MapPanel getMapPanel(){
        if(mapPanel == null){
            mapPanel = new MapPanel();
        }
        return mapPanel;
    }

    public static MaskPanel getMaskPanel(){
        if(maskPanel == null){
            maskPanel = new MaskPanel();
        }
        return maskPanel;
    }

    private Factory() {
    }
}