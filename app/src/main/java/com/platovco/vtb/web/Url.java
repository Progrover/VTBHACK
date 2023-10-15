package com.platovco.vtb.web;

enum Url {
    SERVER_URL("http://37.221.203.14:8080/"),
    TEST_URL("http://192.168.253.74:8080/");
    private final String string;

    Url(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}