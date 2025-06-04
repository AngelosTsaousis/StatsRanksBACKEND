package gr.alpha.stats.ranks.team;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    /**
     * Finds teams by their group ID.
     *
     * @param groupId the ID of the group to find teams for
     * @return a list of teams that belong to the specified group
     */
    List<Team> findByGroupId(Integer groupId);
}
