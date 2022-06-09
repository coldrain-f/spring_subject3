package edu.coldrain.subject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 글로벌 에러처리 연습용 Controller
 */
@RestController
@RequestMapping("/error")
public class ErrorApi {

    @GetMapping
    public void test(@RequestParam String value) {
        if (value.equals("value")) {
            throw new IllegalArgumentException();
        }
    }
}
