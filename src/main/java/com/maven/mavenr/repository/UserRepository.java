package com.maven.mavenr.repository;

import com.maven.mavenr.model.User;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCassandraRepository<User, String> {

    // Custom query method example
    Flux<User> findByName(String name);
}
