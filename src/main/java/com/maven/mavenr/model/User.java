package com.maven.mavenr.model;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table("users")
public class User {

    @PrimaryKey
    private String id;

    private String name;
    private String email;

    @Transient
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
}
