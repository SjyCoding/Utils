package com.sforits.Test.Json;

import com.sforits.entity.Book;
import com.sforits.Utils.Json.JsonChange;
import org.junit.Test;

import java.util.*;

/**
 * @Author：sforits
 * @E-mail：sforits@gmail.com
 * @Date：2020/9/8-10:52 Created by IntelliJ IDEA.
 */
public class MainTest {

    Map<String, Object> map = new HashMap<String, Object>();


    @Test
    public void t0() {
        List<Book> books = new ArrayList<Book>();

        books.add(new Book("101", "书名1", 100));
        books.add(new Book("102", "书名2", 100));
        books.add(new Book("103", "书名3", 100));

        System.out.println(books);

        map.put("books",books);

        List<Book> books1 = new JsonChange<Book>().mapToList(map,Book.class,"books");

        System.out.println(books);

    }
}