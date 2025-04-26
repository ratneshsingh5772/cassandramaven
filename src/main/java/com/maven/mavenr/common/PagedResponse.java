package com.maven.mavenr.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T> {

    private List<T> content;    // your list of users
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
}
