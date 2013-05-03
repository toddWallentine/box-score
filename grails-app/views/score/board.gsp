<%@ page import="com.wallentine.box.score.Score" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="box">
		<r:require modules="bootstrap"/>
	</head>
	<body>
		<div class="row-fluid">
		<div class="span6">
			<h4>Men (WOD ${wod.number}: ${wod.name})</h4>
			<table class="table table-striped table-condensed">
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
			<h4>Women (WOD ${wod.number}: ${wod.name})</h4>
			<table class="table table-striped table-condensed">
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