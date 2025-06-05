package gr.alpha.stats.ranks.player;
import gr.alpha.stats.ranks.DTOObjects.TopPlayerDTO;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private  final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Fetches all players from the database.
     * @return
     */
    public Iterable<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    /**
     * Fetches players by team ID.
     * @param teamId
     * @return
     */
    public Iterable<Player> getTeamPlayers(Integer teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    /**
     * Fetches top 3 scores for a specific Group ID.
     * @param groupId
     */
    public Iterable<TopPlayerDTO> getTop3Scores(Integer groupId) {
        return playerRepository.findTop3ScorersByGroupId(groupId);
    }

    /**
     * Fetches top 3 three-pointers for a specific Group ID.
     * @param groupId
     */
    public Iterable<TopPlayerDTO> getTop3ThreePointers(Integer groupId) {
        return playerRepository.findTop3ThreePointersByGroupId(groupId);
    }

}
