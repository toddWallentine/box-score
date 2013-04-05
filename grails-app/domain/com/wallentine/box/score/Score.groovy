package com.wallentine.box.score

/**
 * @author Todd Wallentine todd AT wallentine com
 */
class Score {

	int reps

	static belongsTo = [athlete:Athlete, wod:Workout]

    static constraints = {
    }
}
