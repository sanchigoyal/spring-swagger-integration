package com.sanchi.java.swagger.services;

import com.sanchi.java.swagger.models.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class DataService {

  private static Map<Integer, Message> datastore = new HashMap<>();
  private static int counter = 0;

  /**
   * Returns a message matching identifier.
   */
  public Message getMessage(int messageId) {
    return datastore.get(messageId);
  }

  /**
   * Returns all messages.
   */
  public List<Message> getMessages() {
    return new ArrayList<>(datastore.values());
  }

  /**
   * Updates a message.
   */
  public Message updateMessage(Message message) {
    this.datastore.put(message.getId(), message);
    return message;
  }

  /**
   * Removes a message.
   */
  public void removeMessage(int messageId) {
    this.datastore.remove(messageId);
  }

  /**
   * Creates a new message.
   */
  public Message createMessage(Message message) {
    message.setId(++counter);
    return this.datastore.put(message.getId(), message);
  }


}
