package com.game.engine.repo;

import com.game.engine.model.Board;
import com.game.engine.model.TicTacToeBoard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardRepository extends MongoRepository<TicTacToeBoard, String> {
}
