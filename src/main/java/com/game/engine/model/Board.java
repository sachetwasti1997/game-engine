package com.game.engine.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Board {
    private String [][] board;
    private int rows;
    private int cols;
    private String uuid;

    public Board(int rows, int cols, String uuid) {
        this.rows = rows;
        this.cols = cols;
        board = new String[rows][cols];
        this.uuid = uuid;
    }

    public String getCell(int i, int j) {
        if (i >= rows || j >= cols) {
            return "INVALID";
        }
        return board[i][j];
    }

    public void setCell(int i, int j, String mark) {
        if (i >= rows || j >= cols) {
            return;
        }
        board[i][j] = mark;
    }
}
