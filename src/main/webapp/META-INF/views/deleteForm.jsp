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

                <p><b>Delete panel</b> </p>


                <form method="post" action="/delete/currency">
                    <fieldset>
                        <p>Delete all rates from currency (table A i C)
                        <select  class="currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                            <c:forEach items="${lists}" var="list">
                                <option value=${list}>${list}</option>
                            </c:forEach>
                        </select>
                            <input type="submit" value="Delete"/>
                    </fieldset></form>

                <form method="post" action="/delete/period">
                    <fieldset height = "200px">
                        <p>Delete all for period
                            <input name="startDate" type="date" placeholder="Start period" min="2005-01-01" max="2018-12-31"/>
                            <input name="endDate" type="date" placeholder="End period" min="2005-01-01" max="2018-12-31"/>
                            <input type="submit" value="Delete"/></p>
                    </fieldset></form>
            </div>

        </div>

        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>

    </div>



</div>



<%@include file="base/footer.jsp"%>