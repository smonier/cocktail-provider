<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="ui" uri="http://www.jahia.org/tags/uiComponentsLib" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="query" uri="http://www.jahia.org/tags/queryLib" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="s" uri="http://www.jahia.org/tags/search" %>
<%@ taglib prefix="cdb" uri="http://www.jahia.org/tags/concktaildb" %>

<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>
<template:addResources type="css" resources="cocktail.css"/>
<c:set var="cocktail" value="${cdb:getCocktailById(currentResource.moduleParams.cocktail)}"/>
<div class="blog mb-5 col-md-4" id="${cocktail.getId()}">
    <div class="blog-wrapper">
        <div class="img-blog ">
            <c:set var="imageURL" value="${cocktail.getCocktailThumb()}"/>

            <c:if test="${not empty imageURL and imageURL ne 'null'}">
                <img alt="${cocktail.getName()}"
                     src="${cocktail.getCocktailThumb()}">
            </c:if>
        </div>
        <div class="content-blog">
            <h2 class="blog-title">${cocktail.getName()}</h2>
            <p>
                <c:forEach items="${cocktail.getCategories()}" var="categories" varStatus="status">
                    <c:if test="${categories  ne 'null'}">
                        <i class="fas fa-glass-cheers"></i> ${categories} ${status.last ? '' : ' | '}
                    </c:if>
                </c:forEach>
            </p>
            <h5>Ingredients</h5>
            <div style="display: table;width:100%">
                <c:forEach items="${cocktail.getIngredients()}" var="ingredients" varStatus="status">
                    <div style="display: table-row;">
                        <c:forEach var="ingredient" items="${ingredients}" varStatus="status">
                            <c:if test="${ingredient  ne 'null'}">
                                <c:set var="rand">
                                    <%= java.lang.Math.round(java.lang.Math.random() * 1000) %>
                                </c:set>
                                <c:set var="modalId" value="${fn:replace(ingredient,' ', '')}-${rand}"/>
                                <div style="display: table-cell;"><a href="#" data-toggle="modal"
                                                                     data-target="#${modalId}">${ingredient}</a>
                                </div>
                                <c:if test="${status.first}">
                                    <c:set var="ingredientByName" value="${cdb:getIngredientByName(ingredient)}"/>
                                    <c:forEach items="${ingredientByName}" var="ingredientDetail"
                                               varStatus="status">

                                        <div class="modal fade" id="${modalId}" tabindex="-1" role="dialog"
                                             aria-labelledby="${modalId}ModalCenterTitle" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title"
                                                            id="${modalId}ModalLongTitle">${ingredientDetail.getName()}</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <c:if test="${ingredientDetail.getType()  ne 'null'}">
                                                            <p>Type: <i>${ingredientDetail.getType()}</i></p>
                                                        </c:if>
                                                        <c:set var="imageURL"
                                                               value="${ingredientDetail.getIngredientThumb()}"/>

                                                        <c:if test="${not empty imageURL and imageURL ne 'null'}">
                                                            <p class=" text-center">
                                                            <img class="img-fluid"
                                                                 alt="${ingredientDetail.getName()}"
                                                                 src="${ingredientDetail.getIngredientThumb()}">
                                                            </p>
                                                        </c:if>
                                                        <c:if test="${ingredientDetail.getDescription()  ne 'null'}">
                                                            <p>${ingredientDetail.getDescription()}</p>
                                                        </c:if>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal">Close
                                                        </button>
                                                        <a href="${url.base}${renderContext.mainResource.node.path}.html?ingredient=${ingredientDetail.getName()}"
                                                           class="btn btn-primary"
                                                           role="button">Cocktails
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>

            <h5 class="mt-3">Instructions</h5>
            <div>
                <p class="description-blog">${cocktail.getInstructions()}</p>

                <p>
                    <c:if test="${cocktail.getCocktailGlass()  ne 'null'}">
                        <i class="fas fa-glass-martini-alt"></i> ${cocktail.getCocktailGlass()}
                    </c:if>
                </p>
                <p>
                    <c:forEach items="${cocktail.getTags()}" var="tags" varStatus="status">
                        <c:if test="${tags  ne 'null'}">
                            ${status.first ? ' <i class="fas fa-tag"></i> ' : ''} ${tags} ${status.last ? '' : ' <i class="fas fa-tag"></i> '}
                        </c:if>

                    </c:forEach>
                </p>
                <div class="text-right">${cocktail.getDateModified()}</div>
            </div>
        </div>
    </div>
</div>
