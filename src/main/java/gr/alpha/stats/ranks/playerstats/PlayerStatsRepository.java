package gr.alpha.stats.ranks.playerstats;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {
    /**
     * Finds all PlayerStats by the specified game ID.
     *
     * @param gameId the ID of the game
     * @return a list of PlayerStats associated with the specified game ID
     */
    List<PlayerStats> findAllByGameId(Long gameId);
}
