package gr.alpha.stats.ranks.playerstats;

import gr.alpha.stats.ranks.game.Game;
import gr.alpha.stats.ranks.player.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerStatsService {
    private final PlayerStatsRepository repository;

    /**
     * Constructor for PlayerStatsService.
     * @param repository the repository to be used for player stats operations
     */
    public PlayerStatsService(PlayerStatsRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves player stats by game ID from the repository.
     * @param gameId the ID of the game
     * @return a list of PlayerStats objects associated with the specified game ID
     */
    public List<PlayerStats> getPlayerStatsByGameId(Long gameId) {
        return repository.findAllByGameId(gameId);
    }

    /**
     * Retrieves all player stats from the repository.
     * @return a list of PlayerStats objects
     */
    public List<PlayerStats> getAllStats() {
        return repository.findAll();
    }

    /**
     * Saves or updates player stats in the repository.
     * @param playerStats
     * @return
     */
    public PlayerStats saveOrUpdatePlayerStats(PlayerStats playerStats) {
        return repository.save(playerStats);
    }

    /**
     * Creates a new PlayerStats object for a player in a specific game.
     * @param player the player for whom the stats are being created
     * @param game the game in which the stats are being recorded
     * @return the newly created PlayerStats object
     */
    public PlayerStats createNewPlayerStats(Player player, Game game) {
        PlayerStats playerStats = new PlayerStats();
        playerStats.setPlayer(player);
        playerStats.setGame(game);
        playerStats.setPoints(-1);
        playerStats.setThreePointers(-1);
        return repository.save(playerStats);
    }
}
