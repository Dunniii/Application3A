package com.example.application3a;

import java.util.List;
public class RestAtomeResponse {

    private Integer count;
    private String next;
    private List<Atome> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Atome> getResults() {
        return results;
    }
}
