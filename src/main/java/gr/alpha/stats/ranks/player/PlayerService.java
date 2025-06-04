package gr.alpha.stats.ranks.player;
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
}
