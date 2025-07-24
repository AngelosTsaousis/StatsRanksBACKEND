package gr.alpha.stats.ranks.DTOObjects;

public class PlayerGameLogDTO {
    private String opponentName;
    private int totalPoints;
    private int totalThreePointers;

    // Default constructor (required by Jackson)
    public PlayerGameLogDTO() {}

    // All-args constructor (used in @Query projection)
    public PlayerGameLogDTO(String opponentName, int totalPoints, int totalThreePointers) {
        this.opponentName = opponentName;
        this.totalPoints = totalPoints;
        this.totalThreePointers = totalThreePointers;
    }

    // Getters
    public String getOpponentName() {
        return opponentName;
    }
    public int getTotalPoints() {
        return totalPoints;
    }
    public int getTotalThreePointers() {
        return totalThreePointers;
    }

    // Optional: Setters (only needed if you plan to modify after creation)
    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
    public void setTotalThreePointers(int totalThreePointers) {
        this.totalThreePointers = totalThreePointers;
    }
}
