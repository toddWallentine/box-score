package com.wallentine.box.score

/**
 * The TeamScore provides a way to combine the Team information along with their score and placement for a 
 * single WOD or overall.
 *
 * @author Todd Wallentine todd AT wallentine com
 */
class TeamScore {
	Team team
	int score
	int place

	// TODO Add compare methods that compare place and score. Useful in calling List.sort(). -todd 17Apr2013
}