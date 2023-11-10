package com.bda.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.Session;
import jakarta.websocket.EncodeException;

@ServerEndpoint(
        value = "/ws/gameWebSocket",
        encoders = {GameWebSocketEncoder.class},
        decoders = {GameWebSocketDecoder.class})
public class GameWebSocket {

  private static Game game = new Game();
  private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
  private static Slot lastPlayer = Slot.CIRCLE;

  @OnOpen
  public void onOpen(Session session) throws IOException, EncodeException {
    sessions.add(session);
    session.getUserProperties().put("player", lastPlayer);
    final String firstMessage = "{\"action\":\"start\", \"player\":\"" + lastPlayer + "\"}";

    session.getAsyncRemote().sendText(firstMessage);
    session.getAsyncRemote().sendObject(game);
    lastPlayer = lastPlayer == Slot.CIRCLE ? Slot.EXXES : Slot.CIRCLE;
  }

  @OnMessage
  public void onMessage(Move move) {
    game.play(move);
    for (Session session : sessions) {
      try {
        session.getAsyncRemote().sendObject(game);
      } catch (Exception ex) {
        Logger.getLogger(GameWebSocket.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  @OnClose
  public void onClose(Session session) {
    sessions.remove(session);
  }
}
