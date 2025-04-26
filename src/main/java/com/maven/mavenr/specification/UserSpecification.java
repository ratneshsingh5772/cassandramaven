package com.maven.mavenr.specification;

import com.maven.mavenr.dto.UserSearchCriteria;
import com.maven.mavenr.model.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class UserSpecification {

    public Flux<User> applyFilter(Flux<User> users, UserSearchCriteria searchCriteria) {
        Flux<User> filtered = users.filter(user -> {
            boolean matches = true;

            if (searchCriteria.getName() != null && !searchCriteria.getName().isBlank()) {
                matches = matches && user.getName() != null &&
                        user.getName().toLowerCase().contains(searchCriteria.getName().toLowerCase());
            }
            if (searchCriteria.getEmail() != null && !searchCriteria.getEmail().isBlank()) {
                matches = matches && user.getEmail() != null &&
                        user.getEmail().toLowerCase().contains(searchCriteria.getEmail().toLowerCase());
            }

            return matches;
        });

        // 🧠 Apply Pagination (skip + take)
        int pageNo = searchCriteria.getPageNo() != null ? searchCriteria.getPageNo() : 0;
        int pageSize = searchCriteria.getPageSize() != null ? searchCriteria.getPageSize() : 10;
        int skip = pageNo * pageSize;

        return filtered.skip(skip).take(pageSize);
    }
}
