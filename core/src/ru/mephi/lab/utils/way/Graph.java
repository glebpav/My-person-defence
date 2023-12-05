package ru.mephi.lab.utils.way;

import java.util.Arrays;

class Graph {
    private String[] vertex; // Набор вершин
    private int[][] matrix; // Матрица смежности
    private VisitedVertex vv;

    public Graph(String[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void show() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void dijkstra(int index) {
        vv = new VisitedVertex(index, vertex);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            int value = vv.updateAll();
            update(value);
        }
        vv.show();
    }

    public void update(int index) {
        int len = 0;
        for (int i = 0; i < vertex.length; i++) {
            len = vv.getDis(index) + matrix[index][i];
            if (!vv.isInVisited_vertex(i) && len < vv.getDis(i)) {
                vv.dis[i] = len;
                vv.pre_vertex.put(vertex[i], "" + (index + 1));
            } else if (!vv.isInVisited_vertex(i) && len == vv.getDis(i)) {
                String value = vv.pre_vertex.get(vertex[i]);
                vv.pre_vertex.put(vertex[i], "" + value + "," + (index + 1));
            }
        }
    }

}
