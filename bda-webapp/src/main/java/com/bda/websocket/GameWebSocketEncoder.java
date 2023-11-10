package com.bda.websocket;

import java.io.IOException;
import java.io.Writer;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

import org.json.JSONObject;

public class GameWebSocketEncoder implements Encoder.TextStream<Game> {

  @Override
  public void init(EndpointConfig config) {
  }

  @Override
  public void destroy() {
  }

  @Override
  public void encode(Game game, Writer writer) throws EncodeException, IOException {
    JSONObject  gameJSON = new JSONObject(game);
    writer.write(gameJSON.toString());
    writer.flush();

  }
}