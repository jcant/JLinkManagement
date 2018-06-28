package com.gmail.gm.jcant.JLinkManagement.Helpers;

public class JSymbolsHelper {
    public static boolean isContainSpecialSymbols(String sample, boolean fullControl) {
        String[] checkers = new String[] { " ", ".", ",", "!", "?", "_", "+", "-", "*", "~", "`", "@", "#", "$", "%",
                "^", "&", "\"", "(", ")", "'", "|", "\\", "/" };
        String[] lightCheckers = new String[] { " ", ",", "`", "'" };

        String[] work;
        if (fullControl) {
            work = checkers;
        } else {
            work = lightCheckers;
        }

        for (String item : work) {
            if (sample.contains(item)) {
                return true;
            }
        }

        return false;
    }
}
