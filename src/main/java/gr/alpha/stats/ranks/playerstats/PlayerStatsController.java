package gr.alpha.stats.ranks.playerstats;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player-stats")
public class PlayerStatsController {

    private final PlayerStatsService service;

    public PlayerStatsController(PlayerStatsService service) {
        this.service = service;
    }

    /**
     * Retrieves all player stats.
     *
     * @return a list of all PlayerStats
     */
    @GetMapping
    public List<PlayerStats> getAllPlayerStats() {
        return service.getAllStats();
    }

    /**
     * Retrieves player stats by game ID.
     *
     * @param id the ID of the game to retrieve player stats for
     * @return a list of PlayerStats associated with the specified game ID
     */
    @GetMapping("/game/{id}")
    public List<PlayerStats> getPlayerStatsByGameId(@PathVariable Long id) {
        return service.getPlayerStatsByGameId(id);
    }

    @PostMapping
    public ResponseEntity<PlayerStats> createPlayerStats(@RequestBody PlayerStats playerStats) {
        PlayerStats saved = service.saveOrUpdatePlayerStats(playerStats);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerStats> updatePlayerStats(@PathVariable Long id, @RequestBody PlayerStats playerStats) {
        playerStats.setId(id); // Ensure the ID matches the URL
        PlayerStats updated = service.saveOrUpdatePlayerStats(playerStats);
        return ResponseEntity.ok(updated);
    }
}