package com.game.engine.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash
public class TicTacToeBoard extends Board {

    @Id
    private String gameUUID;
    public TicTacToeBoard(int rows, int cols, String gameUUID) {
        super(rows, cols, gameUUID);
    }

}
