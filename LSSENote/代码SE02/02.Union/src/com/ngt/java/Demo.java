package com.ngt.java;

import com.ngt.java.tools.Asserts;
import com.ngt.java.tools.Times;

/**
 * @author ngt
 * @create 2020-08-17 13:56
 */
public class Demo {
    static final int count = 1000000;
    public static void main(String[] args) {
        test(new UnionFind_QF(count));
        test(new UnionFind_QU(count));
        test(new UnionFinde_QU_Size(count));
        test(new UnionFinde_QU_Rank(count));
        test(new UnionFinde_QU_Rank_PC(count));

//        testTime(new UnionFind_QF(count));
//        testTime(new UnionFind_QU(count));
        testTime(new UnionFinde_QU_Size(count));
        testTime(new UnionFinde_QU_Rank(count));
        testTime(new UnionFinde_QU_Rank_PC(count));

    }

    public static void test(UnionFind uf){
        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        uf.union(6, 7);

        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);

        Asserts.test(!uf.isSame(2, 7));

        uf.union(4, 6);

        Asserts.test(uf.isSame(2, 7));
    }

    public static void testTime(UnionFind uf){
        Times.test(uf.getClass().getName(), ()->{
            for (int i = 0; i < count; i++) {
                uf.union((int)(Math.random() * count),
                        (int)(Math.random() * count));
            }

            for (int i = 0; i < count; i++) {
                uf.isSame((int)(Math.random() * count),
                        (int)(Math.random() * count));
            }
        });
    }
}
