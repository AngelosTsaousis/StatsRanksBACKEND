package gr.alpha.stats.ranks.team;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}

