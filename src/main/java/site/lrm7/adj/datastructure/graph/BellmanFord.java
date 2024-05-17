package site.lrm7.adj.datastructure.graph;

import java.util.List;

/**
 *  <h3>Bellman-Ford算法， 可以处理副边</h3>
 */
public class BellmanFord {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(
                new Edge(v3, 1),
                new Edge(v2, 2)
        );
        v3.edges = List.of(
                new Edge(v4, 1)
        );
        v2.edges = List.of(
                new Edge(v3, -2)
        );

        v4.edges = List.of();

        List<Vertex> graph = List.of(v1, v2, v3, v4);

        bellmanFord(graph, v1);
    }

    private static void bellmanFord(List<Vertex> graph, Vertex source) {
        source.dist = 0;
        int size = graph.size();
        for (int i = 0; i < size - 1 ; i++) {
            for (Vertex s : graph) {
                for (Edge edge : s.edges) {
                    Vertex e = edge.linked;
                    if (s.dist != Vertex.INF && s.dist + edge.weight < e.dist) {
                        e.dist = s.dist + edge.weight;
                        e.prev = s;
                    }
                }
            }
        }

        for (Vertex v : graph) {
            System.out.println(v);
        }

    }
}
