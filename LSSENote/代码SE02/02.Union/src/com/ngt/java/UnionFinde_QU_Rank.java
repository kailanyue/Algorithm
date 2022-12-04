package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-17 15:10
 * Quick Union - 基于rank的优化
 * 1.注意当两个节点的高度不一致时，合并并不会更改节点的高度
 * 2.当两个节点的高度一样是，合并之后高度会增加1
 */
public class UnionFinde_QU_Rank extends UnionFind_QU {
    private int[] ranks;

    public UnionFinde_QU_Rank(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            ranks[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        if (ranks[p1] < ranks[p2]) {
            parents[p1] = p2;
        } else if (ranks[p1] > ranks[p2]) {
            parents[p2] = p1;
        } else {
            parents[p1] = p2;
            ranks[p2]++;
        }
    }
}
