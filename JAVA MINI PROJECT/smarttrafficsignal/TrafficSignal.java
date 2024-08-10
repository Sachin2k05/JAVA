package com.mycompany.smarttrafficsignal;

import java.awt.*;
import javax.swing.*;

public class TrafficSignal extends JFrame {

    private TrafficLightPanel[] trafficLightPanels;
    private JTextField messageField;

    public TrafficSignal(String title) {
        super(title);
        setLayout(new BorderLayout());

        // Initialize the traffic light panels
        trafficLightPanels = new TrafficLightPanel[3];
        JPanel panel = new JPanel(new GridLayout(1, 3));
        for (int i = 0; i < 3; i++) {
            trafficLightPanels[i] = new TrafficLightPanel();
            panel.add(trafficLightPanels[i]);
        }
        add(panel, BorderLayout.CENTER);

        // Initialize message field
        messageField = new JTextField(20);
        messageField.setEditable(false);
        add(messageField, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Adjusts the frame size to fit the components
    }

    public void setRedLight(int index) {
        trafficLightPanels[index].setLight("RED");
        messageField.setText("Stop!");
    }

    public void setYellowLight(int index) {
        trafficLightPanels[index].setLight("YELLOW");
        messageField.setText("Get Ready to Go!");
    }

    public void setGreenLight(int index) {
        trafficLightPanels[index].setLight("GREEN");
        messageField.setText("Go!");
    }

    private class TrafficLightPanel extends JPanel {
        private String lightColor = "OFF";

        public TrafficLightPanel() {
            setPreferredSize(new Dimension(200, 300));
        }

        public void setLight(String color) {
            this.lightColor = color;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the traffic light frame
            g2d.setColor(Color.BLACK);
            g2d.fillRoundRect(50, 20, 100, 260, 15, 15);
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawRoundRect(50, 20, 100, 260, 15, 15);

            // Draw the lights
            drawLight(g2d, 75, 40, 60, "RED");
            drawLight(g2d, 75, 120, 60, "YELLOW");
            drawLight(g2d, 75, 200, 60, "GREEN");
        }

        private void drawLight(Graphics2D g2d, int x, int y, int diameter, String color) {
            g2d.setColor(Color.BLACK);
            g2d.fillOval(x, y, diameter, diameter);

            if ("RED".equals(lightColor) && color.equals("RED")) {
                g2d.setColor(Color.RED);
            } else if ("YELLOW".equals(lightColor) && color.equals("YELLOW")) {
                g2d.setColor(Color.YELLOW);
            } else if ("GREEN".equals(lightColor) && color.equals("GREEN")) {
                g2d.setColor(Color.GREEN);
            } else {
                g2d.setColor(Color.GRAY); // Default color for lights that are off
            }

            g2d.fillOval(x + 2, y + 2, diameter - 4, diameter - 4); // Draw a smaller circle to simulate the light
        }
    }
}
