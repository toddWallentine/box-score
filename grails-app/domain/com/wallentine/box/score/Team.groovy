package com.wallentine.box.score

/**
 * A Team holds athletes and has a name. It maps to a CrossFit Box
 * or other grouping of athletes.
 *
 * @author Todd Wallentine todd AT wallentine com
 */
class Team {

	String name

	static hasMany = [athletes:Athlete]

    static constraints = {
    	name blank: false, nullable: false
    }

    String toString() {
    	return name
    }
}