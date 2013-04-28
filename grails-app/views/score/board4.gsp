<%@ page import="com.wallentine.box.score.Score" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Overall Team Leaderboard</title>
	</head>
	<body>
		<div id="list-score" class="content scaffold-list" role="main">
			<h1>Overall Leaderboard</h1>
			<table>
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th>Team</th>
						<g:each in="${wods}" status="i" var="wod">
							<th>${wod.number}: ${wod.name}</th>
						</g:each>
					</tr>
				</thead>
				<tbody>
					<g:each in="${scores}" status="i" var="score">
						<tr>
							<td>${score.place} (${score.totalPlace})</td>
							<td>${score.team.name}</td>
							<g:each in="${wods}" status="j" var="wod">
								<g:set var="currentScore" value="${score.wodScoreMap.get(wod)}" />
								<td>${currentScore.place} (${currentScore.score})</td>
							</g:each>
						</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>