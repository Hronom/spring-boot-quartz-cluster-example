package com.github.hronom.spring.boot.quartz.cluster.example.supervisor.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  private final Log logger = LogFactory.getLog(getClass());

  @GetMapping("tmp/{any}")
  public ResponseEntity<String> addJobs(
      @RequestParam(defaultValue = "10", required = false) int jobs,
      @PathVariable String any) {
    logger.info("path variable: " + any);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(any);
  }
}
