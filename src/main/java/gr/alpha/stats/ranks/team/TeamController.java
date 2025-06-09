package gr.alpha.stats.ranks.team;

import gr.alpha.stats.ranks.DTOObjects.TopTeamsDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
     * Generates scouting report as PDF file.
     * @param id
     * @return
     */
    @GetMapping("/generate/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Integer id) {
        Team team = teamService.findById(id).orElse(null);
        byte[] pdfBytes = teamService.generatePdf("Scouting Report", team);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "document.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

}

