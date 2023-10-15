package com.platovco.vtb.web;

import org.json.JSONException;

import java.io.IOException;

@FunctionalInterface
public interface Callback<T> {
    void accept(int code, T arg) throws IOException, JSONException;
}
