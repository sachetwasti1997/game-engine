package com.game.engine.controller;

import com.game.engine.model.GameResult;
import com.game.engine.model.Move;
import com.game.engine.service.TicTacToeGame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game/tictactoe")
public class TicTacToeGameController {

    private final TicTacToeGame ticTacToeGame;

    public TicTacToeGameController(TicTacToeGame ticTacToeGame) {
        this.ticTacToeGame = ticTacToeGame;
    }

    @PostMapping("/start/row/{row}/col/{col}")
    public ResponseEntity<String> startGame(@PathVariable(name = "row")int row, @PathVariable(name = "col") int col) {
        return ResponseEntity.ok(ticTacToeGame.startGame(row, col));
    }

    @PostMapping("/move")
    public ResponseEntity<GameResult> makeMove(@RequestParam("uuid") String uuid, @RequestBody Move move) {
        return ResponseEntity.ok(ticTacToeGame.makeMove(uuid, move));
    }

}
