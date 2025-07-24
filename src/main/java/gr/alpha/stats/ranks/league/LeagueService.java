package gr.alpha.stats.ranks.league;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LeagueService {

    private final LeagueRepository repository;

    /**
     * Constructor for LeagueService.
     * @param repository the repository to be used for league operations
     */
    public LeagueService(LeagueRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all leagues from the repository.
     * @return a list of League objects
     */
    public List<League> getAllLeagues() {
        return repository.findAll();
    }

}
