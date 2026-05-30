package com.game.engine.repo;

import com.game.engine.model.Board;
import com.game.engine.model.TicTacToeBoard;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<TicTacToeBoard, String> {
}
