package gr.alpha.stats.ranks.team;
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

}
