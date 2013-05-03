import com.wallentine.box.score.*
import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->

        switch(GrailsUtil.environment) {
            case "development":
            case "test":

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
                break;
        }
    }

    def destroy = {
    }
}