package com.sforits.entity;

import lombok.*;

/**
 * @Author：sforits
 * @E-mail：sforits@gmail.com
 * @Date：2020/9/8-10:57 Created by IntelliJ IDEA.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String bid;
    private String bookName;
    private Integer price;
}