package com.wallentine.box.score

/**
 * The AthleteOverallScore provides a way to combine the Athlete information along with their overall place, the sum of their place across WODs, and then
 * their individual WOD score (reps and place).
 *
 * @author Todd Wallentine todd AT wallentine com
 */
class AthleteOverallScore {
	Athlete athlete
	int place
	int totalPlace
	Map<Workout, AthleteScore> wodScoreMap
}