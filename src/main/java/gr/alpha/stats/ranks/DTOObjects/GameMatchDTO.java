package gr.alpha.stats.ranks.DTOObjects;

public record GameMatchDTO(
        String homeTeamPhoto,
        String homeTeamName,
        Integer homeTeamPoints,
        String awayTeamPhoto,
        String awayTeamName,
        Integer awayTeamPoints) {}
