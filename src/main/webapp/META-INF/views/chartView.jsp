<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file="base/header.jsp"%>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

    google.charts.load('current', {packages: ['corechart', 'bar']});
    google.charts.setOnLoadCallback(drawBasic);

    function drawBasic()
    {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Date');
        data.addColumn('number', 'Ask');
        data.addColumn('number', 'Bid');

        <c:forEach items="${rates}" var="rate">
        data.addRow(['${rate.effectiveDate}',${rate.ask},${rate.bid}]);
        </c:forEach>

        var options = {
            title: '${currency} exchange rates - 30 last days ',
            hAxis: {
                title: 'Date'
            },
            vAxis: {
                title: 'Exchange rate'
            }
        };

        var chart = new google.visualization.LineChart(
            document.getElementById('curve_chart'));

        chart.draw(data, options);
    }
</script>

<div class="container-fluid">

    <!-- Breadcrumbs-->

    <ol class="breadcrumb">

        <li class="breadcrumb-item">

            <a href="/chart">Chart</a>

        </li>

        <li class="breadcrumb-item active">List</li>

    </ol>

    <div class="card mb-3">

        <div class="card-header">

            <i class="fa fa-table"></i> Chart view </div>

        <div class="card-body">

            <div class="table-responsive">
                <form method="post"><p><select class="currencyForm" name="currencyCode" type="text" placeholder="Currency code">
                <c:forEach items="${lists}" var="list">
                    <option value=${list}>${list}</option>
                </c:forEach>
            </select>
                    <input type="submit" value="Change">
                </form>
                <div id="curve_chart" style="width: 900px; height: 500px"></div>




            </div>

        </div>

        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>

    </div>



</div>



<%@include file="base/footer.jsp"%>