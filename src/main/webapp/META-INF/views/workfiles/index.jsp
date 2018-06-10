<%--
  Created by IntelliJ IDEA.
  User: marcin
  Date: 30.05.18
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Administrator panel - update</title>

</head>
<body>


<p><b>Administration panel</b> </p>


<form method="post" action="/rateA/save">
    <fieldset>
    <p>Table A archiwal rates (2005-2017)
    <input name="start" type="number" placeholder="Start period" min="2005" max="2018"/>
    <input name="end" type="number" placeholder="End period" min="2005" max="2018"/>
    <input type="submit" value="Update"/></p>
    </fieldset></form>

<form method="post" action="/rateC/save">
    <fieldset height = "200px">
        <p>Table C archiwal rates (2005-2017)
            <input name="start" type="number" placeholder="Start period" min="2005" max="2018"/>
            <input name="end" type="number" placeholder="End period" min="2005" max="2018"/>
            <input type="submit" value="Update"/></p>
    </fieldset></form>

<form method="post" action="/rateA/saveall">
    <fieldset height = "200px">
        <p>Table A current update (2018)
            <input type="submit" value="Update"/></p>
    </fieldset></form>

<form method="post" action="/rateC/saveall">
    <fieldset height = "200px">
        <p>Table C current update (2018)
            <input type="submit" value="Search"/></p>
    </fieldset></form>

</body>
</html>
