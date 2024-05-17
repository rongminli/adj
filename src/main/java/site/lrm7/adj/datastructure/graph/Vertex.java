package site.lrm7.adj.datastructure.graph;

import java.util.List;
import java.util.Objects;

/**
 * 顶点
 */
public class Vertex {
    String name;
    List<Edge> edges;
    boolean visited = false;
    int inDegree = 0;
    int status; // 状态 0-未访问， 1-访问中， 2-访问过。
    int dist = INF; // 距离
    Vertex prev = null;

    static final Integer INF = Integer.MAX_VALUE;

    public Vertex(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        a.edges = List.of(new Edge(b), new Edge(c));
        b.edges = List.of(new Edge(d));
        c.edges = List.of(new Edge(d));
        d.edges = List.of();
    }


    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                ", dist=" + dist +
                ", prev=" + prev +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return vertex.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
