<%@ page import="com.wallentine.box.score.Score" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="box">
		<r:require modules="bootstrap"/>
	</head>
	<body>
		<div class="page-header">
			<h1>Team Leaderboard (WOD ${wod.number}: ${wod.name})</h1>
		</div>
		<div class="row-fluid">
		<div class="span12">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th>Team Name</th>
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
		</div>
	</body>
</html>