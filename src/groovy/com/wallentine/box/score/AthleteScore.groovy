package com.wallentine.box.score

/**
 * The AthleteScore provides a way to combine the Athlete information along with their score and placement for a 
 * single WOD or overall.
 *
 * @author Todd Wallentine todd AT wallentine com
 */
class AthleteScore {
	Athlete athlete
	int score
	int place
	Workout wod
	
	// TODO Add compare methods that compare place and score. Useful in calling List.sort(). -todd 17Apr2013
}