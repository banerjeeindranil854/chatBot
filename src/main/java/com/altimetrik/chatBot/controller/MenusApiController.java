package com.altimetrik.chatBot.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.altimetrik.chatBot.controllerInf.MenusApi;
import com.altimetrik.chatBot.model.MenuVo;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-27T09:47:09.303Z")

@Controller
public class MenusApiController implements MenusApi {

    private static final Logger log = LoggerFactory.getLogger(MenusApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public MenusApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<MenuVo>> getMenu(@ApiParam(value = "menu object"  )  @Valid @RequestBody MenuVo body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/xml")) {
            try {
                return new ResponseEntity<List<MenuVo>>(objectMapper.readValue("<menu>  <id>123</id>  <name>aeiou</name>  <count>aeiou</count>  <type>aeiou</type>  <description>aeiou</description></menu>", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/xml", e);
                return new ResponseEntity<List<MenuVo>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<MenuVo>>(objectMapper.readValue("[ {  \"name\" : \"name\",  \"count\" : \"count\",  \"description\" : \"description\",  \"id\" : 0,  \"type\" : \"type\"}, {  \"name\" : \"name\",  \"count\" : \"count\",  \"description\" : \"description\",  \"id\" : 0,  \"type\" : \"type\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<MenuVo>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<MenuVo>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
