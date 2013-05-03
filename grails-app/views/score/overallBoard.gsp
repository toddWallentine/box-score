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
			<h4>Men</h4>
			<table class="table table-striped table-condensed">
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
								<g:if test="${currentScore != null}">
									<td>${currentScore.place} (${currentScore.score})</td>
								</g:if>
							</g:each>
						</tr>
					</g:each>
				</tbody>
			</table>
		</div>
		<div class="span6">
			<h4>Women</h4>
			<table class="table table-striped table-condensed">
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
								<g:if test="${currentScore != null}">
									<td>${currentScore.place} (${currentScore.score})</td>
								</g:if>
							</g:each>
						</tr>
					</g:each>
				</tbody>
			</table>
		</div>
		</div>
	</body>
</html>