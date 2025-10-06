package gr.alpha.stats.ranks.team;

import gr.alpha.stats.ranks.DTOObjects.PlayerAveragesDTO;
import gr.alpha.stats.ranks.DTOObjects.TeamGameLogDTO;
import gr.alpha.stats.ranks.DTOObjects.TopTeamsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    /**
     * Find team by team id.
     *
     * @param id
     * @return
     */
    Optional<Team> findById(Integer id);

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
            t.id AS id,
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
        t.id AS id,
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
        totalPoints ASC
    LIMIT 3
    """, nativeQuery = true)
    List<TopTeamsDTO> findTop3DefensiveTeamsByGroupId(Integer groupId);

    @Query(value = """
        SELECT  
            t.id AS id,
            t.name AS teamName, 
            t.photo_url AS photoUrl,
            SUM(
                CASE 
                    WHEN g.home_team_id = t.id AND g.home_team_points IS NOT NULL THEN g.home_team_points 
                    ELSE 0 
                END +
                CASE 
                    WHEN g.away_team_id = t.id AND g.away_team_points IS NOT NULL THEN g.away_team_points 
                    ELSE 0 
                END
            ) AS totalPoints
        FROM 
            teams t
        LEFT JOIN 
            games g ON t.id = g.home_team_id OR t.id = g.away_team_id
        WHERE (t.group_id % 10) = :leagueId            
        GROUP BY 
            t.id, t.name
        ORDER BY 
            totalPoints DESC
        LIMIT 3
        """, nativeQuery = true)
    List<TopTeamsDTO> findTop3ScoringLeagueTeams(Integer leagueId);

    @Query(value = """
        SELECT  
            t.id AS id,
            t.name AS teamName, 
            t.photo_url AS photoUrl,
            SUM(
                CASE 
                    WHEN g.home_team_id = t.id AND g.home_team_points IS NOT NULL THEN g.away_team_points 
                    ELSE 0 
                END +
                CASE 
                    WHEN g.away_team_id = t.id AND g.away_team_points IS NOT NULL THEN g.home_team_points 
                    ELSE 0 
                END
            ) AS totalPoints
        FROM 
            teams t
        LEFT JOIN 
            games g ON t.id = g.home_team_id OR t.id = g.away_team_id
        WHERE 
            (t.group_id % 10) = :leagueId
        GROUP BY 
            t.id, t.name
        ORDER BY 
            totalPoints ASC
        LIMIT 3
        """, nativeQuery = true)
    List<TopTeamsDTO> findTop3DefenciveLeagueTeams(Integer leagueId);

    /**
     * Finds the average points scored by a team based on its ID.
     *
     * @param teamId the ID of the team to find the average points for
     * @return the average points scored by the team
     */
    @Query(value = """
        SELECT 
            AVG(
                CASE 
                    WHEN g.home_team_id = t.id THEN g.home_team_points 
                    ELSE g.away_team_points 
                END
            ) AS averagePoints
        FROM 
            teams t
        LEFT JOIN 
            games g ON t.id = g.home_team_id OR t.id = g.away_team_id
        WHERE 
            t.id = :teamId
        GROUP BY 
            t.id
        """, nativeQuery = true)
    Double findTeamsAveragePointsByTeamId(Integer teamId);


    /**
     * Finds the average three-pointers made by a team based on its ID.
     *
     * @param teamId the ID of the team to find the average three-pointers for
     * @return the average three-pointers made by the team
     */
    @Query(value = """
        SELECT AVG(ps.three_pointers)
            FROM player_stats ps
            JOIN players p ON ps.player_id = p.id
            WHERE p.team_id = :teamId
            AND ps.three_pointers <> -1;
            """, nativeQuery = true)
    Double findAverageThreePointersByTeamId(Integer teamId);


    /**
     * Finds game logs for a team based on its ID, including opponent name, total points, and total three-pointers.
     * @param teamId
     * @return
     */
    @Query(value = """
        SELECT
            CASE
                WHEN g.home_team_id = :teamId THEN t.name
                WHEN g.away_team_id = :teamId THEN tH.name
                END AS opponent_team,
            CASE
                WHEN g.home_team_id = :teamId THEN g.home_team_points
                WHEN g.away_team_id = :teamId THEN g.away_team_points
                END AS total_points,
            SUM(ps.three_pointers) AS total_threes
        FROM games g
        JOIN teams t on g.away_team_id = t.id
        JOIN teams tH on g.home_team_id = tH.id
        JOIN player_stats ps on ps.game_id = g.id
        JOIN players p on ps.player_id = p.id
        WHERE (g.home_team_id = :teamId OR g.away_team_id = :teamId)
        AND g.home_team_points IS NOT NULL
        AND g.away_team_points IS NOT NULL
        AND ps.three_pointers <> -1
        AND p.team_id = :teamId
        GROUP BY g.id, team_name;
            """, nativeQuery = true)
    List<TeamGameLogDTO> findGameLogsByTeamId(Integer teamId);


    /**
     * Finds player averages (points per game and three-pointers per game) for a specific team.
     * @param teamId
     * @return
     */
    @Query(value = """
        SELECT
            p.first_name,
            p.last_name,
            AVG(ps.points) AS ppg,
            AVG(ps.three_pointers) AS tpg
        FROM
            players p
        JOIN
            player_stats ps ON ps.player_id = p.id
        WHERE
            p.team_id = :teamId
        AND ps.points <> -1 AND ps.three_pointers <> -1
        GROUP BY
            p.id, p.first_name, p.last_name
        ORDER BY
            ppg DESC;
            """, nativeQuery = true)
    List<PlayerAveragesDTO> findPlayerAveragesByTeamId(Integer teamId);

}
