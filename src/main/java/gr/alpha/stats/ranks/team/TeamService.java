package gr.alpha.stats.ranks.team;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import gr.alpha.stats.ranks.DTOObjects.TopTeamsDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     * Find team by team id.
     *
     * @param id
     * @return
     */
    public Optional<Team> findById(Integer id) {
        return teamRepository.findById(id);
    }

    /**
     * Fetches all teams from the database.
     * @return
     */
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    /**
     * Fetches teams by group ID.
     * @param groupId
     * @return
     */
    public List<Team> getGroupTeams(Integer groupId) {
        return teamRepository.findByGroupId(groupId);
    }

    /**
     * Fetches the top 3 scoring teams in a specific group.
     * @param groupId
     * @return
     */
    public List<TopTeamsDTO> getTop3ScoringTeamsByGroupId(Integer groupId) {
        return teamRepository.findTop3ScoringTeamsByGroupId(groupId);
    }

    /**
     * Fetches the top 3 defensive teams in a specific group.
     * @param groupId
     * @return
     */
    public List<TopTeamsDTO> getTop3DefensiveTeamsByGroupId(Integer groupId) {
        return teamRepository.findTop3DefensiveTeamsByGroupId(groupId);
    }

    /**
     * Get top 3 scoring teams for a specific league.
     * @param leagueId
     * @return
     */
    public List<TopTeamsDTO> getTop3ScoringTeamsByLeagueId(Integer leagueId) {
        return teamRepository.findTop3ScoringLeagueTeams(leagueId);
    }

    /**
     * Get top 3 defensive teams for a specific league.
     * @param leagueId
     * @return
     */
    public List<TopTeamsDTO> getTop3DefensiveTeamsByLeagueId(Integer leagueId) {
        return teamRepository.findTop3DefenciveLeagueTeams(leagueId);
    }

    public byte[] generatePdf(String title, Team team) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Add title
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Paragraph titleParagraph = new Paragraph(title, titleFont);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(titleParagraph);
            document.add(Chunk.NEWLINE);

            String photoUrl = team.getPhotoUrl();
            String teamName = team.getName();
            if (photoUrl != null && !photoUrl.isEmpty()) {
                try {
                    Image img = Image.getInstance(photoUrl);
                    img.scaleToFit(200 , 200);
                    img.setAlignment(Element.ALIGN_LEFT);

                    // Create a table with two columns: photo and team name
                    PdfPTable table = new PdfPTable(2);
                    table.setWidthPercentage(100);
                    table.setWidths(new int[]{1, 3});

                    PdfPCell imageCell = new PdfPCell(img, true);
                    imageCell.setBorder(Rectangle.NO_BORDER);
                    imageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    Font nameFont = new Font(Font.HELVETICA, 22, Font.BOLD);
                    Phrase namePhrase = new Phrase("TEST ONOMA", nameFont);
                    PdfPCell nameCell = new PdfPCell(namePhrase);
                    nameCell.setBorder(Rectangle.NO_BORDER);
                    nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);

                    table.addCell(imageCell);
                    table.addCell(nameCell);

                    document.add(table);
                    document.add(Chunk.NEWLINE);
                } catch (Exception ex) {
                    // Optionally log or handle image loading errors
                }
            }


            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
