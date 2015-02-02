<%@ page import="muzic.Song" %>



<div class="fieldcontain ${hasErrors(bean: songInstance, field: 'artist', 'error')} required">
	<label for="artist">
		<g:message code="song.artist.label" default="Artist" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="artist" name="artist.id" from="${muzic.Artist.list()}" optionKey="id" required="" value="${songInstance?.artist?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: songInstance, field: 'lyrics', 'error')} required">
	<label for="lyrics">
		<g:message code="song.lyrics.label" default="Lyrics" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lyrics" required="" value="${songInstance?.lyrics}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: songInstance, field: 'releaseYear', 'error')} required">
	<label for="releaseYear">
		<g:message code="song.releaseYear.label" default="Release Year" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="releaseYear" type="number" value="${songInstance.releaseYear}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: songInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="song.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${songInstance?.title}"/>

</div>

