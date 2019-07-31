package astar.panel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import astar.Astar;
import astar.datastruct.Location;
import astar.manage.Factory;

public class AstarPanel extends JPanel {
    private static final long serialVersionUID = 6336230092400053301L;

    private Astar astar = Factory.getAstar();
    private MapPanel mapPanel = new MapPanel();
    private MaskPanel maskPanel = new MaskPanel();

    private JLabel mapName = new JLabel();
    private JLabel maskName = new JLabel();
    private JLabel logName = new JLabel();
    private JButton btnMakeMap = new JButton();
    private JButton btnSearch = new JButton();
    private JButton btnSetPath = new JButton();
    
    private JTextArea logPath = new JTextArea();
    private JScrollPane scollPath;

    
    public AstarPanel() {
        this.setLayout(null);
        this.setComponent();
    }

    private void setComponent() { //버튼, 칸 size, position setting
        
        mapName.setText("map");
        maskName.setText("mask");
        logName.setText("log : path");


        btnMakeMap.setText("init");
        btnSearch.setText("search");
        btnSetPath.setText("path");
        scollPath = new JScrollPane(logPath);

        mapName.setBounds(30,20,200,20);
        mapPanel.setBounds(20,50,800,800); // map 설정

        maskName.setBounds(880, 20, 200, 20);
        maskPanel.setBounds(880, 50 ,200, 200); //mask 설정

        logName.setBounds(880, 280,200,20);
        scollPath.setBounds(880, 300, 242, 500);
        btnMakeMap.setBounds(880, 800, 80, 20);
        btnSearch.setBounds(960, 800, 80, 20);
        btnSetPath.setBounds(1040, 800, 80, 20);
        
        logPath.setBackground(new Color(200,200,200));
        logPath.setEditable(false);
        addSearchText();

        this.add(mapName);
        this.add(maskName);
        this.add(logName);

        this.add(btnMakeMap);
        this.add(btnSetPath);
        this.add(btnSearch);
        this.add(scollPath);

        this.add(mapPanel);
        this.add(maskPanel);

        btnMakeMap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                astar.init();   
                mapPanel.repaint();
                logPath.setText("");
                addSearchText();
            }
        });

        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logPath.append("   --------------VISITED--------------\n");
                new Thread(() -> {
                    while (astar.getSearched().size() != 0) {
                        try {
                            Location node = astar.setVisited();
                            logPath.append("  "+node.toString()+"\n");
                            if (node.getX() == astar.getEndX() && node.getY() == astar.getEndY())
                                break;
                            mapPanel.repaint();
                            Thread.sleep(500);
                        } catch (Exception ee) {
                            
                        }
                    }
                    
                }).start();
                
            }
        });

        btnSetPath.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                astar.setPath();
                mapPanel.repaint();
                addPathText();
            }
        });
        
    }

    void addSearchText(){
        logPath.append("   -------------INIT SEARCHED-------------\n");
        for (Location loc : astar.getSearched()) {
            logPath.append("  "+loc.toString()+"\n");
        }
    }

    void addPathText(){
        logPath.append("   -----------------PATH------------------\n");  
        for (Location loc : astar.getPath()) {
            logPath.append("  "+loc.toString()+"\n");
        }
    }
}