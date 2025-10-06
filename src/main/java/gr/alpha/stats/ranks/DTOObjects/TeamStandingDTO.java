package gr.alpha.stats.ranks.DTOObjects;

public class TeamStandingDTO {
    private String team_photo;
    private String team_name;
    private Long wins;
    private Long losses;
    private Long points;
    private Long points_scored;
    private Long points_received;
    private Long point_difference;

    public TeamStandingDTO(String team_photo, String team_name, Long wins, Long losses, Long points, Long points_scored, Long points_received, Long point_difference) {
        this.team_photo = team_photo;
        this.team_name = team_name;
        this.wins = wins;
        this.losses = losses;
        this.points = points;
        this.points_scored = points_scored;
        this.points_received = points_received;
        this.point_difference = point_difference;
    }

    public String getTeam_photo() {
        return team_photo;
    }
    public void setTeam_photo(String team_photo) {
        this.team_photo = team_photo;
    }
    public String getTeam_name() {
        return team_name;
    }
    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }
    public Long getWins() {
        return wins;
    }
    public void setWins(Long wins) {
        this.wins = wins;
    }
    public Long getLosses() {
        return losses;
    }
    public void setLosses(Long losses) {
        this.losses = losses;
    }
    public Long getPoints() {
        return points;
    }
    public void setPoints(Long points) {
        this.points = points;
    }
    public Long getPoints_scored() {
        return points_scored;
    }
    public void setPoints_scored(Long points_scored) {
        this.points_scored = points_scored;
    }
    public Long getPoints_received() {
        return points_received;
    }
    public void setPoints_received(Long points_received) {
        this.points_received = points_received;
    }

    public Long getPoint_difference() {
        return point_difference;
    }

    public void setPoint_difference(Long point_difference) {
        this.point_difference = point_difference;
    }
}
