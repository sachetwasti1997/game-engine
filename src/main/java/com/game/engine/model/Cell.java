package com.game.engine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cell {
    private String cellName;
    private Piece piece;
    public Cell(String cellName) {
        this.cellName = cellName;
        this.piece = new Piece();
    }
}
