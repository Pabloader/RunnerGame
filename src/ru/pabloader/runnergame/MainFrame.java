package ru.pabloader.runnergame;

import javax.swing.JFrame;

/**
 *
 * @author P@bloid
 */
public class MainFrame extends JFrame implements Runnable {
    private static MainFrame mainFrame;
    private static MainPanel mainPanel;

    public static void main(String[] args) {
        mainFrame = new MainFrame();
        mainFrame.setSize(800, 600);
        mainFrame.setTitle("Runner Game");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainPanel = new MainPanel();
        mainFrame.add(mainPanel);
        mainFrame.addKeyListener(mainPanel);
        mainFrame.setVisible(true);
        mainPanel.init();
        new Thread(mainFrame).start();
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    @Override
    public void run() {
        try {
            while (true)
                mainFrame.repaint();
        } catch (Exception e) {
        }
    }
}
