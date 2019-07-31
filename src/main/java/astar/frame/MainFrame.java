package astar.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import astar.panel.AstarPanel;

/**
 * MainFrame
 */
public class MainFrame extends JFrame {
    private static final long serialVersionUID = -6266536651643285629L;
    private JPanel panel;
    public MainFrame() {
        panel=new AstarPanel();
        
        this.setTitle("AStar");
        this.setResizable(false);
        this.setSize(1200,900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new MainFrame();
    }
}