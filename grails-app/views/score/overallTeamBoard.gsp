<%@ page import="com.wallentine.box.score.Score" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="box">
		<r:require modules="bootstrap"/>
	</head>
	<body>
		<div class="page-header">
			<h1>Overall Team Leaderboard</h1>
		</div>
		<div class="row">
		<div class="span12">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Place</th>
						<th>Team Name</th>
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
		</div>
	</body>
</html>