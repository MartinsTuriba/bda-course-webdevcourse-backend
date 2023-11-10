package com.bda.websocket;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

public class GameWebSocketDecoder implements Decoder.TextStream<Move> {

  @Override
  public void init(EndpointConfig config) {
  }

  @Override
  public void destroy() {
  }

  @Override
  public Move decode(Reader reader) throws DecodeException, IOException {
    try (JsonReader jsonReader = Json.createReader(reader)) {
      JsonObject jsonObject = jsonReader.readObject();
      //System.out.println(jsonObject);
      Move move = new Move();
      move.setPlayer(Slot.valueOf(jsonObject.getString("player")));
      move.setX(jsonObject.getInt("x"));
      move.setY(jsonObject.getInt("y"));
      return move;
    }
  }
}

