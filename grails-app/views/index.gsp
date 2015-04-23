<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html>
<html>
<head>
    <asset:stylesheet href="application.css"/>

    <script>
        <% def user = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        if (user?.username) {
        %>

        var loggedInUser = {
            username: '<%=  user?.username %>',
            id: <%= user?.id %>
        };

    <% } %>

    </script>

    <asset:javascript src="application.js"/>
</head>

<body ng-app="app">

<div ng-include="'templates/header.html'"></div>

<ng-view></ng-view>

</body>
</html>
