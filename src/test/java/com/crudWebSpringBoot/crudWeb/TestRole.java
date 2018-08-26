package com.crudWebSpringBoot.crudWeb;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestRole {
    @Test
    public void test(){
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        List<Integer> integers2 = new ArrayList<>();
        integers2.add(1);
        integers2.add(2);
        List<Integer> result = new ArrayList<>();
        for(Integer i : integers) {
            if(!integers2.contains(i)) {
                result.add(i);
            }
        }
      result.forEach(System.out::println);
    }
    @Test
    public void credTest(){

    }


}
