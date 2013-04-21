package com.wallentine.box.score

/**
 * @author Todd Wallentine todd AT wallentine com
 */
class ScoreController {
	def scaffold = Score

	/**
	 * Display an individual leaderboard for a specific WOD.
	 *
	 * @param id The WOD ID.
	 */
	def board() {
		def wodID = params.id
		def wod = Workout.get(wodID)

		def maleScoreMap = getScoreMap(wod, "M")
		def femaleScoreMap = getScoreMap(wod, "F")

		render(view: "board", model: [wod: wod, maleScoreMap: maleScoreMap, femaleScoreMap: femaleScoreMap])	
	}

	/**
	 * Display the team leaderboard for a specific WOD.
	 *
	 * @param id The WOD ID.
	 */
	def board2() {
		def wodID = params.id
		def wod = Workout.get(wodID)

		// Collect up the best 3 male and best 3 female athletes to create the team score.
		def teamScores = []
		Team.list().each { team ->
			def teamReps = 0 // TODO Count the reps for top 3 male and top 3 female

			def maleScores = Score.findAll(sort: "reps", order: "desc", max: 3) {
				wod == wod && athlete.gender == "M" && athlete.team == team
			}
			maleScores.each { score ->
				teamReps += score.reps
			}
			def femaleScores = Score.findAll(sort: "reps", order: "desc", max: 3) {
				wod == wod && athlete.gender == "F" && athlete.team == team
			}
			femaleScores.each { score ->
				teamReps += score.reps
			}

			def teamScore = new TeamScore(team: team, score: teamReps)
			teamScores.add(teamScore)
		}

		// Sort the TeamScore instances on reps
		def teamScoreComparator = [
			compare: { a,b ->
				a.equals(b)? 0: a.score > b.score? -1: 1
			}
		] as Comparator
		teamScores.sort(true, teamScoreComparator)

		// Set the place (after sorting)
		def lastScore = -1
		def place = 0
		teamScores.each { teamScore ->
			def score = teamScore.score

			if(score != lastScore) place++
			teamScore.place = place
		}

		render(view: "board2", model: [wod: wod, teamScores: teamScores])
	}

	/**
	 * Display the overall individual leaderboard.
	 */
	def board3() {

		def wods = Workout.list()
		/*
		def maleScores = []
		def rawMaleScores = Score.findAll(sort: "reps", order: "desc") {
			athlete.gender == "M"
		}
		rawMaleScores.each { rawMaleScore ->
			def maleScore = new AthleteOverallScore(athlete: rawMaleScore.athlete)
			// TODO Set the maleScore place, totalPlace, and wodScoreMap
			maleScores.add(maleScore)
		}

		def femaleScores = []
		*/
		// TODO Collect up all the athlete scores

		def maleAthletes = Athlete.findAll() {
			gender == "M"
		}
		def maleScores = []
		maleAthletes.each { athlete ->
			def wsm = [:]
			def totalPlace = 0
			wods.each { wod ->
				def s = 10 // determine the score
				def p = 1 // determine the place
				def athleteScore = new AthleteScore(athlete: athletes[0], score: s, place: p)
				wsm1.put(wod, athleteScore)
				totalPlace += p
			}
			def maleScore = new AthleteOverallScore(athlete: athletes[0], place: 1, totalPlace: totalPlace, wodScoreMap: wsm)
			maleScores.add(maleScore)
		}
		// TODO Sort the maleScores by totalPlace, lowest to highest
		// TODO Set the place based upon the current order

		def femaleScores = []

		render(view: "board3", model: [wods: wods, maleScores: maleScores, femaleScores: femaleScores])
	}

	/**
	 * Get a score Map for the given WOD with the given gender ("M" or "F").
	 *
	 * @param wod The Workout to create the map for.
	 * @param gender The gender of the athletes (Male = "M", Female = "F")
	 * @return Map<Score, Integer>
	 */
	private getScoreMap(wod, gender) {
		def scores = Score.findAll(sort: "reps", order: "desc") {
			wod == wod && athlete.gender == gender
		}
		return createScoreMap(scores)
	}

	/**
	 * Create a mapping from a Score to a place (integer). This will
	 * deal with ties.
	 *
	 * @param scores List of Score
	 * @return Map<Score, Integer>
	 */
	private createScoreMap(scores) {
		def scoreMap = [:]

		int place = 0
		int lastScore = -1
		scores.each { score ->
			if(score.reps != lastScore) place++
			scoreMap.put(score, place)
			lastScore = score.reps
		}

		return scoreMap
	}
}