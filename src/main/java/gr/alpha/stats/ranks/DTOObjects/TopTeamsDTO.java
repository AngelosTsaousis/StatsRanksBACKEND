package gr.alpha.stats.ranks.DTOObjects;

public class TopTeamsDTO {

    private String teamName;
    private String photoUrl;
    private Long totalPoints;

    public TopTeamsDTO(String teamName, String photoUrl, Long totalPoints) {
        this.teamName = teamName;
        this.photoUrl = photoUrl;
        this.totalPoints = totalPoints;
    }

    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Long getPoints() {
        return totalPoints;
    }
    public void setPoints(Long points) {
        this.totalPoints = points;
    }
}
