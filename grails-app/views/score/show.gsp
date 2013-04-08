
<%@ page import="com.wallentine.box.score.Score" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'score.label', default: 'Score')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-score" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-score" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list score">
			
				<g:if test="${scoreInstance?.athlete}">
				<li class="fieldcontain">
					<span id="athlete-label" class="property-label"><g:message code="score.athlete.label" default="Athlete" /></span>
					
						<span class="property-value" aria-labelledby="athlete-label"><g:link controller="athlete" action="show" id="${scoreInstance?.athlete?.id}">${scoreInstance?.athlete?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scoreInstance?.reps}">
				<li class="fieldcontain">
					<span id="reps-label" class="property-label"><g:message code="score.reps.label" default="Reps" /></span>
					
						<span class="property-value" aria-labelledby="reps-label"><g:fieldValue bean="${scoreInstance}" field="reps"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scoreInstance?.wod}">
				<li class="fieldcontain">
					<span id="wod-label" class="property-label"><g:message code="score.wod.label" default="Wod" /></span>
					
						<span class="property-value" aria-labelledby="wod-label"><g:link controller="workout" action="show" id="${scoreInstance?.wod?.id}">${scoreInstance?.wod?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${scoreInstance?.id}" />
					<g:link class="edit" action="edit" id="${scoreInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
