
package com.api.games.controller;

import com.api.games.data.Games;
import com.api.games.service.GamesService;
import com.api.games.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MVCGameController {
    @Autowired
    GamesService gameService;
    @GetMapping("/") 
    public String viewHomePage(Model model) {
        model.addAttribute("listarGames", gameService.listarTodosGames()); 
        return "Biblioteca"; 
    }

    @GetMapping("/deletarGame/{id}") 
    public String deletarGame(@PathVariable(value = "id") Integer id) { 
        gameService.deletarGame(id); 
        return "redirect:/"; 
    }

    @GetMapping("/criarGame") 
    public String criarGame(Model model) { 
        Games game = new Games(); 
        model.addAttribute("game", game); 
        return "novo-game"; 
    }

    @PostMapping("/salvarGame") 
    public String salvarGame(@ModelAttribute("game") Games game, BindingResult result, @RequestParam("file") MultipartFile imagem) { 
        if (result.hasErrors()) { 
            return "novo-game"; 
        }
        if (game.getId()==null) { 
            UploadUtil.uploadImg(imagem);
            game.setImagemDir(imagem.getOriginalFilename());
            gameService.novoGame(game); 
        } else { 
            gameService.atualizarGame(game.getId(), game); 
        }
        return "novo-game"; 
    }

    @GetMapping("/atualizarGame/{id}") 
    public String atualizarGame(@PathVariable(value = "id") Integer id, Model model) { 
        Games game = gameService.buscarGamePorId(id); 
        model.addAttribute("game", game); 
        return "atualizar"; 
    }
}
