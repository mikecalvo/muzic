<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html>
<html>
<head>
  <asset:stylesheet href="application.css"/>

  <script>
    <% def user = SecurityContextHolder.getContext().getAuthentication().getPrincipal() %>

    var loggedInUser = {
      username: '<%=  user.username %>',
      id: <%= user.id %>
    };
  </script>

  <asset:javascript src="application.js"/>
</head>

<body ng-app="app">

<div id="menu">
  <a href="#/plays" class="btn-default">Song Plays</a>
  <a href="#/artists" class="btn-link">Artists</a>
</div>

<ng-view></ng-view>

</body>
</html>
