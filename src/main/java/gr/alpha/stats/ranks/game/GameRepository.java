package gr.alpha.stats.ranks.game;

import gr.alpha.stats.ranks.DTOObjects.ScheduleDTO;
import gr.alpha.stats.ranks.DTOObjects.TeamStandingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    /**
     * Finds games by group ID and orders them by game date.
     *
     * @param groupId the ID of the group to find games for
     * @return an iterable of games ordered by game date
     */
    Iterable<Game> findByGroupIdOrderByGameDate(Integer groupId);

    /**
     * Finds team standings by group ID.
     *
     * @param groupId the ID of the group to find standings for
     * @return an iterable of team standings
     */
    @Query(value = """

            SELECT
            t.photo_url AS teamPhoto,
            t.name AS teamName,
        
            COUNT(CASE
                      WHEN (g.home_team_id = t.id AND g.home_team_points > g.away_team_points)
                          OR (g.away_team_id = t.id AND g.away_team_points > g.home_team_points)
                          THEN 1 END) AS wins,
        
            COUNT(CASE
                      WHEN (g.home_team_id = t.id AND g.home_team_points < g.away_team_points)
                          OR (g.away_team_id = t.id AND g.away_team_points < g.home_team_points)
                          THEN 1 END) AS losses,
        
            COUNT(CASE
                      WHEN (g.home_team_id = t.id AND g.home_team_points > g.away_team_points)
                          OR (g.away_team_id = t.id AND g.away_team_points > g.home_team_points)
                          THEN 1 END) * 2
                +
            COUNT(CASE
                      WHEN (g.home_team_id = t.id AND g.home_team_points < g.away_team_points)
                          OR (g.away_team_id = t.id AND g.away_team_points < g.home_team_points)
                          THEN 1 END) AS points,
        
            SUM(CASE
                    WHEN g.home_team_id = t.id THEN g.home_team_points
                    WHEN g.away_team_id = t.id THEN g.away_team_points
                    ELSE 0 END) AS pointsScored,
        
            SUM(CASE
                    WHEN g.home_team_id = t.id THEN g.away_team_points
                    WHEN g.away_team_id = t.id THEN g.home_team_points
                    ELSE 0 END) AS pointsReceived,
        
            SUM(CASE
                    WHEN g.home_team_id = t.id THEN g.home_team_points
                    WHEN g.away_team_id = t.id THEN g.away_team_points
                    ELSE 0 END)
                -
            SUM(CASE
                    WHEN g.home_team_id = t.id THEN g.away_team_points
                    WHEN g.away_team_id = t.id THEN g.home_team_points
                    ELSE 0 END) AS pointsDifference
        
        FROM teams t
                 LEFT JOIN games g ON t.id = g.home_team_id OR t.id = g.away_team_id
        WHERE t.group_id = :groupId
        GROUP BY t.id, t.name, t.photo_url
        ORDER BY points DESC, pointsDifference DESC;
        """, nativeQuery = true)
    Iterable<TeamStandingDTO> findTeamStandingsByGroupId(Integer groupId);


    List<Game> findByHomeTeamIdOrAwayTeamId(Integer homeTeamId, Integer awayTeamId);

    /**
     * Finds the schedule of games for a specific group.
     *
     * @param groupId the ID of the group to find the schedule for
     * @return a list of ScheduleDTO containing game details
     */
    @Query(value = """
        SELECT 
            g.game_date AS gameDate,
            ht.photo_url as homeTeamPhoto,
            ht.name AS homeTeamName,
            g.home_team_points AS homeTeamPoints,
            at.photo_url AS awayTeamPhoto,
            at.name AS awayTeamName,
            g.away_team_points AS awayTeamPoints
        FROM games g
        JOIN teams ht ON g.home_team_id = ht.id
        JOIN teams at ON g.away_team_id = at.id
        WHERE g.group_id = :groupId
        ORDER BY game_date;
        """, nativeQuery = true)
    List<ScheduleDTO> findScheduleByGroupId(Integer groupId);

}
