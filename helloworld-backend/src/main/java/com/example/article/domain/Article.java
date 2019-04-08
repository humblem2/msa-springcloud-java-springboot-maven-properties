package com.example.article.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "city")
@Entity
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Article(@NonNull String name) {
        this.name = name;
    }
}