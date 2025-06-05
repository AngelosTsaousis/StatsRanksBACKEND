package gr.alpha.stats.ranks.DTOObjects;

public class TopPlayerDTO {
    private String firstName;
    private String lastName;
    private Long total;

    /**
     * Constructor for TopPlayerDTO.
     *
     * @param firstName the first name of the player
     * @param lastName  the last name of the player
     * @param total the total points scored by the player
     */
    public TopPlayerDTO(String firstName, String lastName, Long total) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.total = total;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
}
