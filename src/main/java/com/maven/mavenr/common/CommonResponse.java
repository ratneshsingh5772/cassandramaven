package com.maven.mavenr.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {

    private int statusCode;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(200, "Success", data);
    }

    public static <T> CommonResponse<T> error(String message, int statusCode) {
        return new CommonResponse<>(statusCode, message, null);
    }
    public static  <T> Mono<ResponseEntity<CommonResponse<T>>> okResponse(T data) {
        return Mono.just(ResponseEntity.ok(CommonResponse.success(data)));
    }

}
