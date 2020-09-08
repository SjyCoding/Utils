package com.sforits.Test.DateTime;

import com.sforits.Utils.DateTime.StringTime;
import org.junit.Test;

/**
 * @Author：sforits
 * @E-mail：sforits@gmail.com
 * @Date：2020/9/8-11:44 Created by IntelliJ IDEA.
 */
public class StringTimeTest {
    @Test
    public void t1() {
        String stringTime = StringTime.getStringTime();
        System.out.println(stringTime);
    }
}