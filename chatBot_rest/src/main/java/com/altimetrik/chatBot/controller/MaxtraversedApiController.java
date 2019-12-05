package com.altimetrik.chatBot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.chatBot.entities.Menu;
import com.altimetrik.chatBot.service.ChatBotServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "maxtraversed", description = "the maxtraversed API")
@CrossOrigin
public class MaxtraversedApiController{

    private static final Logger log = LoggerFactory.getLogger(MaxtraversedApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Autowired
    ChatBotServiceInterface chatBotServiceInterface;

    @org.springframework.beans.factory.annotation.Autowired
    public MaxtraversedApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @ApiOperation(value = "get max traversed details", nickname = "getTraversed", notes = "get max traversed path", response = Menu.class, responseContainer = "List", tags={ "chatbotTravarsal", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Menu.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Invoice not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/maxtraversed",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    public ResponseEntity<List<Menu>> getTraversed(@RequestHeader(name = "Content-Type", required = true) String contentType,
    		@RequestHeader(name = "Accept", required = true) String accept) {
      
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<List<Menu>>(chatBotServiceInterface.getMaxTraversal(), HttpStatus.OK);
        }

        return new ResponseEntity<List<Menu>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
