package com.sforits.Utils.io;

import javax.swing.*;
import java.io.*;

public class IOHelper {
    public static void close(Object... lists) throws IOException {

        for (Object list : lists) {
            if (list instanceof InputStream) {
                InputStream in = (InputStream) list;
                in.close();
            }
            if (list instanceof OutputStream) {
                OutputStream list1 = (OutputStream) list;
                list1.close();
            }
        }

    }
}
