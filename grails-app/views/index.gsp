<%@ page import="com.wallentine.box.score.Workout" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="box"/>
	</head>
	<body>
		<div class="page-header">
			<h1>Box Battle 2013 at Maximum Performance</h1>
		</div>
		<div class="row">
			<div class="span12">
				<p>
					Before the Box Battle begins, each team and athlete must be registered. In
					addition, each WOD needs to be created.
				</p>
				<ul>
					<li><g:link controller="team" action="create">Register a Team</g:link></li>
					<li><g:link controller="team" action="list">List/Edit Teams</g:link></li>
					<li><g:link controller="athlete" action="create">Register an Athlete</g:link></li>
					<li><g:link controller="athlete" action="list">List/Edit Athletes</g:link></li>
					<li><g:link controller="workout" action="create">Create a WOD</g:link></li>
					<li><g:link controller="workout" action="list">List/Edit WODs</g:link></li>
				</ul>

				<p>
					During the Box Battle, each athlete will recieve a score for each WOD.
				</p>
				<ul>
					<li><g:link controller="score" action="create">Enter a Score</g:link></li>
					<li><g:link controller="score" action="list">List/Edit a Score</g:link></li>
				</ul>

				<p>
					During the Box Battle, leaderboards can be seen.
				</p>
				<ul>
					<li><g:link controller="score" action="overallBoard">Overall Leaderboard</g:link></li>
					<li><g:link controller="score" action="overallTeamBoard">Overall Team Leaderboard</g:link></li>
					<g:each var="wod" in="${Workout.list().sort { it.number } }">
						<li>WOD ${wod.number}: ${wod.name}
							<ul>
								<li>
									<g:link controller="score" action="board" params='[id: "${wod.number}"]'>
										Individual Leaderboard
									</g:link>
								</li>
								<li>
									<g:link controller="score" action="teamBoard" params='[id: "${wod.number}"]'>
										Team Leaderboard
									</g:link>
								</li>
							</ul>
						</li>
					</g:each>
				</ul>
			</div>
		</div>
	</body>
</html>