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
	def teamBoard() {
		def wodID = params.id
		def wod = Workout.get(wodID)

		// Collect up the best 3 male and best 3 female athletes to create the team score.
		def teamScores = []
		Team.list().each { team ->
			def teamReps = getTeamReps(team, wod)
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
			if(teamScore.score != lastScore) place++
			teamScore.place = place
			lastScore = teamScore.score
		}

		render(view: "teamBoard", model: [wod: wod, teamScores: teamScores])
	}

	/**
	 * Get the number of reps that a team did for a specific WOD.
	 *
	 * @param team The Team to count the reps for.
	 * @param wod The WOD to count the reps for.
	 * @return The total number of reps for the top 3 males and top 3 females
	 *         on the given Team for the given WOD.
	 */
	private int getTeamReps(team, wod) {

		def teamReps = 0

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

		return teamReps
	}

	/**
	 * Display the overall individual leaderboard.
	 */
	def overallBoard() {

		def wods = Workout.list()
		def overallScoreComparator = [
			compare: { a,b ->
				a.equals(b)? 0: a.totalPlace < b.totalPlace? -1: 1
			}
		] as Comparator

		def maleScores = getScores(wods, "M")
		maleScores.sort(true, overallScoreComparator)
		def lastPlace = -1
		def place = 0
		maleScores.each { athleteOverallScore ->
			if(athleteOverallScore.totalPlace != lastPlace) place++
			athleteOverallScore.place = place
			lastPlace = athleteOverallScore.totalPlace
		}

		def femaleScores = getScores(wods, "F")
		femaleScores.sort(true, overallScoreComparator)
		lastPlace = -1
		place = 0
		femaleScores.each { athleteOverallScore ->
			if(athleteOverallScore.totalPlace != lastPlace) place++
			athleteOverallScore.place = place
			lastPlace = athleteOverallScore.totalPlace
		}

		render(view: "overallBoard", model: [wods: wods, maleScores: maleScores, femaleScores: femaleScores])
	}

	/**
	 * Display the overall team leaderboard.
	 */
	def overallTeamBoard() {
		def overallScoreComparator = [
			compare: { a,b ->
				a.equals(b)? 0: a.totalPlace < b.totalPlace? -1: 1
			}
		] as Comparator

		def wods = Workout.list()

		def scores = []
		def teams = Team.list()
		teams.each { team ->
			def wodScoreMap = [:]
			def totalPlace = 0
			wods.each { wod ->
				def teamScores = getTeamScores(wod)
				def teamScore = teamScores.get(team)
				wodScoreMap.put(wod, teamScore)
				totalPlace += teamScore.place
			}
			def teamOverallScore = new TeamOverallScore(team: team,
				place: 0, totalPlace: totalPlace, wodScoreMap: wodScoreMap)
			scores.add(teamOverallScore)
		}
		scores.sort(true, overallScoreComparator)
		def lastPlace = -1
		def place = 0
		scores.each { overallScore ->
			if(overallScore.totalPlace != lastPlace) place++
			overallScore.place = place
			lastPlace = overallScore.totalPlace
		}

		render(view: "overallTeamBoard", model: [wods: wods, scores: scores])
	}

	private getScores(wods, g) {
		def overallScores = []

		def athletes = Athlete.findAll() {
			gender == g
		}
		athletes.each { athlete ->
			def wsm = [:]
			def totalPlace = 0
			wods.each { wod ->
				def score = Score.findByAthleteAndWod(athlete, wod)
				def athleteScore
				if(score) {
					def s = score.reps
					def p = determinePlaceInWod(wod, athlete)
					athleteScore = new AthleteScore(athlete: athlete, score: s, place: p)
				} else {
					def lastPlace = Score.findAll() { wod == wod && athlete.gender == athlete.gender }.size
					athleteScore = new AthleteScore(athlete: athlete, score: 0, place: lastPlace)
				}
				wsm.put(wod, athleteScore)
				totalPlace += athleteScore.place
			}
			def overallScore = new AthleteOverallScore(athlete: athlete, place: 1, totalPlace: totalPlace, wodScoreMap: wsm)
			overallScores.add(overallScore)
		}

		return overallScores
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

	/**
	 * Get a Map of Athlete to AthleteScore for the given WOD and gender.
	 *
	 * @param wod The Workout to get scores for.
	 * @param gender "M" or "F"
	 * @return Map<Athlete, AthleteScore>
	 */
	private getAthleteScores(wod, gender) {
		def athleteScoreMap = [:]

		def scoreMap = getScoreMap(wod, gender)
		scoreMap.each{ score, place ->
			def athleteScore = new AthleteScore(athlete: score.athlete,
				score: score.reps, place: place, wod: score.wod)
			scoreMap.put(score.athlete, athleteScore)
		}

		return athleteScoreMap
	}

	private determinePlaceInWod(wod, athlete) {
		def place = 0

		def athleteScoreMap = getScoreMap(wod, athlete.gender)
    	athleteScoreMap.each { key, value ->
      		if(key.athlete == athlete) {
        		place = value
      		}
    	}

		return place
	}

	private getTeamScores(wod) {
		def teamScores = []
		Team.list().each { team ->
			def teamReps = getTeamReps(team, wod)
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
			if(teamScore.score != lastScore) place++
			teamScore.place = place
			lastScore = teamScore.score
		}

		def teamScoreMap = [:]
		teamScores.each { teamScore ->
			teamScoreMap.put(teamScore.team, teamScore)
		}
		return teamScoreMap
	}
}