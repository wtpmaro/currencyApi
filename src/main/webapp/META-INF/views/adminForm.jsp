<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="base/header.jsp"%>



<div class="container-fluid">

    <!-- Breadcrumbs-->

    <ol class="breadcrumb">

        <li class="breadcrumb-item">

            <a href="/rateA/form">Update</a>

        </li>

        <li class="breadcrumb-item">

            <a href="/delete/form">Delete</a>

        </li>

        <li class="breadcrumb-item active">List</li>

    </ol>



    <div class="card mb-3">

        <div class="card-header">

            <i class="fa fa-table"></i> Admin tools </div>

        <div class="card-body">

            <div class="table-responsive">

                <p><b>Update panel</b> </p>


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
                            <input type="submit" value="Update"/></p>
                    </fieldset></form>

            </div>

        </div>

        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>

    </div>



</div>



<%@include file="base/footer.jsp"%>