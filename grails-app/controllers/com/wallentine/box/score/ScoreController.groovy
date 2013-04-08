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

		def maleScores = Score.findAll(sort: "reps", order: "desc") {
			wod == wod && athlete.gender == "M"
		}
		def maleScoreMap = createScoreMap(maleScores)

		def femaleScores = Score.findAll(sort: "reps", order: "desc") {
			wod == wod && athlete.gender == "F"
		}
		def femaleScoreMap = createScoreMap(femaleScores)

		render(view: "board", model: [wod: wod,
			maleScoreMap: maleScoreMap,
			femaleScoreMap: femaleScoreMap])	
	}

	/**
	 * Display the team leaderboard for a specific WOD.
	 *
	 * @param id The WOD ID.
	 */
	def board2() {
		def wodID = params.id
		def wod = Workout.get(wodID)

		def teamScoreMap = [:]
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

			teamScoreMap.put(team, teamReps)
		}

		// TODO Sort the teamScoreMap by reps
		// TODO Add placement to teamScoreMap

		render(view: "board2", model: [wod: wod,
			teamScoreMap: teamScoreMap])
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