package edu.coldrain.subject.handler;

import edu.coldrain.subject.controller.RestaurantApiController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice(assignableTypes = {RestaurantApiController.class})
public class RestaurantApiExceptionHandler {


}