<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="base/header.jsp"%>



<div class="container-fluid">

    <!-- Breadcrumbs-->

    <ol class="breadcrumb">

        <li class="breadcrumb-item">

            <a href="/calculator/form">Calculator form</a>

        </li>

        <li class="breadcrumb-item active">List</li>

    </ol>



    <div class="card mb-3">

        <div class="card-header">

            <i class="fa fa-table"></i> Currencies converter</div>

        <div class="card-body">

            <div class="table-responsive">

                <p><b>Search</b> </p>


                <form method="post" action="/calculator/buyForeign">
                    <fieldset>
                        <p><b> Exchange PLN amount to foreign Currency</b></p>
                        <p>Currency Code: <select class="form-control foo currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                            <c:forEach items="${lists}" var="list">
                                <option value=${list}>${list}</option>
                            </c:forEach>
                        </select></p>
                        <p>Value Date: <input class="form-control foo" name="startDate" type="date" placeholder="Date"/></p>
                        <p>Amount in PLN:   <input class="form-control foo" name="amountToConversion" type="number" step="0.01" placeholder=""/></p>
                        <p>Price type: <select class="form-control foo" name="priceType" type="text">
                                <option value="ask">Ask</option>
                            <option value="mid">Mid</option></p>
                            </select>
                        <p><input class="btn btn-default" type="submit" value="Calculate"/></p>
                    </fieldset></form>

                <form method="post" action="/calculator/buyPln">

                    <fieldset>
                        <p><b> Exchange foreign amount to polish currency </b></p>
                        <p>Currency Code: <select class="form-control foo currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                            <c:forEach items="${lists}" var="list">
                                <option value=${list}>${list}</option>
                            </c:forEach>
                        </select></p>
                        <p>Value Date: <input class="form-control foo" name="startDate" type="date" placeholder="Date"/></p>
                        <p>Amount in foreign currency:   <input class="form-control foo" name="amountToConversion" type="number" step="0.01" placeholder=""/></p>
                        <p>Price type: <select class="form-control foo" name="priceType" type="text">
                            <option value="bid">Bid</option>
                            <option value="mid">Mid</option></p>
                        </select>
                        <p><input  class="btn btn-default" type="submit" value="Calculate"/></p>
                    </fieldset></form>

            </div>

        </div>

        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>

    </div>



</div>



<%@include file="base/footer.jsp"%>