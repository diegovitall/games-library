
package com.api.games.service;

import com.api.games.data.Games;
import com.api.games.data.GamesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamesService {
    @Autowired
    GamesRepository gamesRepository;
    public Games novoGame(Games game){
        game.setId(null);
        gamesRepository.save(game);
        return game;
    }
    public List<Games> listarTodosGames() {
        return gamesRepository.findAll();
    }
    public Games buscarGamePorId(Integer id){
        return gamesRepository.findById(id).orElse(null);
    }
    public Games atualizarGame(Integer id, Games gameRequest){
        Games game = buscarGamePorId(id);
        game.setTitulo(gameRequest.getTitulo());
        game.setGenero(gameRequest.getGenero());
        game.setAnoLancamento(gameRequest.getAnoLancamento());
        gamesRepository.save(game);
        return game;
    }
    public void deletarGame(Integer id){
        Games game = buscarGamePorId(id);
        gamesRepository.deleteById(game.getId());
    }
}
