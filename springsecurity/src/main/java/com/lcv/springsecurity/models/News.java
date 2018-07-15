package com.lcv.springsecurity.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class News {
    private int id;
    private String title;
    private String content;
    private String author;
}
