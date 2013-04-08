import com.wallentine.box.score.*

class BootStrap {

    def init = { servletContext ->

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
    	// TODO Add more athletes to MP team

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
    	// TODO Add more athletes to CFMK team

    	// TODO Add more teams: CF785, KSUCF, ...

    	new Workout(number: 1, name: "Killer").save()
    	new Workout(number: 2, name: "Tame").save()
    	new Workout(number: 3, name: "Long").save()

    	def random = new java.util.Random()
    	Athlete.list().each { athlete ->
    		Workout.list().each { wod ->
    			def reps = Math.abs(random.nextInt() % 20 + 1)
    			def score = new Score(reps: reps, athlete: athlete, wod: wod)
    			score.save()
    		}
    	}
    }

    def destroy = {
    }
}
