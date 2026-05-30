package com.game.engine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
    public TicTacToeBoard(int rows, int cols, Set<String> players) {
        super(rows, cols, players);
    }

}
