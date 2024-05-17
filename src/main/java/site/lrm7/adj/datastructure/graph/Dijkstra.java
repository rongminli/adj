package site.lrm7.adj.datastructure.graph;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 迪克斯特拉（Dijkstra）单源最短路径算法是一种用于寻找图中某个顶点到其余所有顶点的最短路径的算法。该算法是以荷兰计算机科学家Edsger W. Dijkstra的名字命名的。

 该算法的基本思想是从起始节点开始，依次找到离起始节点最近的未访问过的节点，并将其加入到已访问节点集合中。在每次迭代中，更新当前已访问节点的相邻节点的距离，直到所有节点都被访问过为止。

 算法的具体实现如下：

 1. 从起始节点开始，建立一个优先队列，将所有节点按照距离起始节点的距离从小到大排序。将起始节点加入已访问节点集合中。

 2. 从优先队列中取出距离起始节点最近的一个节点，将其标记为已访问。

 3. 遍历当前节点的所有邻居节点，如果该邻居节点的距离比当前路径更短，则将该邻居节点加入优先队列中，并将优先队列重新排序。

 4. 重复执行第2步和第3步，直到所有节点都被访问过为止。

 最终，返回已访问节点集合中的最后一个节点就是目标节点，而返回的距离即为起始节点到目标节点的最短距离。

 需要注意的是，该算法只适用于无负权边和无环图的情况。在实际应用中，可以通过增加剪枝操作等方式来适应负权边和有环图的情况。
 */
public class Dijkstra {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(
                new Edge(v3, 9),
                new Edge(v6, 14),
                new Edge(v2, 7)
        );
        v3.edges = List.of(
                new Edge(v4, 11),
                new Edge(v6, 2)
        );
        v2.edges = List.of(
                new Edge(v4, 15)
        );
        v6.edges = List.of(
                new Edge(v5, 9)
        );
        v4.edges = List.of(
                new Edge(v5, 6)
        );
        v5.edges = List.of();

        List<Vertex> graph = List.of(v1, v2, v3, v4 ,v5 , v6);

        dijkstra(graph, v1);
    }

    private static void dijkstra(List<Vertex> graph, Vertex source) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.dist));
        source.dist = 0;
        for (Vertex v : graph) {
            queue.offer(v);
        }
        while(!queue.isEmpty()) {
            // 1. 选取当前节点
            Vertex curr = queue.peek();
            // 2. 更新当前顶点邻居距离
            if(!curr.visited) {
                updateNeighboursDist(curr, queue);
                curr.visited = true;
            }
            // 3. 移除当前顶点
            queue.poll();
        }

        for (Vertex vertex : graph) {
            System.out.println(vertex.name + " " + vertex.dist + " " + (vertex.prev!=null?vertex.prev.name:"null"));
        }
    }

    private static void updateNeighboursDist(Vertex curr, PriorityQueue<Vertex> queue) {
        for (Edge edge : curr.edges) {
            Vertex n = edge.linked;
            if(!n.visited) {
                int dist = curr.dist + edge.weight;
                if(dist < n.dist) {
                    n.dist = dist;
                    n.prev = curr;
                    queue.offer(n);
                }
            }
        }
    }
}
