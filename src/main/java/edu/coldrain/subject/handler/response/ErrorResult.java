package edu.coldrain.subject.handler.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResult {

    private LocalDateTime timestamp;
    private int status; // 500
    private String error; // Internal Server Error
    private String message;
    private String path; // /user

    @Builder
    public ErrorResult(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
