package gr.alpha.stats.ranks.DTOObjects;

public class TeamGameLogDTO {
    private String opponentName;
    private Integer totalPoints;
    private Integer totalThreePointers;

    // Default constructor (required by Jackson)
    public TeamGameLogDTO() {}

    // All-args constructor (used in @Query projection)
    public TeamGameLogDTO(String opponentName, Integer totalPoints, Integer totalThreePointers) {
        this.opponentName = opponentName;
        this.totalPoints = totalPoints;
        this.totalThreePointers = totalThreePointers;
    }

    // Getters
    public String getOpponentName() {
        return opponentName;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public Integer getTotalThreePointers() {
        return totalThreePointers;
    }

    // Optional: Setters (only needed if you plan to modify after creation)
    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void setTotalThreePointers(Integer totalThreePointers) {
        this.totalThreePointers = totalThreePointers;
    }
}
