package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-17 16:23
 * Quick Union - 基于rank的优化 - 路径减半(Path Halving)
 * 1. 每间隔一个节点指向其祖父节点
 */
public class UnionFinde_QU_Rank_PH extends UnionFinde_QU_Rank {
    public UnionFinde_QU_Rank_PH(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}
