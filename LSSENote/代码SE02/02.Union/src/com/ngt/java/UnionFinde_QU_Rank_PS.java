package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-17 16:23
 * Quick Union - 基于rank的优化 - 路径分裂(Path Spliting)
 * 1. 每个节点都指向其祖父节点
 */
public class UnionFinde_QU_Rank_PS extends UnionFinde_QU_Rank {
    public UnionFinde_QU_Rank_PS(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            int p = parents[v];
            parents[v] = parents[parents[v]];
            v = p;
        }
        return v;
    }
}
