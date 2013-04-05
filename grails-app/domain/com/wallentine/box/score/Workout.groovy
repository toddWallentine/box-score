package com.wallentine.box.score

/**
 * @author Todd Wallentine todd AT wallentine com
 */
class Workout {

	int number
	String name

    static constraints = {
    }

    String toString() {
    	return "$number: $name"
    }
}