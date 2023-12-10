package ru.mephi.lab.utils.way;

import java.util.ArrayList;
import java.util.Collections;

class GraphShortestPath {

    // find a vertex with minimum distance
    int minDistance(int[] pathArray, Boolean[] sptSet) {
        // Initialize min value
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < pathArray.length; v++)
            if (!sptSet[v] && pathArray[v] <= min) {
                min = pathArray[v];
                minIndex = v;
            }

        return minIndex;
    }

    ArrayList<Integer> dijkstra(int[][] graph, int srcNode, int endNode) {
        int[] pathArray = new int[graph.length];
        int[] nodeIdxFrom = new int[graph.length];
        Boolean[] sptSet = new Boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            pathArray[i] = Integer.MAX_VALUE;
            nodeIdxFrom[i] = -1;
            sptSet[i] = false;
        }

        pathArray[srcNode] = 0;
        pathArray[srcNode] = srcNode;

        for (int count = 0; count < graph.length - 1; count++) {
            int u = minDistance(pathArray, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < graph.length; v++)
                if (!sptSet[v] && graph[u][v] != 0 && pathArray[u] != Integer.MAX_VALUE && pathArray[u] + graph[u][v] < pathArray[v]) {
                    pathArray[v] = pathArray[u] + graph[u][v];
                    nodeIdxFrom[v] = u;
                }
        }

        ArrayList<Integer> path = new ArrayList<>();

        int nodeIdx = endNode;
        while (nodeIdx != srcNode) {
            path.add(nodeIdx);
            nodeIdx = nodeIdxFrom[nodeIdx];
            if (nodeIdx == -1) {
                path.add(-1);
                break;
            }
        }
        System.out.println("path: " + path);

        if (path.contains(-1)) return null;
        Collections.reverse(path);
        return path;
    }
}