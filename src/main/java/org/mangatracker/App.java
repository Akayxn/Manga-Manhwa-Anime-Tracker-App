package org.mangatracker;

import com.fasterxml.jackson.core.JsonProcessingException;

public class App {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println(User.fetchUsers());

    }
}