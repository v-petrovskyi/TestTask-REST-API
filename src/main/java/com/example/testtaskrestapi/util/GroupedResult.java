package com.example.testtaskrestapi.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GroupedResult {
    private String location;
    private long count;

    public GroupedResult(String location, long count) {
        this.location = location;
        this.count = count;
    }
}
