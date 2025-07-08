package gr.alpha.stats.ranks.team;
import gr.alpha.stats.ranks.DTOObjects.PlayerAveragesDTO;
import gr.alpha.stats.ranks.DTOObjects.TeamGameLogDTO;
import gr.alpha.stats.ranks.DTOObjects.TopTeamsDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     * Find team by team id.
     *
     * @param id
     * @return
     */
    public Optional<Team> findById(Integer id) {
        return teamRepository.findById(id);
    }

    /**
     * Fetches all teams from the database.
     * @return
     */
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    /**
     * Fetches teams by group ID.
     * @param groupId
     * @return
     */
    public List<Team> getGroupTeams(Integer groupId) {
        return teamRepository.findByGroupId(groupId);
    }

    /**
     * Fetches the top 3 scoring teams in a specific group.
     * @param groupId
     * @return
     */
    public List<TopTeamsDTO> getTop3ScoringTeamsByGroupId(Integer groupId) {
        return teamRepository.findTop3ScoringTeamsByGroupId(groupId);
    }

    /**
     * Fetches the top 3 defensive teams in a specific group.
     * @param groupId
     * @return
     */
    public List<TopTeamsDTO> getTop3DefensiveTeamsByGroupId(Integer groupId) {
        return teamRepository.findTop3DefensiveTeamsByGroupId(groupId);
    }

    /**
     * Get top 3 scoring teams for a specific league.
     * @param leagueId
     * @return
     */
    public List<TopTeamsDTO> getTop3ScoringTeamsByLeagueId(Integer leagueId) {
        return teamRepository.findTop3ScoringLeagueTeams(leagueId);
    }

    /**
     * Get top 3 defensive teams for a specific league.
     * @param leagueId
     * @return
     */
    public List<TopTeamsDTO> getTop3DefensiveTeamsByLeagueId(Integer leagueId) {
        return teamRepository.findTop3DefenciveLeagueTeams(leagueId);
    }

    /**
     * Get the average points scored by a team based on its ID.
     * @param teamId
     * @return
     */
    public Double getAveragePointsByTeamId(Integer teamId) {
        return teamRepository.findTeamsAveragePointsByTeamId(teamId);
    }

    /**
     * Finds the average three-pointers made by a team based on its ID.
     *
     * @param teamId the ID of the team to find the average three-pointers for
     * @return the average three-pointers made by the team
     */
    public Double getAverageThreePointersByTeamId(Integer teamId) {
        return teamRepository.findAverageThreePointersByTeamId(teamId);
    }

    /**
     * Gets game logs for a team based on its ID, including opponent name, total points, and total three-pointers.
     * @param teamId
     * @return
     */
    public List<TeamGameLogDTO> getTeamGameLogsByTeamId(Integer teamId) {
        return teamRepository.findGameLogsByTeamId(teamId);
    }

    /**
     * Get player averages (points per game and three-pointers per game) for a specific team.
     * @param teamId
     * @return
     */
    public List<PlayerAveragesDTO> getPlayerAveragesByTeamId(Integer teamId) {
        return teamRepository.findPlayerAveragesByTeamId(teamId);
    }
}
