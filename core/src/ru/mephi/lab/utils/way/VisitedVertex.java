package ru.mephi.lab.utils.way;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class VisitedVertex {
    public int[] dis; // дистанция установлена
    public int[] visited_vertex; // Используется, чтобы определить, была ли вершина посещена. 1 означает, что был, 0 означает, что не был
    public Map<String, String> pre_vertex;

    /**
     * @param index указывает начальную вершину
     * @param набор вершин вершин
     */
    public VisitedVertex(int index, String[] vertex) {
        this.visited_vertex = new int[vertex.length];
        visited_vertex[index] = 1;
        this.dis = new int[vertex.length];
        Arrays.fill(dis, Short.MAX_VALUE);
        dis[index] = 0;
        this.pre_vertex = new HashMap<>();
        for (String s : vertex) {
            pre_vertex.put(s, "" + 0);
        }
    }

    public boolean isInVisited_vertex(int index) {
        return visited_vertex[index] == 1;
    }

    public int getDis(int index) {
        return dis[index];
    }

    public int updateAll() {
        int index = 0;
        int min = Short.MAX_VALUE;
        for (int i = 0; i < visited_vertex.length; i++) {
            if (visited_vertex[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        visited_vertex[index] = 1;
        return index;
    }

    public void show() {
        System.out.println(Arrays.toString(dis));
        for (Map.Entry<String, String> entry : pre_vertex.entrySet()) {
            System.out.println("Имя вершины: " + entry.getKey() + "," + "содержит родительскую вершину: " + entry.getValue() + "");
        }
    }
}