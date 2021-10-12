<%@ page import="java.util.*" %>
<html>
    <head>
        <link rel="stylesheet" href="style.css">
        <title>Newsletter - pw1</title>
    </head>
    <body>
    <div class="main">
        <h1>PW1 - NEWSLETTER</h1>
        <%
            String nome = (String) request.getParameter("nome");
            out.print( "<p>Oi "+nome+", verifique seu email.</p>");

        %>
    </div>
    </body>
</html>
