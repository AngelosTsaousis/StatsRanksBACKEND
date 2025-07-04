package gr.alpha.stats.ranks.team;

import gr.alpha.stats.ranks.DTOObjects.PlayerAveragesDTO;
import gr.alpha.stats.ranks.DTOObjects.TeamGameLogDTO;
import gr.alpha.stats.ranks.DTOObjects.TopTeamsDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
class TeamController {

    private final TeamService teamService;

    /**
     * Constructor for TeamController.
     *
     * @param teamService the service to handle team-related operations
     */
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * Fetches a team by its ID.
     *
     * @param teamId the ID of the team to find
     * @return the team with the specified ID
     */
    @GetMapping("/{teamId}")
    public Team getTeamById(@PathVariable Integer teamId) {
        Optional<Team> team = teamService.findById(teamId);
        return team.orElseThrow(() -> new RuntimeException("Team not found with id: " + teamId));
    }

    /**
     * Fetches all teams from the database.
     *
     * @return
     */
    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    /**
     * Fetches teams by group ID.
     *
     * @param groupId the ID of the group to find teams for
     * @return a list of teams that belong to the specified group
     */
    @GetMapping("/group/{groupId}")
    public List<Team> getGroupTeams(@PathVariable Integer groupId) {
        return teamService.getGroupTeams(groupId);
    }

    /**
     * Fetches the top 3 scoring teams in a specific group.
     *
     * @param groupId the ID of the group to find top teams for
     * @return a list of TopTeamsDTO containing the top 3 teams' names and their total points
     */
    @GetMapping("/group/{groupId}/top3Offensive")
    public List<TopTeamsDTO> getTop3ScoringTeamsByGroupId(@PathVariable Integer groupId) {
        return teamService.getTop3ScoringTeamsByGroupId(groupId);
    }

    /**
     * Fetches the top 3 defensive teams in a specific group.
     *
     * @param groupId the ID of the group to find top defensive teams for
     * @return a list of TopTeamsDTO containing the top 3 defensive teams' names and their total points conceded
     */
    @GetMapping("/group/{groupId}/top3Defensive")
    public List<TopTeamsDTO> getTop3DefensiveTeamsByGroupId(@PathVariable Integer groupId) {
        return teamService.getTop3DefensiveTeamsByGroupId(groupId);
    }

    /**
     * Get top 3 scoring teams for a specific league.
     *
     * @param leagueId the ID of the league to find top teams for
     * @return a list of TopTeamsDTO containing the top 3 scoring teams' names and their total points
     */
    @GetMapping("/league/{leagueId}/top3Offensive")
    public List<TopTeamsDTO> getTop3ScoringTeamsByLeagueId(@PathVariable Integer leagueId) {
        return teamService.getTop3ScoringTeamsByLeagueId(leagueId);
    }

    /**
     * Get top 3 defensive teams for a specific league.
     *
     * @param leagueId the ID of the league to find top defensive teams for
     * @return a list of TopTeamsDTO containing the top 3 defensive teams' names and their total points conceded
     */
    @GetMapping("/league/{leagueId}/top3Defensive")
    public List<TopTeamsDTO> getTop3DefensiveTeamsByLeagueId(@PathVariable Integer leagueId) {
        return teamService.getTop3DefensiveTeamsByLeagueId(leagueId);
    }

    /**
     * Get the average points scored by a team based on its ID.
     *
     * @param teamId the ID of the team to calculate average points for
     * @return the average points scored by the team
     */
    @GetMapping("/averagePoints/{teamId}")
    public Double getAveragePointsByTeamId(@PathVariable Integer teamId) {
        return teamService.getAveragePointsByTeamId(teamId);
    }

    /**
     * Get the average three-pointers made by a team based on its ID.
     *
     * @param teamId the ID of the team to find the average three-pointers for
     * @return the average three-pointers made by the team
     */
    @GetMapping("/averageThreePointers/{teamId}")
    public Double getAverageThreePointersByTeamId(@PathVariable Integer teamId) {
        return teamService.getAverageThreePointersByTeamId(teamId);
    }

    /**
     * Gets game logs for a team based on its ID, including opponent name, total points, and total three-pointers.
     * @param teamId
     * @return
     */
    @GetMapping("/gameLogs/{teamId}")
    public List<TeamGameLogDTO> getTeamGameLogs(@PathVariable Integer teamId) {
        return teamService.getTeamGameLogsByTeamId(teamId);
    }

    /**
     * Get player averages (points per game and three-pointers per game) for a specific team.
     * @param teamId
     * @return
     */
    @GetMapping("/playerAverages/{teamId}")
    public List<PlayerAveragesDTO> getPlayerAveragesByTeamId(@PathVariable Integer teamId) {
        return teamService.getPlayerAveragesByTeamId(teamId);
    }

}

