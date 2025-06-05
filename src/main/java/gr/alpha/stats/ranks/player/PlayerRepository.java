package gr.alpha.stats.ranks.player;

import gr.alpha.stats.ranks.DTOObjects.TopPlayerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    /**
     * Finds players by their team ID.
     *
     * @param teamId the ID of the team
     * @return an iterable of players belonging to the specified team
     */
    Iterable<Player> findByTeamId(Integer teamId);

    /**
     * Finds the top 3 scorers in a specific group based on their total points.
     *
     * @param groupId the ID of the group to find top scorers for
     * @return an iterable of TopPlayerDTO containing the top 3 players' names and their total points
     */
    @Query(value = """
        SELECT 
            p.first_name AS firstName,
            p.last_name AS lastName,
            SUM(ps.points) AS total
        FROM 
            player_stats ps
        JOIN 
            players p ON ps.player_id = p.id
        JOIN 
            teams t ON p.team_id = t.id
        WHERE 
            t.group_id = :groupId
        GROUP BY 
            p.id
        ORDER BY 
            total DESC
        LIMIT 3
        """, nativeQuery = true)
    Iterable<TopPlayerDTO> findTop3ScorersByGroupId(Integer groupId);

    /**
     * Finds the top 3 players with the most three-pointers in a specific group.
     *
     * @param groupId the ID of the group to find top three-point shooters for
     * @return an iterable of TopPlayerDTO containing the top 3 players' names and their total three-pointers
     */
    @Query(value = """
        SELECT 
            p.first_name AS firstName,
            p.last_name AS lastName,
            p.
            SUM(ps.three_pointers) AS total
        FROM 
            player_stats ps
        JOIN 
            players p ON ps.player_id = p.id
        JOIN 
            teams t ON p.team_id = t.id
        WHERE 
            t.group_id = :groupId
        GROUP BY 
            p.id
        ORDER BY 
            total DESC
        LIMIT 3
        """, nativeQuery = true)
    Iterable<TopPlayerDTO> findTop3ThreePointersByGroupId(Integer groupId);

}
