box-score
=========

Scoring system for Box Battle

Assume: All scores are max reps (AMRAP)
Assume: All Atheletes are part of a team
Assume: Team score is based upon top 3 female and top 3 male.


Use Case 1: Create a team
Input: Name


Use Case 2: Add an Athlete to a team
Input: Name, Gender, Team


Use Case 3: Enter a score for an Athlete
Input: Name, Score, WOD
Select Name and WOD if possible. Otherwise, return errors when entering invalid data.


Use Case 4: View Individual Leaderboard for a WOD
Input: WOD
For a given WOD, display individuals in order of score (reps) from highest to lowest.


Use Case 5: View Team Leaderboard for a WOD
Input: WOD
For a given WOD, display teams in order of score (reps) from highest to lowest.


Use Case 6: View Individual Leaderboard Overall
Using WOD place, show all athetes scores from lowest to highest.


Use Case 7: View Team Leaderboard Overall
Using WOD place, show all team scores from lowest to highest.