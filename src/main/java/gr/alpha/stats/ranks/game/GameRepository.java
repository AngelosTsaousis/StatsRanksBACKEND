package gr.alpha.stats.ranks.game;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {

    /**
     * Finds games by group ID and orders them by game date.
     *
     * @param groupId the ID of the group to find games for
     * @return an iterable of games ordered by game date
     */
    Iterable<Game> findByGroupIdOrderByGameDate(Integer groupId);
}
