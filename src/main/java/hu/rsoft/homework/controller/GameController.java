package hu.rsoft.homework.controller;

import hu.rsoft.homework.entity.Game;
import hu.rsoft.homework.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Random;

@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;

    /**
     * Endpoint to start game. It generates an id for the gamer.
     *
     * @return Id for gamer
     */
    @GetMapping("/start")
    public long startGame() {

        Game game = new Game();
        game.setNumber(new Random().nextInt(101));
        game.setStartTime(Instant.now());

        Game saved = gameRepository.saveAndFlush(game);

        return saved.getId();
    }

}
