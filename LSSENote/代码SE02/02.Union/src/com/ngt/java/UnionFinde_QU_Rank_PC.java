package com.ngt.java;

/**
 * @author ngt
 * @create 2020-08-17 15:46
 * Quick Union - 基于rank的优化 - 路径压缩(Path Compression)
 * 1. 使路径上的所有节点都指向根节点
 * 2. 由于在find中使用了 递归算法会抵消掉路径压缩带来的性能提升
 * 3. 注意find的返回值不能返回v，只能返回parents[v]
 */
public class UnionFinde_QU_Rank_PC extends UnionFinde_QU_Rank {
    public UnionFinde_QU_Rank_PC(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        if (parents[v] != v) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
