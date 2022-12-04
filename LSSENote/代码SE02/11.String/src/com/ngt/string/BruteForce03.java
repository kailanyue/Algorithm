package com.ngt.string;

/**
 * @author ngt
 * @create 2020-08-30 15:43
 */
public class BruteForce03 {
    public static int indexOf(String text, String pattren) {
        if (text == null || pattren == null) return -1;
        char[] textChars = text.toCharArray();
        char[] pattrenChars = pattren.toCharArray();
        int tlen = textChars.length;
        int plen = pattrenChars.length;
        if (tlen == 0 || plen == 0 || tlen < plen) return -1;
        int tiMax = tlen - plen;
        for (int ti = 0; ti <= tiMax; ti++) {
            int pi = 0;
            for (; pi < plen; pi++) {
                if (textChars[ti + pi] != pattrenChars[pi]) break;
            }
            if (pi == plen) return ti;
        }
        return -1;
    }
}
