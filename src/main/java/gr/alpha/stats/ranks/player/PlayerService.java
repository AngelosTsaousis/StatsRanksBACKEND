package gr.alpha.stats.ranks.player;
import gr.alpha.stats.ranks.DTOObjects.PlayerGameLogDTO;
import gr.alpha.stats.ranks.DTOObjects.TopPlayerDTO;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private  final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Fetches a player by ID.
     * @param id
     * @return
     */
    public Player getPlayerById(Integer id) {
        return playerRepository.findById(id).orElse(null);
    }
    /**
     * Fetches all players from the database.
     * @return
     */
    public Iterable<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    /**
     * Fetches players by team ID.
     * @param teamId
     * @return
     */
    public Iterable<Player> getTeamPlayers(Integer teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    /**
     * Fetches top 3 scores for a specific Group ID.
     * @param groupId
     */
    public Iterable<TopPlayerDTO> getTop3Scores(Integer groupId) {
        return playerRepository.findTop3ScorersByGroupId(groupId);
    }

    /**
     * Fetches top 3 three-pointers for a specific Group ID.
     * @param groupId
     */
    public Iterable<TopPlayerDTO> getTop3ThreePointers(Integer groupId) {
        return playerRepository.findTop3ThreePointersByGroupId(groupId);
    }

    /**
     * Fetches top 3 scorers per league.
     * @param leagueId
     * @return
     */
    public Iterable<TopPlayerDTO> getTop3ScorersByLeague(Integer leagueId) {
        return playerRepository.findTop3ScorersByLeague(leagueId);
    }

    /**
     * Fetches top 3 three-pointers per league.
     * @param leagueId
     * @return
     */
    public Iterable<TopPlayerDTO> getTop3ThreePointersByLeague(Integer leagueId) {
        return playerRepository.findTop3ThreePointersByLeague(leagueId);
    }

    /**
     * Gets the average points per game for a specific player.
     *
     * @param playerId the ID of the player to calculate average points for
     * @return the average points per game for the specified player
     */
    public Double getAveragePointsPerGame(Integer playerId) {
        return playerRepository.findAveragePointsPerGame(playerId);
    }

    /**
     * Gets the average 3pts pointers per game for a specific player.
     *
     * @param playerId the ID of the player to calculate average three pointers for
     * @return the average points per game for the specified player
     */
    public Double getAverageThreePointersPerGame(Integer playerId) {
        return playerRepository.findAverageThreePointersPerGame(playerId);
    }

    /**
     * Gets all games for a specific player, including the opponent team name, points scored, and three-pointers made.
     *
     * @param playerId the ID of the player to fetch game logs for
     * @return an iterable of TeamGameLogDTO containing game details for the specified player
     */
    public Iterable<PlayerGameLogDTO> getPlayerGameLogs(Integer playerId) {
        return playerRepository.findAllGamesForPlayer(playerId);
    }
}
