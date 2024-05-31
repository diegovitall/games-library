
package com.api.games.controller;

import com.api.games.data.Games;
import com.api.games.service.GamesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GamesController {
    @Autowired
    GamesService gameService;
    @GetMapping("/listar")
    public ResponseEntity<List> listarTodosGames() {
        List<Games> games = gameService.listarTodosGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<Games> buscarGamePorId(@PathVariable Integer id) {
        Games game = gameService.buscarGamePorId(id);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Games> criarGame(@RequestBody Games game) {
    var novoGame = gameService.novoGame(game);
        return new ResponseEntity<>(novoGame, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Games> atualizarGame(@PathVariable Integer id, @RequestBody Games game) {
        var gameAtualizado = gameService.atualizarGame(id, game);
        return new ResponseEntity<>(gameAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarGame(@PathVariable Integer id) {
        gameService.deletarGame(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
