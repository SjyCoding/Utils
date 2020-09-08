package com.sforits.Utils.String;

import org.junit.Test;

import java.util.UUID;

/**
 * @Author：sforits
 * @E-mail：sforits@gmail.com
 * @Date：2020/9/8-11:33 Created by IntelliJ IDEA.
 */
public class UIDTest {
    @Test
    public void uuidAdd10() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "    " + UUID.randomUUID());
        }
    }

    @Test
    public void test() {
        UUID uuid = UUID.randomUUID();
        String[] split = uuid.toString().split("-");
        for (String s : split) {
            System.out.println(s);
        }
    }

}