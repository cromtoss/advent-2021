package com.safegraze.day02;

import java.util.List;

public class LocationManager {

    private int currentDepth = 0;
    private int currentHorizontalDistance = 0;
    private int currentAim = 0;

    private LocationManager() {}

    public static LocationManager make(List<String> rawNavigationLines) {
        LocationManager manager = new LocationManager();

        for(String rawNavLine: rawNavigationLines) {
            String[] parsedNavLine = rawNavLine.split(" ");
            if (parsedNavLine[0].equalsIgnoreCase("down")) {
                manager.currentAim += Integer.parseInt(parsedNavLine[1]);
            } else if (parsedNavLine[0].equalsIgnoreCase("up")) {
                manager.currentAim -= Integer.parseInt(parsedNavLine[1]);
            } else if (parsedNavLine[0].equalsIgnoreCase("forward")) {
                manager.currentHorizontalDistance += Integer.parseInt(parsedNavLine[1]);
                manager.currentDepth += manager.currentAim * Integer.parseInt(parsedNavLine[1]);
            } else {
                throw new RuntimeException("Received unknown navigational instruction!");
            }
        }

        return manager;
    }

    public int getCurrentDepth() {
        return currentDepth;
    }

    public int getCurrentHorizontalDistance() {
        return currentHorizontalDistance;
    }

    public int getCurrentAim() {
        return currentAim;
    }
}
