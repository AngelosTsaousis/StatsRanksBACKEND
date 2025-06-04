package gr.alpha.stats.ranks.game;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/games")
class GameController {
    private final GameService gameService;

    /**
     * Constructor for GameController.
     *
     * @param gameService the service to access game data
     */
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Retrieves all games.
     *
     * @return a list of all games
     */
    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    /**
     * Retrieves a specific games by their group ID.
     *
     * @param id the ID of the game to retrieve
     * @return the game with the specified ID
     */
    @GetMapping("/group/{id}")
    public Iterable<Game> getGameById(@PathVariable Integer id) {
        return gameService.getGamesByGroupId(id);
    }

    /**
     * Retrieves all game dates for a specific group.
     *
     * @param groupId the ID of the group to find game dates for
     * @return a list of distinct game dates for the specified group
     */
    @GetMapping("/group/{groupId}/dates")
    public List<LocalDate> getGameDatesByGroupId(@PathVariable Integer groupId) {
        return gameService.getGameDatesByGroupId(groupId);
    }

}
