package com.codegym.aurora.util;

import java.util.HashMap;
import java.util.Map;

public class NumeroloryConstants {
    public static final Map<Character, Integer> alphabetMap = new HashMap<>();

    static {
        alphabetMap.put('A', 1);
        alphabetMap.put('B', 2);
        alphabetMap.put('C', 3);
        alphabetMap.put('D', 4);
        alphabetMap.put('E', 5);
        alphabetMap.put('F', 6);
        alphabetMap.put('G', 7);
        alphabetMap.put('H', 8);
        alphabetMap.put('I', 9);
        alphabetMap.put('J', 1);
        alphabetMap.put('K', 2);
        alphabetMap.put('L', 3);
        alphabetMap.put('M', 4);
        alphabetMap.put('N', 5);
        alphabetMap.put('O', 6);
        alphabetMap.put('P', 7);
        alphabetMap.put('Q', 8);
        alphabetMap.put('R', 9);
        alphabetMap.put('S', 1);
        alphabetMap.put('T', 2);
        alphabetMap.put('U', 3);
        alphabetMap.put('V', 4);
        alphabetMap.put('W', 5);
        alphabetMap.put('X', 6);
        alphabetMap.put('Y', 7);
        alphabetMap.put('Z', 8);
    }

    //Bảng chữ cái nguyên âm trong trường hợp trước hoặc sau y là 1 nguyên âm
    public static final Map<Character, Integer> vowelLettersMap = new HashMap<>();

    static {
        vowelLettersMap.put('A', 1);
        vowelLettersMap.put('E', 5);
        vowelLettersMap.put('I', 9);
        vowelLettersMap.put('O', 6);
        vowelLettersMap.put('U', 3);
    }
    //Bảng nguyên âm trong trường hợp được xem là 1 nguyên âm
    public static final Map<Character, Integer> vowelLettersMapHaveY = new HashMap<>();

    static {
        vowelLettersMapHaveY.put('A', 1);
        vowelLettersMapHaveY.put('E', 5);
        vowelLettersMapHaveY.put('I', 9);
        vowelLettersMapHaveY.put('O', 6);
        vowelLettersMapHaveY.put('U', 3);
        vowelLettersMapHaveY.put('Y', 7);
    }

    //Bảng phụ âm
    public static final Map<Character, Integer> consonantLettersMapHaveY = new HashMap<>();

    static {
        consonantLettersMapHaveY.put('B', 2);
        consonantLettersMapHaveY.put('C', 3);
        consonantLettersMapHaveY.put('D', 4);
        consonantLettersMapHaveY.put('F', 6);
        consonantLettersMapHaveY.put('G', 7);
        consonantLettersMapHaveY.put('H', 8);
        consonantLettersMapHaveY.put('J', 1);
        consonantLettersMapHaveY.put('K', 2);
        consonantLettersMapHaveY.put('L', 3);
        consonantLettersMapHaveY.put('M', 4);
        consonantLettersMapHaveY.put('N', 5);
        consonantLettersMapHaveY.put('P', 7);
        consonantLettersMapHaveY.put('Q', 8);
        consonantLettersMapHaveY.put('R', 9);
        consonantLettersMapHaveY.put('S', 1);
        consonantLettersMapHaveY.put('T', 2);
        consonantLettersMapHaveY.put('V', 4);
        consonantLettersMapHaveY.put('W', 5);
        consonantLettersMapHaveY.put('X', 6);
        consonantLettersMapHaveY.put('Y', 7);
        consonantLettersMapHaveY.put('Z', 8);
    }

    public static final Map<Character, Integer> consonantLettersMap = new HashMap<>();

    static {
        consonantLettersMap.put('B', 2);
        consonantLettersMap.put('C', 3);
        consonantLettersMap.put('D', 4);
        consonantLettersMap.put('F', 6);
        consonantLettersMap.put('G', 7);
        consonantLettersMap.put('H', 8);
        consonantLettersMap.put('J', 1);
        consonantLettersMap.put('K', 2);
        consonantLettersMap.put('L', 3);
        consonantLettersMap.put('M', 4);
        consonantLettersMap.put('N', 5);
        consonantLettersMap.put('P', 7);
        consonantLettersMap.put('Q', 8);
        consonantLettersMap.put('R', 9);
        consonantLettersMap.put('S', 1);
        consonantLettersMap.put('T', 2);
        consonantLettersMap.put('V', 4);
        consonantLettersMap.put('W', 5);
        consonantLettersMap.put('X', 6);
        consonantLettersMap.put('Y', 7);
        consonantLettersMap.put('Z', 8);
    }

    public static final int MASTER_NUMBER_33 = 33;
    public static final int MASTER_NUMBER_11= 11;
    public static final int MASTER_NUMBER_22 = 22;
}
