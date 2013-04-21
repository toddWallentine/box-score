<%@ page import="com.wallentine.box.score.Score" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Team Leaderboard (WOD ${wod.number}: ${wod.name})</title>
	</head>
	<body>
		<div id="list-score" class="content scaffold-list" role="main">
			<h1>Team Leaderboard (WOD ${wod.number}: ${wod.name})</h1>
			<table>
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th>Name</th>
						<th>Reps</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${teamScores}" status="i" var="teamScore">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td>${teamScore.place}</td>
							<td>${teamScore.team.name}</td>
							<td>${teamScore.score}</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>