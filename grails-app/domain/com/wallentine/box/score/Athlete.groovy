package com.wallentine.box.score

/**
 * An Athlete is an individual that is part of a Team, has
 * a name, and a gender. An Athlete will be associated with
 * scores.
 *
 * @author Todd Wallentine todd AT wallentine com
 */
class Athlete {

	String name
	String gender

	static belongsTo = [team:Team]

    static constraints = {
    	name blank: false, nullable: false
    	gender inList: ["M", "F"]
    }

    String toString() {
    	return name
    }
}