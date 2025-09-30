package gr.alpha.stats.ranks.DTOObjects;

public class TopTeamsDTO {
    private Integer id;
    private String teamName;
    private String photoUrl;
    private Long totalPoints;

    public TopTeamsDTO(Integer id, String teamName, String photoUrl, Long totalPoints) {
        this.id = id;
        this.teamName = teamName;
        this.photoUrl = photoUrl;
        this.totalPoints = totalPoints;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public Long getTotalPoints() {
        return totalPoints;
    }
    public void setTotalPoints(Long totalPoints) {
        this.totalPoints = totalPoints;
    }
}
