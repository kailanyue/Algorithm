package com.ngt.graph;

import java.util.List;
import java.util.Set;

/**
 * @author ngt
 * @create 2020-08-17 23:46
 */
public abstract class Graph<V, E> {
    public  WeightManager<E> weightManager;
    public Graph(){}

    public Graph(WeightManager<E> weightManager){
        this.weightManager = weightManager;
    }
    public abstract int edgesSize();

    public abstract int verticesSize();

    public abstract void addVertex(V v);

    public abstract void addEdge(V from, V to, E weight);

    public abstract void addEdge(V from, V to);

    public abstract void removeVertex(V v);

    public abstract void removeEdge(V from, V to);

    public abstract void bfs(V begin, VertexVisitor<V> visitor);

    public abstract void dfs(V begin, VertexVisitor<V> visitor);

    public abstract List<V> topologicalSort();//返回AOV网络的路径

    public abstract Set<EdgeInfo<V, E>> mst(); // 最小生成树

    public interface VertexVisitor<V> {
        boolean visit(V v);
    }

    public interface WeightManager<E>{
        int compare(E w1, E w2);
        E add(E w1, E w2);
    }

    public static class EdgeInfo<V, E> {
        private V from;
        private V to;
        private E weight;

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EdgeInfo [from=" + from + ", to=" + to + ", weight=" + weight + "]";
        }
    }
}
