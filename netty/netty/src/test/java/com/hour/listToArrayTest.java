package com.hour;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class listToArrayTest {
    
    @Test
    public void listToArray() {
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{1,2,3});
        list.add(new int[]{1,2,3,4});
        list.add(new int[]{1,2,3,4,5});
        list.add(new int[]{1,2,3,4,5,6});
        int[][] array = new int[list.size()][];
        list.toArray(array);
    }
}
