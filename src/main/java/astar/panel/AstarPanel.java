package astar.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import astar.Astar;

public class AstarPanel extends JPanel {
    private static final long serialVersionUID = 6336230092400053301L;

    private MapPanel mapPanel = new MapPanel();
    private MaskPanel maskPanel = new MaskPanel();

    private JLabel mapName = new JLabel();
    private JLabel maskName = new JLabel();
    private JLabel logName = new JLabel();
    private JButton btnMakeMap = new JButton();
    private JButton btnSetPath = new JButton();
    
    private JTextArea logPath = new JTextArea();
    private JScrollPane scollPath;
    
    private Astar astar;
    
    public AstarPanel() {
        astar = new Astar();
        this.setLayout(null);
        this.setComponent();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(checkClickMap(e.getY(), e.getX())){

                };

                if(checkClickMask(e.getY(), e.getX())){
                    
                }
            }
        });

        btnMakeMap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                astar.init();   

            }
        });

        btnSetPath.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                astar.proc();
            }
        });
    }

    private void setComponent() { //버튼, 칸 size, position setting
        mapName.setText("map");
        maskName.setText("mask");
        logName.setText("log : path");


        btnMakeMap.setText("init");
        btnSetPath.setText("search");
        scollPath = new JScrollPane(logPath);

        mapName.setBounds(30,20,200,20);
        mapPanel.setBounds(20,40,800,800); // map 설정

        maskName.setBounds(720, 20, 200, 20);
        maskPanel.setBounds(720, 50 ,200, 200); //mask 설정

        logName.setBounds(720, 280,200,20);
        scollPath.setBounds(720, 300, 240, 400);
        btnMakeMap.setBounds(720, 700, 80, 20);
        btnSetPath.setBounds(800, 700, 80, 20);


        this.add(mapName);
        this.add(maskName);
        this.add(logName);

        this.add(btnMakeMap);
        this.add(btnSetPath);
        this.add(scollPath);
    }

    private boolean checkClickMask(int y, int x) {
        if (y >= 800 + 40) {
            return false;
        } else if (x >= 800 + 20) {
            return false;
        } else if (x < 20) {
            return false;
        } else if (y < 40) {
            return false;
        }
		return true;
	}


	private Boolean checkClickMap(int y, int x) {
        if (y >= 720 + 200) {
            return false;
        } else if (x >= 50 + 200) {
            return false;
        } else if (x < 720) {
            return false;
        } else if (y < 50) {
            return false;
        }
        return true;
    }
}