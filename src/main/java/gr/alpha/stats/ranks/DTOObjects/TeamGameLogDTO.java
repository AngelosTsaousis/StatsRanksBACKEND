package gr.alpha.stats.ranks.DTOObjects;

public class TeamGameLogDTO {
    private String opponentName;
    private Integer totalPoints;
    private Long totalThreePointers;

    // Default constructor (required by Jackson)
    public TeamGameLogDTO() {}

    // All-args constructor (used in @Query projection)
    public TeamGameLogDTO(String opponentName, Integer totalPoints, Long totalThreePointers) {
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

    public Long getTotalThreePointers() {
        return totalThreePointers;
    }

    // Optional: Setters (only needed if you plan to modify after creation)
    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void setTotalThreePointers(Long totalThreePointers) {
        this.totalThreePointers = totalThreePointers;
    }
}
