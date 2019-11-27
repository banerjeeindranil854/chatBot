/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.10).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.altimetrik.chatBot.controllerInf;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.altimetrik.chatBot.entities.Menu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-27T09:47:09.303Z")

@Api(value = "maxtraversed", description = "the maxtraversed API")
public interface MaxtraversedApi {

    @ApiOperation(value = "get max traversed details", nickname = "getTraversed", notes = "get max traversed path", response = Menu.class, responseContainer = "List", tags={ "chatbotTravarsal", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Menu.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Invoice not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/maxtraversed",
        produces = { "application/xml", "application/json" }, 
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.GET)
    ResponseEntity<List<Menu>> getTraversed();

}
