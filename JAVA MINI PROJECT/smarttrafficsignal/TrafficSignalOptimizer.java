package com.mycompany.smarttrafficsignal;

import java.util.List;
import javax.swing.SwingUtilities;

public class TrafficSignalOptimizer {

    private TrafficSignal trafficSignalFrame;

    public TrafficSignalOptimizer() {
        // Initialize the traffic signal frame
        SwingUtilities.invokeLater(() -> {
            trafficSignalFrame = new TrafficSignal("Traffic Light");
            trafficSignalFrame.setSize(700, 400);
            trafficSignalFrame.setVisible(true);
        });
    }

    public void optimizeTrafficSignals(List<TrafficData> trafficDataList) {
        // Simulate signal change for demonstration
        new Thread(() -> {
            try {
                while (true) {
                    for (int i = 0; i < 3; i++) {
                        switch (i) {
                            case 0:
                                setTrafficSignalColor(i, "RED");
                                break;
                            case 1:
                                setTrafficSignalColor(i, "YELLOW");
                                break;
                            case 2:
                                setTrafficSignalColor(i, "GREEN");
                                break;
                        }
                    }
                    Thread.sleep(5000); // Change signal every 5 seconds

                    for (int i = 0; i < 3; i++) {
                        switch (i) {
                            case 0:
                                setTrafficSignalColor(i, "YELLOW");
                                break;
                            case 1:
                                setTrafficSignalColor(i, "GREEN");
                                break;
                            case 2:
                                setTrafficSignalColor(i, "RED");
                                break;
                        }
                    }
                    Thread.sleep(5000); // Change signal every 5 seconds

                    for (int i = 0; i < 3; i++) {
                        switch (i) {
                            case 0:
                                setTrafficSignalColor(i, "GREEN");
                                break;
                            case 1:
                                setTrafficSignalColor(i, "RED");
                                break;
                            case 2:
                                setTrafficSignalColor(i, "YELLOW");
                                break;
                        }
                    }
                    Thread.sleep(5000); // Change signal every 5 seconds
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void generateReport(List<TrafficData> trafficDataList) {
        System.out.println("Generating traffic report...");
        System.out.println("---------------------------------------------------------------");
        System.out.println("| Intersection Name    | Vehicle Count | Average Speed (km/h) |");
        System.out.println("---------------------------------------------------------------");

        for (TrafficData data : trafficDataList) {
            System.out.printf("| %-20s | %-13d | %-21.2f |%n",
                    data.getIntersectionName(), data.getVehicleCount(), data.getAverageSpeed());
        }

        System.out.println("---------------------------------------------------------------");
        int totalVehicles = trafficDataList.stream().mapToInt(TrafficData::getVehicleCount).sum();
        double averageSpeed = trafficDataList.stream().mapToDouble(TrafficData::getAverageSpeed).average().orElse(0.0);

        System.out.printf("Total Vehicle Count: %d%n", totalVehicles);
        System.out.printf("Average Speed Across Intersections: %.2f km/h%n", averageSpeed);

        if (totalVehicles > 100) {
            setTrafficSignalColor(0, "RED");
            setTrafficSignalColor(1, "RED");
            setTrafficSignalColor(2, "RED");
        } else if (averageSpeed < 20) {
            setTrafficSignalColor(0, "GREEN");
            setTrafficSignalColor(1, "GREEN");
            setTrafficSignalColor(2, "GREEN");
        } else if (averageSpeed > 100) {
            setTrafficSignalColor(0, "RED");
            setTrafficSignalColor(1, "RED");
            setTrafficSignalColor(2, "RED");
        } else {
            setTrafficSignalColor(0, "YELLOW");
            setTrafficSignalColor(1, "YELLOW");
            setTrafficSignalColor(2, "YELLOW");
        }
    }

    private void setTrafficSignalColor(int index, String color) {
        if (trafficSignalFrame != null) {
            SwingUtilities.invokeLater(() -> {
                switch (color) {
                    case "RED":
                        trafficSignalFrame.setRedLight(index);
                        break;
                    case "YELLOW":
                        trafficSignalFrame.setYellowLight(index);
                        break;
                    case "GREEN":
                        trafficSignalFrame.setGreenLight(index);
                        break;
                }
            });
        }
    }
}
