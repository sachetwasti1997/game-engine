package com.game.engine.service;

import com.game.engine.model.Board;
import com.game.engine.model.GameResult;
import com.game.engine.model.Move;
import com.game.engine.model.TicTacToeBoard;
import com.game.engine.repo.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TicTacToeGame {

    private final BoardRepository boardRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(TicTacToeGame.class);

    public TicTacToeGame(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void startGame(int rows, int cols) {
        String uuid = UUID.randomUUID().toString();
        boardRepository.save(new TicTacToeBoard(rows, cols, uuid));
    }

    public GameResult makeMove(String uuid, Move move) {
        Optional<TicTacToeBoard> board = boardRepository.findById(uuid);
        if (board.isEmpty()) {
            LOGGER.warn("No tictactoe game found for the given UUID {}", uuid);
            return null;
        }
        TicTacToeBoard ticTacToeBoard = board.get();
        ticTacToeBoard.setCell(move.getX(), move.getY(), move.getPlayers());
        return getGameStatus(board.get());
    }

    public GameResult getGameStatus(TicTacToeBoard board) {
        int x = board.getRows();
        int y = board.getCols();
        int i = 0, j = 0;

        GameResult result;

        result = rowColWon(x, y, board);
        if (result.isGameOver()) {
            return result;
        }
        result = determineDiagWon(x, y, board);
        if (result.isGameOver()) {
            return result;
        }

        return calculateIfGameOver(board);
    }

    private GameResult determineDiagWon(int x, int y, TicTacToeBoard board) {
        int i;
        int j;
        boolean diagFilled = true;
        boolean revDigFilled = true;

        // determine diagonal win
        i = 0;
        j = 0;
        String firstCharacter = board.getCell(i, j);
        String revFirstCharacter = board.getCell(x -1, 0);
        i++;
        j++;
        while (i < x && j < y) {
            if (diagFilled &&!board.getCell(i, j).equals(firstCharacter)) {
                diagFilled = false;
            }
            if (revDigFilled && !board.getCell(x -1-i, j).equals(revFirstCharacter)) {
                revDigFilled = false;
            }
            if (!revDigFilled && !diagFilled) {
                break;
            }
            i++;
            j++;
        }

        if (revDigFilled) {
            return new GameResult(true, revFirstCharacter, new String[]{"Reversed Diagonal Covered"});
        }
        if (diagFilled) {
            return new GameResult(true, firstCharacter, new String[]{"Diagonal Covered"});
        }

        return getNoWinNoGameOver();
    }

    private GameResult rowColWon(int x, int y, TicTacToeBoard board) {
        boolean rowsFilled = false;
        boolean colFilled = false;
        int i=0, j=0;
        // determine if row/col won
        for (i=0; i<x; i++) {
            colFilled = true;
            String firstCharacter = board.getCell(i, 0);
            for (j=1; j < y; j++) {
                if (!board.getCell(i, j).equals(firstCharacter)) {
                    colFilled = false;
                    break;
                }
            }
            if (colFilled) {
                return new GameResult(true, firstCharacter, new String[]{"Row Covered"});
            }

            rowsFilled = true;
            String firstCharacterCol = board.getCell(0, i);
            for (j = 1; j<x; i++) {
                if (!board.getCell(j, i).equals(firstCharacterCol)) {
                    rowsFilled = false;
                    break;
                }
            }
            if (rowsFilled) {
                return new GameResult(true, firstCharacterCol, new String[]{"Column Covered"});
            }
        }

        return getNoWinNoGameOver();
    }

    private GameResult calculateIfGameOver(TicTacToeBoard board) {
        int count = 0;
        for (int i=0; i< board.getRows(); i++) {
            for (int j=0; j< board.getCols(); j++) {
                if (board.getCell(i, j) != null) {
                    count ++;
                }
            }
        }
        if(count == board.getRows() * board.getCols()) {
            return new GameResult(true, "NA", null);
        }
        return getNoWinNoGameOver();
    }

    private GameResult getNoWinNoGameOver() {
        return new GameResult(false, "-", null);
    }

}
