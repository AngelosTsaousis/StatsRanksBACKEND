package gr.alpha.stats.ranks.game;

import gr.alpha.stats.ranks.DTOObjects.TeamStandingDTO;
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
     * Retrieves all games for a group ID.
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

    /**
     * Retrieves team standings for a specific group.
     *
     * @param groupId the ID of the group to find team standings for
     * @return an iterable of team standings for the specified group
     */
    @GetMapping("/group/{groupId}/standings")
    public Iterable<TeamStandingDTO> getTeamStandingsByGroupId(@PathVariable Integer groupId) {
        return gameService.getTeamStandingsByGroupId(groupId);
    }

}
