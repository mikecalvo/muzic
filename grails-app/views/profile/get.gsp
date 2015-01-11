<!DOCTYPE html>
<html>
<head>
  <title>Profile Detail Page</title>
  <meta name="layout" content="main">
</head>
<body>

<g:if test="${flash.info}">
  <div class="message">
    ${flash.info}
  </div>
</g:if>

<g:if test="${flash.error}">
  <div class="errors">
    ${flash.error}
    <ul>
      <g:each in="${flash.errors}">
        <li><g:message error="${it}"/></li>
      </g:each>
    </ul>
  </div>
</g:if>

<div class="page-content">
  <g:form action="update" method="POST" id="${profile.user.username}">
    <div>
      <label for="username">User Name:</label>
      <span id="username">${profile.user.username}</span>
    </div>

    <div>
      <label for="email">Email:</label>
      <g:textField id="email" name="email" value="${profile.email}" />
    </div>


    <div>
      <label for="date-created">Date Created:</label>
      <g:formatDate id="date-created" date="${profile.dateCreated}" />
    </div>

    <g:submitButton id="save-btn" name="submit">Save</g:submitButton>
  </g:form>
</div>

</body>
</html>