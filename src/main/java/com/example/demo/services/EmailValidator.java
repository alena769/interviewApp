package com.example.demo.services;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {

    private final String regex = "^(.+)@(.+)$";

    @Override
    public Predicate and(Predicate other) {
        return Predicate.super.and(other);
    }

    @Override
    public boolean test(String email) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    @Override
    public Predicate negate() {
        return Predicate.super.negate();
    }

    @Override
    public Predicate or(Predicate other) {
        return Predicate.super.or(other);
    }
}
