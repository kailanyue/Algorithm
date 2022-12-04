package com.ngt.string;

/**
 * @author ngt
 * @create 2020-08-31 22:31
 */
public class KMP {
    public static int indexOf(String text, String pattren) {
        if (text == null || pattren == null) return -1;
        char[] textChars = text.toCharArray();
        char[] pattrenChars = pattren.toCharArray();
        int tlen = textChars.length;
        int plen = pattrenChars.length;
        if (tlen == 0 || plen == 0 || tlen < plen) return -1;

        int[] next = next(pattren);
        int pi = 0, ti = 0, lenDelta = tlen - plen;
        while (pi < plen && ti - pi <= lenDelta) {
            if (pi < 0 || textChars[ti] == pattrenChars[pi]) {
                ti++;
                pi++;
            } else {
                pi = next[pi];
            }
        }
        return (pi == plen) ? (ti - pi) : -1;
    }

    public static int[] next(String pattern) {
        char[] chars = pattern.toCharArray();
        int[] next = new int[chars.length];

        next[0] = -1;
        int i = 0;
        int n = -1;
        int iMax = chars.length - 1;
        while (i < iMax) {
            if (n < 0 || chars[i] == chars[n]) {
                next[++i] = ++n;
            } else {
                n = next[n];
            }
        }
        return next;
    }
}
