package gr.alpha.stats.ranks.player;

import gr.alpha.stats.ranks.DTOObjects.PlayerGameLogDTO;
import gr.alpha.stats.ranks.DTOObjects.TopPlayerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/players")
class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Fetches a player by ID.
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public Player getPlayerById(@PathVariable Integer id) {
        return playerService.getPlayerById(id);
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

    /**
     * Gets the average points per game for a specific player.
     *
     * @param playerId the ID of the player to calculate average points for
     * @return the average points per game for the specified player
     */
    @RequestMapping("/average-points/{playerId}")
    public Double getAveragePointsPerGame(@PathVariable Integer playerId) {
        return playerService.getAveragePointsPerGame(playerId);
    }

    /**
     * Gets the average 3pts pointers per game for a specific player.
     *
     * @param playerId the ID of the player to calculate average three pointers for
     * @return the average points per game for the specified player
     */
    @RequestMapping("/average-three-pointers/{playerId}")
    public Double getAverageThreePointersPerGame(@PathVariable Integer playerId) {
        return playerService.getAverageThreePointersPerGame(playerId);
    }

    /**
     * Gets all games for a specific player, including the opponent team name, points scored, and three-pointers made.
     *
     * @param playerId the ID of the player to fetch game logs for
     * @return an iterable of TeamGameLogDTO containing game details for the specified player
     */
    @RequestMapping("/game-logs/{playerId}")
    public Iterable<PlayerGameLogDTO> getPlayerGameLogs(@PathVariable Integer playerId) {
        return playerService.getPlayerGameLogs(playerId);
    }

    /**
     * Creates a new player.
     * @param player
     * @return
     */
    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        Player saved = playerService.saveOrUpdatePlayer(player);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * Updates an existing player.
     * @param playerId
     * @param player
     * @return
     */
    @PutMapping("/{playerId}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Integer playerId, @RequestBody Player player) {
        player.setId(playerId); // Make sure ID matches URL
        Player updated = playerService.saveOrUpdatePlayer(player);
        return ResponseEntity.ok(updated);
    }
}
