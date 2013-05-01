import com.wallentine.box.score.*
import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->

        switch(GrailsUtil.environment) {
            case "development":
            case "test":
            case "production": // comment out when going live so we don't bootstrap in production.

                if(Workout.count() <= 0) {
                    // bootstrap some test data if none exists.
                    new Workout(number: 1, name: "Killer").save()
                    new Workout(number: 2, name: "Tame").save()
                    new Workout(number: 3, name: "Long").save()
                }

                if(Athlete.count() <= 0) {
                    // bootstrap some test data if none exists.
                    def MAX_TEAMS = 6
                    def MAX_ATHLETES = 10
                    for(int i = 0; i < MAX_TEAMS; i++) {
                        def team = new Team(name: "Team ${i}")
                        for(int j = 0; j < MAX_ATHLETES; j++) {
                            def gender = (j % 2 == 0) ? "M" : "F"
                            def athlete = new Athlete(name: "Athlete ${i}${j}", gender: gender)
                            team.addToAthletes(athlete)
                        }
                        team.save()
                    }

                    def random = new java.util.Random()
                    Athlete.list().each { athlete ->
                        Workout.list().each { wod ->
                            def reps = Math.abs(random.nextInt() % 20 + 1)
                            def score = new Score(reps: reps, athlete: athlete, wod: wod)
                            score.save()
                        }
                    }
                }

                /*
            	def mpTeam = new Team(name: "Maximum Performance")
            	def todd = new Athlete(name: "Todd Wallentine", gender: "M")
            	mpTeam.addToAthletes(todd)
            	def aaron = new Athlete(name: "Aaron Akin", gender: "M")
            	mpTeam.addToAthletes(aaron)
            	def brian = new Athlete(name: "Brian Bittle", gender: "M")
            	mpTeam.addToAthletes(brian)
            	def jax = new Athlete(name: "Jackie Battaglia", gender: "F")
            	mpTeam.addToAthletes(jax)
            	def whit = new Athlete(name: "Whitney Lukenbill", gender: "F")
            	mpTeam.addToAthletes(whit)
            	def rachell = new Athlete(name: "Rachell Bittle", gender: "F")
            	mpTeam.addToAthletes(rachell)
            	mpTeam.save()
                */

                /*
            	def cfmkTeam = new Team(name: "CrossFit Manhattan KS")
            	def megan = new Athlete(name: "Megan John", gender: "F")
            	cfmkTeam.addToAthletes(megan)
            	def shelly = new Athlete(name: "Shelly Weinrich", gender: "F")
            	cfmkTeam.addToAthletes(shelly)
            	def leighann = new Athlete(name: "Leighann Wilkins", gender: "F")
            	cfmkTeam.addToAthletes(leighann)
            	def kevin = new Athlete(name: "Kevin Baribeau", gender: "M")
            	cfmkTeam.addToAthletes(kevin)
            	def matt = new Athlete(name: "Matt Derfler", gender: "M")
            	cfmkTeam.addToAthletes(matt)
            	def alex = new Athlete(name: "Alex Werner", gender: "M")
            	cfmkTeam.addToAthletes(alex)
            	cfmkTeam.save()
                */
                break;
        }
    }

    def destroy = {
    }
}