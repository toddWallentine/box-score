<%@ page import="com.wallentine.box.score.Score" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="box">
		<r:require modules="bootstrap"/>
	</head>
	<body>
		<div class="page-header">
			<h1>Leaderboard (WOD ${wod.number}: ${wod.name})</h1>
		</div>
		<div class="row-fluid">
		<div class="span6">
			<h1>Men</h1>
			<table class="table table-striped">
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
		</div>

		<div class="span6">
			<h1>Women</h1>
			<table class="table table-striped">
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
		</div>
	</body>
</html>