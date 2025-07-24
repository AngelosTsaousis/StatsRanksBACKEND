package gr.alpha.stats.ranks.player;

import gr.alpha.stats.ranks.DTOObjects.PlayerGameLogDTO;
import gr.alpha.stats.ranks.DTOObjects.TopPlayerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    /**
     * Finds player by id.
     *
     * @return an iterable of all players
     */
    Optional<Player> findById(Integer id);

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
            AND ps.points <> -1
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
            SUM(ps.three_pointers) AS total
        FROM 
            player_stats ps
        JOIN 
            players p ON ps.player_id = p.id
        JOIN 
            teams t ON p.team_id = t.id
        WHERE 
            t.group_id = :groupId
            AND ps.three_pointers <> -1
        GROUP BY 
            p.id
        ORDER BY 
            total DESC
        LIMIT 3
        """, nativeQuery = true)
    Iterable<TopPlayerDTO> findTop3ThreePointersByGroupId(Integer groupId);

    /**
     * Finds the top 3 scorers in a specific league based on their total points.
     *
     * @param leagueId the ID of the league to find top scorers for
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
            CAST(t.group_id AS CHAR) LIKE CONCAT('%', :leagueId)
            AND ps.points <> -1    
        GROUP BY 
            p.id
        ORDER BY 
            total DESC
        LIMIT 3
        """, nativeQuery = true)
    Iterable<TopPlayerDTO> findTop3ScorersByLeague(Integer leagueId);

    /**
     * Finds the top 3 players with the most three-pointers in a specific league.
     *
     * @param leagueId the ID of the league to find top three-point shooters for
     * @return an iterable of TopPlayerDTO containing the top 3 players' names and their total three-pointers
     */
    @Query(value = """
        SELECT 
            p.first_name AS firstName,
            p.last_name AS lastName,
            SUM(ps.three_pointers) AS total
        FROM 
            player_stats ps
        JOIN 
            players p ON ps.player_id = p.id
        JOIN 
            teams t ON p.team_id = t.id
        WHERE 
            CAST(t.group_id AS CHAR) LIKE CONCAT('%', :leagueId)
            AND ps.three_pointers <> -1
        GROUP BY 
            p.id
        ORDER BY 
            total DESC
        LIMIT 3
        """, nativeQuery = true)
    Iterable<TopPlayerDTO> findTop3ThreePointersByLeague(Integer leagueId);

    /**
     * Finds the average points per game for a specific player.
     *
     * @param playerId the ID of the player to calculate average points for
     * @return the average points per game for the specified player
     */
    @Query(value = """
        SELECT AVG(points)
        FROM player_stats
        WHERE player_id = :playerId AND points <> -1;
        """, nativeQuery = true)
    Double findAveragePointsPerGame(Integer playerId);

    /**
     * Finds the average 3pts pointers per game for a specific player.
     *
     * @param playerId the ID of the player to calculate average three pointers for
     * @return the average points per game for the specified player
     */
    @Query(value = """
        SELECT AVG(three_pointers)
        FROM player_stats
        WHERE player_id = :playerId AND three_pointers <> -1;;
        """, nativeQuery = true)
    Double findAverageThreePointersPerGame(Integer playerId);

    /**
     * Finds all games for a specific player, including the opponent team name, points scored, and three-pointers made.
     *
     * @param playerId the ID of the player to fetch game logs for
     * @return an iterable of TeamGameLogDTO containing game details for the specified player
     */
    @Query(value = """
        SELECT
            CASE
                WHEN p.team_id = g.home_team_id THEN t_away.name
                ELSE t_home.name
            END AS opponent_team,
            ps.points,
            ps.three_pointers
        FROM player_stats ps
        JOIN players p ON ps.player_id = p.id
        JOIN games g ON ps.game_id = g.id
        JOIN teams t_home ON g.home_team_id = t_home.id
        JOIN teams t_away ON g.away_team_id = t_away.id
        WHERE ps.player_id = :playerId AND ps.points <> -1;                           
        """, nativeQuery = true)
    Iterable<PlayerGameLogDTO> findAllGamesForPlayer(Integer playerId);
}
