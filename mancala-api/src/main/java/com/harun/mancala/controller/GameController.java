package com.harun.mancala.controller;

import com.harun.mancala.domain.Game;
import com.harun.mancala.domain.service.IGameService;
import com.harun.mancala.dto.GameStartRequestDto;
import com.harun.mancala.dto.PlayRequestDto;
import com.harun.mancala.dto.PlayResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final IGameService gameService;

    @Autowired
    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }


    @PostMapping("/start")
    public ResponseEntity<PlayResponseDto> start(
            @RequestBody GameStartRequestDto gameStartRequest
    ) {
        Game game = gameService.startNewGame(
                gameStartRequest.getPlayerNames().playerOne(),
                gameStartRequest.getPlayerNames().playerTwo()
        );
        return ResponseEntity.ok(new PlayResponseDto(game));
    }

    @PostMapping("/play")
    public ResponseEntity<PlayResponseDto> play(
            @RequestBody PlayRequestDto playRequest
    ) {
        Game game = gameService.makeMove(
                playRequest.gameId(),
                playRequest.pitIndex()
        );

        return ResponseEntity.ok(new PlayResponseDto(game));
    }
}
