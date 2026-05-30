package com.game.engine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GameResult {

    private boolean isGameOver;
    private String winner;
    private String[] winningMove;

}
