package gr.alpha.stats.ranks.DTOObjects;

import java.math.BigDecimal;

public class PlayerAveragesDTO {
    private String firstName;
    private String lastName;
    private Double ppg;
    private Double tpg;

    public PlayerAveragesDTO() {}

    public PlayerAveragesDTO(String firstName, String lastName, BigDecimal pointsPerGame, BigDecimal threePointersPerGame) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ppg = pointsPerGame != null ? pointsPerGame.doubleValue() : 0.0;
        this.tpg = threePointersPerGame != null ? threePointersPerGame.doubleValue() : 0.0;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Double getPpg() { return ppg; }
    public Double getTpg() { return tpg; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPpg(Double ppg) { this.ppg = ppg; }
    public void setTpg(Double tpg) { this.tpg = tpg; }
}
