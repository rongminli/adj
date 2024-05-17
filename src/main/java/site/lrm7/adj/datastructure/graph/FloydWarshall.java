package site.lrm7.adj.datastructure.graph;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FloydWarshall {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(
                new Edge(v3, -2)
        );
        v2.edges = List.of(
                new Edge(v1, 4),
                new Edge(v3, 3)
        );
        v3.edges = List.of(
                new Edge(v4, 2)
        );
        v4.edges = List.of(
                new Edge(v2, -1)
        );

        List<Vertex> graph = List.of(v1, v2, v3, v4);

        floydWarshall(graph);
    }

    private static void floydWarshall(List<Vertex> graph) {
        int size = graph.size();
        int[][] dist = new int[size][size];
        Vertex[][] prev = new Vertex[size][size];
        for (int i = 0; i < size; i++) {
            Vertex v = graph.get(i);
            Map<Vertex, Integer> map = v.edges.stream().collect(Collectors.toMap(e -> e.linked, e -> e.weight));
            // 1.初始化
            for (int j = 0; j < size; j++) {
                Vertex u = graph.get(j);
                if (v == u) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = map.getOrDefault(u, Vertex.INF);
                    prev[i][j] = map.get(u) != null ? v : null;
                }
            }
        }
//        print(dist);
        print(prev);

        // 2.看能否借路到达其他顶点。
        /*
            v2->v1
            dist[1][0]
         */
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist[i][k] != Vertex.INF && dist[k][j] != Vertex.INF) {
                        int i1 = dist[i][k] + dist[k][j];
                        if (i1 < dist[i][j]) {
                            dist[i][j] = i1;
                            prev[i][j] = prev[k][j];
                        }
                    }
                }
            }
//            print(dist);
        }
        print(prev);
    }

    private static void print(int[][] dist) {
        System.out.println("-------------------");
        for (int[] row : dist) {
            System.out.println(Arrays.stream(row).boxed()
                    .map(x -> x.equals(Vertex.INF) ? "M" : String.valueOf(x))
                    .map(s -> String.format("%2s", s))
                    .collect(Collectors.joining(",", "[", "]"))
            );
        }
    }

    private static void print(Vertex[][] dist) {
        System.out.println("-------------------");
        for (Vertex[] row : dist) {
            System.out.println(Arrays.stream(row)
                    .map(x -> x == null ? "null" : x.name)
                    .map(s -> String.format("%6s", s))
                    .collect(Collectors.joining(",", "[", "]"))
            );
        }
    }
}
