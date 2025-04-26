package com.maven.mavenr.controller;

import com.maven.mavenr.common.CommonResponse;
import com.maven.mavenr.common.PagedResponse;
import com.maven.mavenr.dto.*;
import com.maven.mavenr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Mono<ResponseEntity<CommonResponse<UserDTO>>> createUser(@RequestBody UserCreateDTO createDTO) {
        return userService.createUser(createDTO).flatMap(CommonResponse::okResponse);
    }



    @PutMapping("/{id}")
    public Mono<ResponseEntity<CommonResponse<UserDTO>>> updateUser(@PathVariable String id, @RequestBody UserUpdateDTO updateDTO) {
        return userService.updateUser(id, updateDTO)
                .map(userDTO -> ResponseEntity.ok(CommonResponse.success(userDTO)));
    }

    @PostMapping("/search")
    public Mono<ResponseEntity<CommonResponse<PagedResponse<UserDTO>>>> searchUsers(@RequestBody UserSearchCriteria searchCriteria) {
        Flux<UserDTO> filteredUsers = userService.searchUsers(searchCriteria);
        Mono<Long> totalCountMono = userService.countUsers(searchCriteria);

        return Mono.zip(filteredUsers.collectList(), totalCountMono)
                .map(tuple -> {
                    List<UserDTO> users = tuple.getT1();
                    Long total = tuple.getT2();
                    int totalPages = (int) Math.ceil((double) total / searchCriteria.getPageSize());

                    PagedResponse<UserDTO> pagedResponse = new PagedResponse<>(
                            users,
                            searchCriteria.getPageNo(),
                            searchCriteria.getPageSize(),
                            total,
                            totalPages
                    );
                    return ResponseEntity.ok(CommonResponse.success(pagedResponse));
                });
    }



    @GetMapping
    public Mono<ResponseEntity<CommonResponse<List<UserDTO>>>> getAllUsers() {
        return  userService.getAllUsers()
                .collectList()
                .map(userList -> ResponseEntity.ok(CommonResponse.success(userList)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CommonResponse<UserDTO>>> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(userDTO -> ResponseEntity.ok(CommonResponse.success(userDTO)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<CommonResponse<Void>>> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id)
                .then(Mono.just(ResponseEntity.ok(CommonResponse.success(null))));
    }
}
