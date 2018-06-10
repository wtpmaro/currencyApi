<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="base/header.jsp"%>



<div class="container-fluid">

    <!-- Breadcrumbs-->

    <ol class="breadcrumb">

        <li class="breadcrumb-item">

            <a href="/show/date">Search form: table C</a>

        </li>

        <li class="breadcrumb-item active">List</li>

    </ol>



    <div class="card mb-3">

        <div class="card-header">

            <i class="fa fa-table"></i> Table: ${rates.no} from ${rates.effectiveDate}</div>

        <div class="card-body">

            <div class="table-responsive">

                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

                    <thead>

                    <tr>

                        <th>Currency Code</th>

                        <th>Effective Date</th>

                        <th>Bid</th>

                        <th>Ask</th>

                        <th>Table no</th>

                    </tr>

                    </thead>

                    <tfoot>

                    <tr>

                        <th>Currency Code</th>

                        <th>Effective Date</th>

                        <th>Bid</th>

                        <th>Ask</th>

                        <th>Table no</th>

                    </tr>

                    </tfoot>

                    <tbody>


                        <tr>

                            <td class="currencyForm">${rates.currencyCode}</td>

                            <td>${rates.effectiveDate}</td>

                            <td>${rates.bid}</td>

                            <td>${rates.ask}</td>

                            <td>${rates.no}

                            </td>
                        </tr>
                    </tbody>

                </table>

            </div>

        </div>

        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>

    </div>



</div>



<%@include file="base/footer.jsp"%>
