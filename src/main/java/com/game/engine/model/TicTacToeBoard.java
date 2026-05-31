package com.game.engine.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.redis.core.RedisHash;

import java.util.Set;

@Getter
@Setter
@Document(collection = "tictactoe")
public class TicTacToeBoard extends Board {

    @Id
    private String gameUUID;
    private Cell[][] board;
    private int rows;
    private int cols;
    private GameStatus gameStatus;
    private String lastPlayerSymbol;

    public TicTacToeBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new Cell[rows][cols];
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                board[i][j] = new Cell("("+i+","+j+")");
            }
        }
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public String getBoard(int i, int j) {
        if (i >= rows || j >= cols) {
            return "INVALID";
        }
        return board[i][j].getPiece().getPieceName();
    }

    public void setBoard(int i, int j, String mark) {
        if (i >= rows || j >= cols) {
            return;
        }
        board[i][j].getPiece().setPieceName(mark);
    }

}
