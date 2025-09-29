package gr.alpha.stats.ranks.DTOObjects;

public class TopPlayerDTO {
    private Integer id;
    private String photoUrl;
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
    public TopPlayerDTO(Integer id, String photoUrl, String firstName, String lastName, Long total) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
