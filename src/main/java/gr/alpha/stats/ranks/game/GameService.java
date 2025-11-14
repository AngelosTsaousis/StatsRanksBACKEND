package gr.alpha.stats.ranks.game;

import gr.alpha.stats.ranks.DTOObjects.GameMatchDTO;
import gr.alpha.stats.ranks.DTOObjects.ScheduleDTO;
import gr.alpha.stats.ranks.DTOObjects.TeamStandingDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
class GameService {

    private  final GameRepository gameRepository;

    /**
     * Constructor for GameService.
     *
     * @param gameRepository the repository to access game data
     */
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * Retrieves all games from the repository.
     *
     * @return a list of all games
     */
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    /**
     * Retrieves games by their group ID, ordered by game date.
     *
     * @param groupId the ID of the group to find games for
     * @return an iterable of games ordered by game date
     */
    public Iterable<Game> getGamesByGroupId(Integer groupId) {
        return gameRepository.findByGroupIdOrderByGameDate(groupId);

    }

    /**
     * Get all dates of games played in a specific group.
     */
    public List<LocalDate> getGameDatesByGroupId(Integer groupId) {
        Iterable<Game> games = gameRepository.findByGroupIdOrderByGameDate(groupId);

        List<LocalDate> distinctDates = StreamSupport.stream(games.spliterator(), false)
                .map(Game::getGameDate)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        return distinctDates;
    }

    /**
     * Retrieves team standings for a specific group.
     *
     * @param groupId the ID of the group to find team standings for
     * @return an iterable of team standings
     */
    public Iterable<TeamStandingDTO> getTeamStandingsByGroupId(Integer groupId) {
        return gameRepository.findTeamStandingsByGroupId(groupId);
    }

    /**
     * Saves or updates a game in the repository.
     *
     * @param game the game to save or update
     * @return the saved or updated game
     */
    public Game saveOrUpdateGame(Game game) {
        return gameRepository.save(game);
    }

    /**
     * Retrieves all games for a specific team by its ID.
     *
     * @param teamId the ID of the team to find games for
     * @return an iterable of games for the specified team
     */
    public Iterable<Game> getGamesByTeamId(Integer teamId) {
        return gameRepository.findByHomeTeamIdOrAwayTeamId(teamId, teamId);
    }

    /**
     * Retrieves the schedule for a specific group, grouped by game date.
     *
     * @param groupId the ID of the team to find the schedule for
     * @return a map where the key is the game date and the value is a list of game matches on that date
     */
    public Map<LocalDate, List<GameMatchDTO>> getGroupedSchedule(Integer groupId) {
        List<ScheduleDTO> flatList = gameRepository.findScheduleByGroupId(groupId);

        return flatList.stream()
                .collect(Collectors.groupingBy(
                        ScheduleDTO::getGameDate, // This is the key for the map (e.g., "2025-10-05")

                        // This part creates the inner list for each date
                        Collectors.mapping(
                                // Transform the full GameData object into a simpler GameMatch object
                                gameData -> new GameMatchDTO(
                                        gameData.getHomeTeamPhoto(),
                                        gameData.getHomeTeamName(),
                                        gameData.getHomeTeamPoints(),
                                        gameData.getAwayTeamPhoto(),
                                        gameData.getAwayTeamName(),
                                        gameData.getAwayTeamPoints()
                                ),
                                Collectors.toList() // Collect the GameMatch objects into a List
                        )
                ));
    }
}
