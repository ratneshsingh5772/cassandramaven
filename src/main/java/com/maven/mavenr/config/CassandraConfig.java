package com.maven.mavenr.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

import java.net.InetSocketAddress;

@Configuration
@EnableReactiveCassandraRepositories(basePackages = "com.maven.mavenr.repository")
public class CassandraConfig extends AbstractReactiveCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "mykeyspace";
    }

    @Override
    protected String getLocalDataCenter() {
        return "datacenter1";
    }

    @Override
    protected String getContactPoints() {
        return "127.0.0.1"; // or "localhost"
    }

    @Override
    protected int getPort() {
        return 9042;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }


    // Explicit CqlSession Bean
    @Bean
    @Primary
    public CqlSession cqlSession() {
        return CqlSession.builder()
                .addContactPoint(new InetSocketAddress(getContactPoints(), getPort()))
                .withLocalDatacenter(getLocalDataCenter())
                .withKeyspace(getKeyspaceName())
                .build();
    }
}
