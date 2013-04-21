<%@ page import="com.wallentine.box.score.Score" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Overall Leaderboard</title>
	</head>
	<body>
		<div id="list-score" class="content scaffold-list" role="main">
			<h1>Male Leaderboard</h1>
			<table>
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th>Name</th>
						<g:each in="${wods}" status="i" var="wod">
							<th>${wod.number}: ${wod.name}</th>
						</g:each>
					</tr>
				</thead>
				<tbody>
					<g:each in="${maleScores}" status="i" var="maleScore">
						<tr>
							<td>${maleScore.place} (${maleScore.totalPlace})</td>
							<td>${maleScore.athlete.name}</td>
							<g:each in="${wods}" status="j" var="wod">
								<g:set var="currentScore" value="${maleScore.wodScoreMap.get(wod)}" />
								<td>${currentScore.place} (${currentScore.score})</td>
							</g:each>
						</tr>
					</g:each>
				</tbody>
			</table>

			<h1>Female Leaderboard</h1>
			<table>
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th>Name</th>
						<g:each in="${wods}" status="i" var="wod">
							<th>${wod.number}: ${wod.name}</th>
						</g:each>
					</tr>
				</thead>
				<tbody>
					<g:each in="${femaleScores}" status="i" var="femaleScore">
						<tr>
							<td>${femaleScore.place} (${femaleScore.totalPlace})</td>
							<td>${femaleScore.athlete.name}</td>
							<g:each in="${wods}" status="j" var="wod">
								<g:set var="currentScore" value="${femaleScore.wodScoreMap.get(wod)}" />
								<td>${currentScore.place} (${currentScore.score})</td>
							</g:each>
						</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>