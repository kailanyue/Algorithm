package com.ngt.graph;

import org.junit.Test;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.*;

/**
 * @author ngt
 * @create 2020-08-17 23:51
 */
public class ListGraph<V, E> extends Graph<V, E> {

    public ListGraph() {

    }

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();
    private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {
        return weightManager.compare(e1.weight, e2.weight);
    };

    public static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            return Objects.equals(value, ((Vertex<V, E>) obj).value);
        }

        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        EdgeInfo<V, E> info() {
            return new EdgeInfo<>(from.value, to.value, weight);
        }


        @Override
        public boolean equals(Object obj) {
            Edge<V, E> edge = (Edge<V, E>) obj;
            return Objects.equals(this.from, edge.from) && Objects.equals(this.to, edge.to);
        }

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }

        @Override
        public String toString() {
            return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
        }
    }

    public void print() {
        System.out.println("[顶点]-------------------");
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            System.out.println(v);
            System.out.println("out-----------");
            System.out.println(vertex.outEdges);
            System.out.println("in-----------");
            System.out.println(vertex.inEdges);
        });

        System.out.println("[边]-------------------");
        edges.forEach((Edge<V, E> edge) -> {
            System.out.println(edge);
        });
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;

        for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            // 将当前遍历到的元素edge从集合vertex.outEdges中删掉
            edge.to.inEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }
        for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            // 将当前遍历到的元素edge从集合vertex.inEdges中删掉
            edge.from.outEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }


    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) return;
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (vertices == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();

        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            if (visitor.visit(vertex.value)) return;

            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                queue.offer(edge.to);
                visitedVertices.add(edge.to);
            }
        }

    }

    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) return;
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();

        // 先访问起点
        stack.push(beginVertex);
        visitedVertices.add(beginVertex);
        if (visitor.visit(begin)) return;

        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();

            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;

                stack.push(edge.from);
                stack.push(edge.to);
                visitedVertices.add(edge.to);
                if (visitor.visit(edge.to.value)) return;

                break;
            }
        }
    }


    public void bfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (vertices == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();

        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            System.out.println(vertex.value);
            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                queue.offer(edge.to);
                visitedVertices.add(edge.to);
            }
        }
    }

    @Override
    public List<V> topologicalSort() {
        List<V> list = new ArrayList<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Map<Vertex<V, E>, Integer> ins = new HashMap<>();

        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int in = vertex.inEdges.size();
            if (in == 0) queue.offer(vertex);
            else ins.put(vertex, in);
        });

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, E> edge : vertex.outEdges) {
                int toIn = ins.get(edge.to) - 1;
                if (toIn == 0) {
                    queue.offer(edge.to);
                } else {
                    ins.put(edge.to, toIn);
                }
            }
        }

        return list;
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return Math.random() > 0.5 ? prim() : kruskal();
    }

    private Set<EdgeInfo<V, E>> prim() {
        Iterator<Vertex<V, E>> it = vertices.values().iterator();
        if (!it.hasNext()) return null;
        Vertex<V, E> vertex = it.next();
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        Set<Vertex<V, E>> addedVertices = new HashSet<>();
        addedVertices.add(vertex);
        MinHeap<Edge<V, E>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);
        int verticesSize = vertices.size();
        while (!heap.isEmpty() && addedVertices.size() < verticesSize) {
            Edge<V, E> edge = heap.remove();
            if (addedVertices.contains(edge.to)) continue;
            edgeInfos.add(edge.info());
            addedVertices.add(edge.to);
            heap.addAll(edge.to.outEdges);
        }
        return edgeInfos;
    }

    private Set<EdgeInfo<V, E>> kruskal() {
        int edgeSize = vertices.size() - 1;
        if (edgeSize == -1) return null;
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator);
        UnionFind<Vertex<V, E>> uf = new UnionFind<>();
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            uf.makeSet(vertex);
        });
        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, E> edge = heap.remove();
            if (uf.isSame(edge.from, edge.to)) continue;
            edgeInfos.add(edge.info());
            uf.union(edge.from, edge.to);
        }
        return edgeInfos;
    }

    public void dfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (begin == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();

        stack.push(beginVertex);
        visitedVertices.add(beginVertex);
        System.out.println(beginVertex.value);
        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);
                visitedVertices.add(edge.to);
                System.out.println(edge.to.value);
                break;
            }
        }
    }

    public void dfs2(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (begin == null) return;
        dfs2(beginVertex, new HashSet<>());
    }

    public void dfs2(Vertex<V, E> vertex, Set<Vertex<V, E>> visitedVertices) {
        System.out.println(vertex.value);
        visitedVertices.add(vertex);
        for (Edge<V, E> edge : vertex.outEdges) {
            if (visitedVertices.contains(edge.to)) continue;
            dfs2(edge.to, visitedVertices);
        }
    }

}
