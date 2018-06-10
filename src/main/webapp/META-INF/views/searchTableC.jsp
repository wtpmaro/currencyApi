<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="base/header.jsp"%>



<div class="container-fluid">

    <!-- Breadcrumbs-->

    <ol class="breadcrumb">

        <li class="breadcrumb-item">

            <a href="/show/date">Search form: table C </a>

        </li>

        <li class="breadcrumb-item active">List</li>

    </ol>



    <div class="card mb-3">

        <div class="card-header">

            <i class="fa fa-table"></i> Searcher </div>

        <div class="card-body">

            <div class="table-responsive">

                <p><b>Search</b> </p>


                <form method="post">
                    <fieldset>
                        <p><b>1.Find exchange rates for currency in searched date.(or nearest)</b></p>
                        <p><select class="currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                            <c:forEach items="${lists}" var="list">
                                <option value=${list}>${list}</option>
                            </c:forEach>
                        </select>
                            <input class="fieldForm" name="startDate" type="date" placeholder="Date"/>
                            <input type="submit" value="Search"/></p>
                    </fieldset></form>

                <form method="post" action="/show/period">
                    <fieldset>
                        <p><b>2.Find exchange rates for currency in period.(Currency, start period date, end period date)</b></p>
                        <p><select class="currencyForm" name="currencyCode" type="text" placeholder="Currency code" >
                        <c:forEach items="${lists}" var="list">
                            <option value=${list} class="currencyForm">${list}</option>
                        </c:forEach>
                    </select>
                            <input class="fieldForm" name="startPeriod" type="date" placeholder="Start date"/>
                            <input class="fieldForm" name="endPeriod" type="date" placeholder="End date"/>
                            <input type="submit" value="Search"/></p>
                    </fieldset></form>

                <form method="post" action="/show/askGreater">
                <fieldset>
                    <p><b>3. Find all rates for currency where ask price is greater than searched value.</b></p>
                    <p>
                    <p><select class="currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                        <c:forEach items="${lists}" var="list">
                            <option value=${list}>${list}</option>
                        </c:forEach>
                    </select>
                        <input class="fieldForm" name="askPrice" type="number" step="0.0001" placeholder="Ask price"/>
                        <input type="submit" value="Search"/>
                </fieldset></form></p>

                <form method="post" action="/show/bidGreater">
                    <fieldset>
                        <p><b>4. Find all rates for currency where bid price is greater than searched value.</b></p>
                        <p><select class="currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                        <c:forEach items="${lists}" var="list">
                        <option value=${list}>${list}</option>
                        </c:forEach>
                        </select>
                            <input class="fieldForm" name="bidPrice" type="number" step="0.0001" placeholder="Bid price"/>
                            <input type="submit" value="Search"/>
                    </fieldset></form></p>


                <form method="post" action="/show/periodAskBetween">
                    <fieldset>
                        <p><b>5. Find all rates for currency where ask price is between searched values.</b></p>
                        <p><select class="currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                            <c:forEach items="${lists}" var="list">
                                <option value=${list}>${list}</option>
                            </c:forEach>
                        </select>
                            <input class="fieldForm" name="lowBarrier" type="number" step="0.0001" placeholder="Start ask price"/>
                            <input class="fieldForm" name="highBarrier" type="number" step="0.0001" placeholder="End ask price"/>
                            <input type="submit" value="Search"/>
                    </fieldset></form></p>

                <form method="post" action="/show/periodBidBetween">
                    <fieldset>
                        <p><b>6. Find all rates for currency where bid price is between searched values.</b></p>
                        <p><select class="currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                            <c:forEach items="${lists}" var="list">
                                <option value=${list}>${list}</option>
                            </c:forEach>
                        </select>
                            <input class="fieldForm" name="lowBarrier" type="number" step="0.0001" placeholder="Start bid price"/>
                            <input class="fieldForm" name="highBarrier" type="number" step="0.0001" placeholder="End bid price"/>
                            <input type="submit" value="Search"/>
                    </fieldset></form></p>

                <form method="post" action="/show/askMaxPerPeriod">
                    <fieldset>
                        <p><b>7. Find max ask rate for currency in a period.</b></p>
                        <p><select class="currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                            <c:forEach items="${lists}" var="list">
                                <option value=${list}>${list}</option>
                            </c:forEach>
                        </select>
                            <input class="fieldForm" name="startDate" type="date" placeholder="Start date"/>
                            <input class="fieldForm" name="endDate" type="date" placeholder="End date"/>
                            <input type="submit" value="Search"/>
                    </fieldset></form></p>

                <form method="post" action="/show/bidMaxPerPeriod">
                    <fieldset>
                        <p><b>8. Find max bid rate for currency in a period.</b></p>
                        <p><select class="currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                            <c:forEach items="${lists}" var="list">
                                <option value=${list}>${list}</option>
                            </c:forEach>
                        </select>
                            <input class="fieldForm" name="startDate" type="date" placeholder="Start date"/>
                            <input class="fieldForm" name="endDate" type="date" placeholder="End date"/>
                            <input type="submit" value="Search"/>
                    </fieldset></form></p>

                <form method="post" action="/show/askMinPerPeriod">
                    <fieldset>
                        <p><b>9. Find min ask rate for currency in a period.</b></p>
                        <p><select class="currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                            <c:forEach items="${lists}" var="list">
                                <option value=${list}>${list}</option>
                            </c:forEach>
                        </select>
                            <input class="fieldForm" name="startDate" type="date" placeholder="Start date"/>
                            <input class="fieldForm" name="endDate" type="date" placeholder="End date"/>
                            <input type="submit" value="Search"/>
                    </fieldset></form></p>

                <form method="post" action="/show/bidMinPerPeriod">
                    <fieldset>
                        <p><b>10. Find min bid rate for currency in a period.</b></p>
                        <p><select class="currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                            <c:forEach items="${lists}" var="list">
                                <option value=${list}>${list}</option>
                            </c:forEach>
                        </select>
                            <input class="fieldForm" name="startDate" type="date" placeholder="Start date"/>
                            <input class="fieldForm" name="endDate" type="date" placeholder="End date"/>
                            <input type="submit" value="Search"/>
                    </fieldset></form></p>

            </div>

        </div>

        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>

    </div>



</div>



<%@include file="base/footer.jsp"%>