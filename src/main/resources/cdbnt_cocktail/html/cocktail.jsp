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
<jcr:nodeProperty node="${currentNode}" var="cocktailName" name="strDrink"/>

<div class="container">
    <div class="row justify-content-center w-100 mb-3">
        <h2 id="cocktailpageId"></h2>
    </div>
    <div class="row">
        <div class="col-xs-12 w-100">
            <div class="blog-refinement-bar">
                <div class="folder-refinement-container">
                    <div class="blog-refinement">
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'A'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=A">A</a>
                        </div>
                        <div class="refinement-item">

                            <a class="refinement-link ${selectedLetter == 'B'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=B">B</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'C'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=C">C</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'D'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=D">D</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'E'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=E">E</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'F'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=F">F</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'G'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=G">G</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'H'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=H">H</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'I'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=I">I</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'J'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=J">J</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'K'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=K">K</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'L'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=L">L</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'M'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=M">M</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'N'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=N">N</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'O'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=O">O</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'P'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=P">P</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'Q'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=Q">Q</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'R'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=R">R</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'S'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=S">S</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'T'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=T">T</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'U'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=U">U</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'V'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=V">V</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'W'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=W">W</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'X'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=X">X</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'Y'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=Y">Y</a>
                        </div>
                        <div class="refinement-item">
                            <a class="refinement-link ${selectedLetter == 'Z'? ' active' : ''}"
                               href="${url.base}${renderContext.mainResource.node.path}.html?letter=Z">Z</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row blog-refinement-bar mb-5 w-100">
    <form class="form-inline justify-content-center w-100" action="${url.base}${renderContext.mainResource.node.path}.html" method="get">
        <input type="text" class="form-control mb-2 mr-sm-2" id="cocktailName" name="cocktailName" placeholder="Cocktail Name">
        <button type="submit" class="btn btn-primary mb-2">Search</button>
    </form>
</div>
<c:choose>
    <c:when test="${empty param}">
        <c:set var="selectedLetter" value='${cdb:getRandomLetter(1)}'/>
        <c:set var="cocktailList" value="${cdb:getCocktailByLetter(selectedLetter)}"/>
        <script>document.getElementById("cocktailpageId").innerHTML = "Cocktails starting with ${selectedLetter}";</script>
    </c:when>
    <c:when test="${not empty param.letter}">
        <c:set var="selectedLetter" value='${param.letter}'/>
        <c:set var="cocktailList" value="${cdb:getCocktailByLetter(selectedLetter)}"/>
        <script>document.getElementById("cocktailpageId").innerHTML = "Cocktails starting with ${selectedLetter}";</script>
    </c:when>
    <c:when test="${not empty param.cocktailName}">
        <c:set var="cocktailName" value='${param.cocktailName}'/>
        <c:set var="cocktailList" value="${cdb:getCocktailByName(cocktailName)}"/>
        <script>document.getElementById("cocktailpageId").innerHTML = "Cocktails named ${cocktailName}";</script>
    </c:when>
    <c:when test="${not empty param.ingredient}">
        <c:set var="cocktailIngredient" value='${param.ingredient}'/>
        <c:set var="cocktailList" value="${cdb:getCocktailByIngredient(cocktailIngredient)}"/>
        <script>document.getElementById("cocktailpageId").innerHTML = "Cocktails made of ${cocktailIngredient}";</script>
    </c:when>
</c:choose>

<div class="row">
    <c:forEach items="${cocktailList}" var="cocktail" varStatus="status">
        <template:include view="cocktailCard">
            <template:param name="cocktail" value="${cocktail.id}"/>
        </template:include>
    </c:forEach>
</div>


