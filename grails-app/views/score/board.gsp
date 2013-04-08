<%@ page import="com.wallentine.box.score.Score" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Leaderboard</title>
	</head>
	<body>
		<div id="list-score" class="content scaffold-list" role="main">
			<h1>WOD: ${wod.name}</h1>
			<h2>Male Leaderboard</h2>
			<table>
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th>Name</th>
						<th>Reps</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${maleScoreMap}" status="i" var="entry">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td>${entry.value}</td>
							<td>${entry.key.athlete.name}</td>
							<td>${entry.key.reps}</td>
						</tr>
					</g:each>
				</tbody>
			</table>

			<h2>Female Leaderboard</h2>
			<table>
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th>Name</th>
						<th>Reps</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${femaleScoreMap}" status="i" var="entry">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td>${entry.value}</td>
							<td>${entry.key.athlete.name}</td>
							<td>${entry.key.reps}</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>