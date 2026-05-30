package com.game.engine.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public abstract class Board {
    private String[][] board;
    private int rows;
    private int cols;
    private Set<String> players;

    public Board(int rows, int cols, Set<String> players) {
        this.rows = rows;
        this.cols = cols;
        board = new String[rows][cols];
        this.players = players;
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
