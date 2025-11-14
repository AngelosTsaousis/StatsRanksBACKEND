package gr.alpha.stats.ranks.DTOObjects;

import java.time.LocalDate;

public interface ScheduleDTO {
    LocalDate getGameDate();
    String getHomeTeamPhoto();
    String getHomeTeamName();
    Integer getHomeTeamPoints();
    String getAwayTeamPhoto();
    String getAwayTeamName();
    Integer getAwayTeamPoints();
}
