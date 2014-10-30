package com.kumliens.school;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Test;

import com.google.common.collect.Lists;

public class DummyTest {

    private final int var = 1;

    @Test
    public void testMe() {
        final Consumer<Integer> consumer = x -> System.out.println(x);
        final List<Integer> ints = Lists.newArrayList(1, 2, 3);
        ints.forEach(consumer);
    }

    @Test
    public void test1() {
        final List<Integer> ints = Lists.newArrayList(1, 2, 3);
        final DummyTest instance = this;
        ints.forEach(x -> {
            System.out.println(x + this.var);
            if (this == instance) {
                System.out.println("hej");
            }
        });
    }

    public static void doIt(final Integer integer) {
        System.out.println(integer);
    }

    @Test
    public void test2() {
        final List<Integer> ints = Lists.newArrayList(1, 2, 3);
        final Consumer<Integer> consumer = x -> doIt(x);
        final Consumer<Integer> consumer2 = DummyTest::doIt;
        ints.forEach(consumer2);
    }

    @Test
    public void test3() {
        final Function<String, Integer> mapper = Integer::new;
        System.out.println(mapper.apply("11"));
    }

}
