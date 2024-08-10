package com.mycompany.smarttrafficsignal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class SmartTrafficSignalApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<TrafficData> trafficDataList = new ArrayList<>();
        
        // Collect traffic data
        System.out.println("Enter traffic data:");
        while (true) {
            System.out.print("Enter vehicle count (or type 'done' to finish): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) break;

            int vehicleCount = Integer.parseInt(input);

            System.out.print("Enter average speed: ");
            double averageSpeed = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter intersection name: ");
            String intersectionName = scanner.nextLine();

            trafficDataList.add(new TrafficData(vehicleCount, averageSpeed, intersectionName));
            System.out.println("Traffic data added. Enter more data or type 'done' to finish.");
        }

        TrafficSignalOptimizer optimizer = new TrafficSignalOptimizer();

        System.out.println("Optimizing traffic signals...");
        optimizer.optimizeTrafficSignals(trafficDataList);

        System.out.println("Traffic signals optimized.");

        System.out.println("Enter 'report' to generate a traffic report, 'visualize' to visualize traffic signals, 'exit' to quit.");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;
            if (input.equalsIgnoreCase("report")) {
                optimizer.generateReport(trafficDataList);
            } else if (input.equalsIgnoreCase("visualize")) {
                SwingUtilities.invokeLater(() -> new TrafficSignal("Traffic Light").setVisible(true));
            }
        }

        scanner.close();
    }
}
