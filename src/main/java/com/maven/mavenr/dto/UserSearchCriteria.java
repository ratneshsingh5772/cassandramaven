package com.maven.mavenr.dto;

import lombok.Data;

@Data
public class UserSearchCriteria {
    private String name;
    private String email;
    private Integer pageNo = 0;      // default 0
    private Integer pageSize = 10;   // default 10
}
