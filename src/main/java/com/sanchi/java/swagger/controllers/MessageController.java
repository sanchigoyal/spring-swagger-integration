package com.sanchi.java.swagger.controllers;

import com.sanchi.java.swagger.models.Message;
import com.sanchi.java.swagger.services.DataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

  @Autowired
  protected DataService dataService;

  /**
   * Returns a message.
   */
  @ApiOperation(value = "Get message by Id", notes = "Returns a message matching identifier")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Record exists"),
      @ApiResponse(code = 404, message = "No record found"),
      @ApiResponse(code = 401, message = "Unauthorized")})
  @RequestMapping(value = "/{messageId}", method = RequestMethod.GET)
  public ResponseEntity<Message> getMessage(@PathVariable int messageId) {
    Message message = dataService.getMessage(messageId);
    if (message == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(message);
  }

  /**
   * Returns all messages.
   */
  @ApiOperation(value = "Get all messages", notes = "Returns all messages")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 401, message = "Unauthorized")})
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ResponseEntity<List<Message>> getMessages(
      @RequestParam(value = "from", required = false) LocalDate fromDate,
      @RequestParam(value = "to", required = false) LocalDate toDate) {
    return ResponseEntity.ok(dataService.getMessages());
  }

  /**
   * Creates a new message.
   */
  @ApiOperation(value = "Create a message", notes = "Creates new message", code = 201)
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Successful operation", response = Message.class),
      @ApiResponse(code = 401, message = "Unauthorized")})
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public ResponseEntity<Message> createMessage(
      @ApiParam(required = true)
      @RequestBody Message message) {
    return ResponseEntity.status(HttpStatus.CREATED).body(dataService.createMessage(message));
  }

  /**
   * Deletes a message.
   */
  @ApiOperation(value = "Delete message", notes = "Deletes a message")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 401, message = "Unauthorized")})
  @RequestMapping(value = "/{messageId}", method = RequestMethod.DELETE)
  public void deleteMessage(@PathVariable int messageId) {
    dataService.removeMessage(messageId);
  }

  /**
   * Updates a message.
   */
  @ApiOperation(value = "Update message", notes = "Updates an existing message")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "No record found"),
      @ApiResponse(code = 401, message = "Unauthorized")})
  @RequestMapping(value = "/{messageId}", method = RequestMethod.PUT)
  public ResponseEntity<Message> updateMessage(@PathVariable int messageId,
      @ApiParam(required = true)
      @RequestBody Message message) {
    Message original = dataService.getMessage(messageId);
    if (original == null) {
      return ResponseEntity.notFound().build();
    }
    message.setId(messageId);
    return ResponseEntity.ok(dataService.updateMessage(message));
  }

}
