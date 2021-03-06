package chat.app.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

  private final SimpMessagingTemplate template;

  @Autowired
  WebSocketController(SimpMessagingTemplate template) {
    this.template = template;
  }

  @MessageMapping("/send/message")
  public void onReceivedMessage(String message, Principal principal) {
    this.template.convertAndSend("/chat",
        new SimpleDateFormat("HH:mm:ss").format(new Date()) + "- " +
            /*principal.getName() + ": " +*/
            message);
  }
}
