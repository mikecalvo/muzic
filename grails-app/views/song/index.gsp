
<%@ page import="muzic.Song" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'song.label', default: 'Song')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-song" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-song" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="song.artist.label" default="Artist" /></th>
					
						<g:sortableColumn property="lyrics" title="${message(code: 'song.lyrics.label', default: 'Lyrics')}" />
					
						<g:sortableColumn property="releaseYear" title="${message(code: 'song.releaseYear.label', default: 'Release Year')}" />
					
						<g:sortableColumn property="title" title="${message(code: 'song.title.label', default: 'Title')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${mySongList}" status="i" var="songInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${songInstance.id}">${fieldValue(bean: songInstance, field: "artist")}</g:link></td>
					
						<td>${fieldValue(bean: songInstance, field: "lyrics")}</td>
					
						<td>${fieldValue(bean: songInstance, field: "releaseYear")}</td>
					
						<td>${fieldValue(bean: songInstance, field: "title")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${songInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
