package gr.alpha.stats.ranks.league;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leagues")
class LeagueController {
    private final LeagueService leagueService;

    /**
     * Constructor for LeagueController.
     * @param leagueService the service to be used for league operations
     */
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    // Additional methods to handle HTTP requests will be added here

    /**
     * Retrieves all leagues.
     * @return a list of League objects
     */
     @GetMapping
     public List<League> getAllLeagues() {
         return leagueService.getAllLeagues();
     }
}
