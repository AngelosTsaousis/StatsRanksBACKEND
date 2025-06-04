package gr.alpha.stats.ranks.player;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Fetches all players.
     * @return
     */
    @RequestMapping("/all")
    public Iterable<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    /**
     * Fetches players by team ID.
     * @param teamId
     * @return
     */
    @RequestMapping("/team/{teamId}")
    public Iterable<Player> getTeamPlayers(@PathVariable Integer teamId) {
        return playerService.getTeamPlayers(teamId);
    }

}
