<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="base/header.jsp"%>



<div class="container-fluid">

    <!-- Breadcrumbs-->

    <ol class="breadcrumb">

        <li class="breadcrumb-item">

            <a href="/calculator/form">Calculator</a>

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

                        <th>Effective date</th>

                        <th>Base currency</th>

                        <th> Base amount</th>

                        <th class="currencyForm">${rates.currencyCode}/pln</th>
                        <th> Traded currency</th>
                        <th> Traded amount</th>


                    </tr>

                    </thead>

                    <tfoot>

                    <tr>

                        <th>Effective date</th>

                        <th>Base currency</th>

                        <th> Base amount</th>

                        <th class="currencyForm">${rates.currencyCode}/pln</th>
                        <th> Traded currency</th>
                        <th> Traded amount</th>

                    </tr>

                    </tfoot>

                    <tbody>


                        <tr>

                            <td>${rates.effectiveDate}</td>
                            <td class="currencyForm">${rates.currencyCode}</td>
                            <td>${amountToconversion}</td>
                            <td>${exchangeRate}</td>
                            <td class="currencyForm">pln</td>
                            <td>${calculation}</td>

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
