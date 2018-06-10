<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="base/header.jsp"%>



<div class="container-fluid">

    <!-- Breadcrumbs-->

    <ol class="breadcrumb">

        <li class="breadcrumb-item">

            <a href="/showA/date">Search form: table A</a>

        </li>

        <li class="breadcrumb-item active">List</li>

    </ol>



    <div class="card mb-3">

        <div class="card-header">

            <i class="fa fa-table"></i> Result table</div>

        <div class="card-body">

            <div class="table-responsive">

                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

                    <thead>

                    <tr>

                        <th>Currency Code</th>

                        <th>Effective Date</th>

                        <th>Mid</th>

                        <th>Table no</th>

                    </tr>

                    </thead>

                    <tfoot>

                    <tr>

                        <th>Currency Code</th>

                        <th>Effective Date</th>

                        <th>Mid</th>

                        <th>Table no</th>

                    </tr>

                    </tfoot>

                    <tbody>



                    <c:forEach items="${rates}" var="rate">

                        <tr>

                            <td class="currencyForm">${rate.currencyCode}</td>

                            <td>${rate.effectiveDate}</td>

                            <td>${rate.mid}</td>

                            <td>${rate.no}

                             <%--   <a href="/books/${rate.id}">show</a> |

                                <a href="/books/${rate.id}/edit">edit</a> |

                                <a href="/books/${book.id}/delete">delete</a> |--%>

                            </td>



                        </tr>

                    </c:forEach>


                    </tbody>

                </table>

            </div>

        </div>

        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>

    </div>



</div>



<%@include file="base/footer.jsp"%>