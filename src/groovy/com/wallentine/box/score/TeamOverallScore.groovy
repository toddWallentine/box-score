package com.wallentine.box.score

/**
 * The TeamOverallScore provides a way to combine the Team information along with their overall place,
 * the sum of their place across WODs, and the team WOD score (reps and place).
 *
 * @author Todd Wallentine todd AT wallentine com
 */
class TeamOverallScore {
	Team team
	int place
	int totalPlace
	Map<Workout, TeamScore> wodScoreMap

	String toString() {
		return "${place} (${totalPlace}) ${team.name}"
	}
}