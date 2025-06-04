package gr.alpha.stats.ranks.player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    /**
     * Finds players by their team ID.
     *
     * @param teamId the ID of the team
     * @return an iterable of players belonging to the specified team
     */
    Iterable<Player> findByTeamId(Integer teamId);
}
