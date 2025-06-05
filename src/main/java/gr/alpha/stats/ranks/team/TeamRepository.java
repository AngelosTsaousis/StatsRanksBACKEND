package gr.alpha.stats.ranks.team;

import gr.alpha.stats.ranks.DTOObjects.TopTeamsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    /**
     * Finds teams by their group ID.
     *
     * @param groupId the ID of the group to find teams for
     * @return a list of teams that belong to the specified group
     */
    List<Team> findByGroupId(Integer groupId);

    /**
     * Finds the top 3 scoring teams in a specific group based on their total points.
     *
     * @param groupId the ID of the group to find top teams for
     * @return a list of TopTeamsDTO containing the top 3 teams' names and their total points
     */
    @Query(value = """
        SELECT  
            t.name AS teamName, 
            t.photo_url AS photoUrl,
            SUM(
                CASE WHEN g.home_team_id = t.id THEN g.home_team_points ELSE 0 END +
                CASE WHEN g.away_team_id = t.id THEN g.away_team_points ELSE 0 END
            ) AS totalPoints
        FROM 
            teams t
        LEFT JOIN 
            games g ON t.id = g.home_team_id OR t.id = g.away_team_id
        WHERE 
            t.group_id = :groupId
        GROUP BY 
            t.id, t.name
        ORDER BY 
            totalPoints DESC
        LIMIT 3
        """, nativeQuery = true)
    List<TopTeamsDTO> findTop3ScoringTeamsByGroupId(Integer groupId);

    /**
     * Finds the top 3 defensive teams in a specific group based on the fewest points conceded.
     *
     * @param groupId the ID of the group to find top defensive teams for
     * @return a list of TopTeamsDTO containing the top 3 defensive teams' names and their total points conceded
     */
    @Query(value = """
    SELECT 
        t.name AS teamName, 
        t.photo_url AS photoUrl, 
        SUM(
            CASE WHEN g.home_team_id = t.id THEN g.away_team_points ELSE 0 END +
            CASE WHEN g.away_team_id = t.id THEN g.home_team_points ELSE 0 END
        ) AS totalPoints
    FROM 
        teams t
    LEFT JOIN 
        games g ON t.id = g.home_team_id OR t.id = g.away_team_id
    WHERE 
        t.group_id = :groupId
    GROUP BY 
        t.id, t.name
    ORDER BY 
        pointsConceded ASC
    LIMIT 3
    """, nativeQuery = true)
    List<TopTeamsDTO> findTop3DefensiveTeamsByGroupId(Integer groupId);
}
