package com.altimetrik.chatBot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.chatBot.entities.Menu;
import com.altimetrik.chatBot.service.ChatBotServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-27T09:47:09.303Z")

@RestController
@Api(value = "menus", description = "the menus API")
public class MenusApiController {

    private static final Logger log = LoggerFactory.getLogger(MenusApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    ChatBotServiceInterface chatBotServiceInterface;

    @org.springframework.beans.factory.annotation.Autowired
    public MenusApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @ApiOperation(value = "get all menue", nickname = "getMenu", notes = "get all the relevent menue", response = Menu.class, responseContainer = "List", tags={ "chatbotMenu", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "generate successfuly bill", response = Menu.class, responseContainer = "List"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/menus",
            produces = { "application/json" }, 
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<List<Menu>> getMenu(
    		@ApiParam(value = "menu object"  )  @Valid @RequestBody Menu body) {
    	return new ResponseEntity<List<Menu>>(chatBotServiceInterface.getAllMenu(body), HttpStatus.OK);
    }

}
