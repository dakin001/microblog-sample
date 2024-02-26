package com.example.moikiitos.shared;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PageResult<T> {
    private List<T> items = new ArrayList<>();
    private Integer total;

    public PageResult() {
    }

    public PageResult(List<T> items) {
        this.items = items;
        total = items.size();
    }
}