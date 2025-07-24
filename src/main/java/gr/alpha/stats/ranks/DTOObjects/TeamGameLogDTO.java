package gr.alpha.stats.ranks.DTOObjects;

public class TeamGameLogDTO {
    private String opponentName;
    private Long totalPoints;
    private Long totalThreePointers;

    // Default constructor (required by Jackson)
    public TeamGameLogDTO() {}

    // All-args constructor (used in @Query projection)
    public TeamGameLogDTO(String opponentName, Long totalPoints, Long totalThreePointers) {
        this.opponentName = opponentName;
        this.totalPoints = totalPoints;
        this.totalThreePointers = totalThreePointers;
    }

    // Getters
    public String getOpponentName() {
        return opponentName;
    }

    public Long getTotalPoints() {
        return totalPoints;
    }

    public Long getTotalThreePointers() {
        return totalThreePointers;
    }

    // Optional: Setters (only needed if you plan to modify after creation)
    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public void setTotalPoints(Long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void setTotalThreePointers(Long totalThreePointers) {
        this.totalThreePointers = totalThreePointers;
    }
}
