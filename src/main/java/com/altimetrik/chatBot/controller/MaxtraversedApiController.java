package com.altimetrik.chatBot.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.altimetrik.chatBot.controllerInf.MaxtraversedApi;
import com.altimetrik.chatBot.model.Menu;
import com.fasterxml.jackson.databind.ObjectMapper;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-27T09:47:09.303Z")

@Controller
public class MaxtraversedApiController implements MaxtraversedApi {

    private static final Logger log = LoggerFactory.getLogger(MaxtraversedApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public MaxtraversedApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Menu>> getTraversed() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/xml")) {
            try {
                return new ResponseEntity<List<Menu>>(objectMapper.readValue("<menu>  <id>123</id>  <name>aeiou</name>  <count>aeiou</count>  <type>aeiou</type>  <description>aeiou</description></menu>", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/xml", e);
                return new ResponseEntity<List<Menu>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Menu>>(objectMapper.readValue("[ {  \"name\" : \"name\",  \"count\" : \"count\",  \"description\" : \"description\",  \"id\" : 0,  \"type\" : \"type\"}, {  \"name\" : \"name\",  \"count\" : \"count\",  \"description\" : \"description\",  \"id\" : 0,  \"type\" : \"type\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Menu>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Menu>>(HttpStatus.NOT_IMPLEMENTED);
    }

}