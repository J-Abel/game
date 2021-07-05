package hu.rsoft.homework.controller;

import hu.rsoft.homework.entity.Game;
import hu.rsoft.homework.model.GuessDto;
import hu.rsoft.homework.model.RestResponse;
import hu.rsoft.homework.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;

    /**
     * Endpoint to start game. It generates an id for the gamer.
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

    /**
     * Endpoint to guess. It needs id and the guessed number and returns that
     * it is less than, greater than, or equal to the original number
     * or the game is over if a user had a good tip before
     *
     * @param payload GuessDto object, it contains id and the guessed number
     * @return RestResponse object, it contains the result
     */
    @PostMapping("/guess")
    public RestResponse guess(@RequestBody GuessDto payload) {

        Game game = gameRepository.findById(payload.getId()).orElseThrow(NoSuchElementException::new);

        // Game is over, if playingTime is not null
        if (Objects.nonNull(game.getPlayingTime())) {
            return RestResponse.over();
        }

        if (game.getNumber() == payload.getGuess()) {
            long timeElapsed = Duration.between(game.getStartTime(), Instant.now()).toMillis();
            long timeElapsedInSeconds = Math.round(timeElapsed / 1000);
            game.setPlayingTime(timeElapsedInSeconds);
            gameRepository.saveAndFlush(game);

            return RestResponse.equal(timeElapsedInSeconds);

        } else if (game.getNumber() < payload.getGuess()) {
            return RestResponse.lower();

        } else {
            return RestResponse.greater();
        }
    }
}
