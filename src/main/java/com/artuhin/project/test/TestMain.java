package com.artuhin.project.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestMain {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String s = localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println(s);

    }
}
