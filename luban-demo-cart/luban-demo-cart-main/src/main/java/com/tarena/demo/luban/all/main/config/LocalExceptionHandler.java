package com.tarena.demo.luban.all.main.config;


import commons.exception.handler.GlobalControllerExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.tarena.demo.luban.all.main.controller")
public class LocalExceptionHandler extends GlobalControllerExceptionHandler {
}
