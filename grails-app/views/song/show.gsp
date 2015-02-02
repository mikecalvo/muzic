
<%@ page import="muzic.Song" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'song.label', default: 'Song')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-song" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-song" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list song">
			
				<g:if test="${songInstance?.artist}">
				<li class="fieldcontain">
					<span id="artist-label" class="property-label"><g:message code="song.artist.label" default="Artist" /></span>
					
						<span class="property-value" aria-labelledby="artist-label"><g:link controller="artist" action="show" id="${songInstance?.artist?.id}">${songInstance?.artist?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${songInstance?.lyrics}">
				<li class="fieldcontain">
					<span id="lyrics-label" class="property-label"><g:message code="song.lyrics.label" default="Lyrics" /></span>
					
						<span class="property-value" aria-labelledby="lyrics-label"><g:fieldValue bean="${songInstance}" field="lyrics"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${songInstance?.releaseYear}">
				<li class="fieldcontain">
					<span id="releaseYear-label" class="property-label"><g:message code="song.releaseYear.label" default="Release Year" /></span>
					
						<span class="property-value" aria-labelledby="releaseYear-label"><g:fieldValue bean="${songInstance}" field="releaseYear"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${songInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="song.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${songInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:songInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${songInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
