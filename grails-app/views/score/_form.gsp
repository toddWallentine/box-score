<%@ page import="com.wallentine.box.score.Score" %>



<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'athlete', 'error')} required">
	<label for="athlete">
		<g:message code="score.athlete.label" default="Athlete" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="athlete" name="athlete.id" from="${com.wallentine.box.score.Athlete.list()}" optionKey="id" required="" value="${scoreInstance?.athlete?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'reps', 'error')} required">
	<label for="reps">
		<g:message code="score.reps.label" default="Reps" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="reps" type="number" value="${scoreInstance.reps}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'wod', 'error')} required">
	<label for="wod">
		<g:message code="score.wod.label" default="Wod" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="wod" name="wod.id" from="${com.wallentine.box.score.Workout.list()}" optionKey="id" required="" value="${scoreInstance?.wod?.id}" class="many-to-one"/>
</div>

