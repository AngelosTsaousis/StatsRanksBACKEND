package gr.alpha.stats.ranks.playerstats;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class PlayerStatsService {
    private final PlayerStatsRepository repository;

    /**
     * Constructor for PlayerStatsService.
     * @param repository the repository to be used for player stats operations
     */
    public PlayerStatsService(PlayerStatsRepository repository) {
        this.repository = repository;
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
}
