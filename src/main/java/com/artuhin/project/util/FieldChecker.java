package com.artuhin.project.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldChecker {
    private static FieldChecker instance;

    public static synchronized FieldChecker getInstance() {
        if (instance == null){
            instance = new FieldChecker();
        }
        return instance;
    }

    private FieldChecker() {
    }

    public static boolean checkField(String value){
        Pattern pattern = Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9\\s*]+$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean checkEmail(String email) {
        Pattern p = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean checkPassword(String password) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher m = p.matcher(password);
        return m.matches();
    }
}