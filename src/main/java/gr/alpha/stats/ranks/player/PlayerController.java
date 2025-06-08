package gr.alpha.stats.ranks.player;

import gr.alpha.stats.ranks.DTOObjects.TopPlayerDTO;
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

    /**
     * Fetches top 3 scores for a specific Group ID.
     * @param groupId
     * @return
     */
    @RequestMapping("/top-scorers/{groupId}")
    public Iterable<TopPlayerDTO> getTop3Scores(@PathVariable Integer groupId) {
        return playerService.getTop3Scores(groupId);
    }

    /**
     * Fetches top 3 three-pointers for a specific Group ID.
     * @param groupId
     * @return
     */
    @RequestMapping("/top-three-pointers/{groupId}")
    public Iterable<TopPlayerDTO> getTop3ThreePointers(@PathVariable Integer groupId) {
        return playerService.getTop3ThreePointers(groupId);
    }

    /**
     * Fetches top 3 scorers per league.
     * @param leagueId
     * @return
     */
    @RequestMapping("/top-scorers/league/{leagueId}")
    public Iterable<TopPlayerDTO> getTop3ScorersByLeague(@PathVariable Integer leagueId) {
        return playerService.getTop3ScorersByLeague(leagueId);
    }

    /**
     * Fetches top 3 three-pointers per league.
     * @param leagueId
     * @return
     */
    @RequestMapping("/top-three-pointers/league/{leagueId}")
    public Iterable<TopPlayerDTO> getTop3ThreePointersByLeague(@PathVariable Integer leagueId) {
        return playerService.getTop3ThreePointersByLeague(leagueId);
    }

}
