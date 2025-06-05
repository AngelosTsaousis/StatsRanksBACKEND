package gr.alpha.stats.ranks.team;
import gr.alpha.stats.ranks.DTOObjects.TopTeamsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
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

}
