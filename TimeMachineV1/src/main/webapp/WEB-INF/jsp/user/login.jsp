<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Time Machine</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/vendor/jquery-2.0.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/vendor/bootstrap.js"></script>
    
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/social-buttons-3.css" />
</head>
<body>
<div class="page">
    <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-brand">Time Machine</span>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav navbar-left">
                <sec:authorize access="isAuthenticated()">
                    <li><a href="${pageContext.request.contextPath}">Home</a></li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()">
                    <li class ="signin_li"><a class="btn btn-primary signin-a" href="${pageContext.request.contextPath}/login">Login <span class="glyphicon glyphicon-log-in" aria-hidden="true"/></a></li>
                	<li class="signup_li" ><a class="btn btn-danger signup-a" href="${pageContext.request.contextPath}/user/register">Sign Up <span class="glyphicon glyphicon-registration-mark" aria-hidden="true"/></a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <form action="${pageContext.request.contextPath}/logout" method="POST">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-primary logout-btn">Logout  <span class="glyphicon glyphicon-log-out" aria-hidden="true"/>
                            </button>
                        </form>
                    </li>
                </sec:authorize>
            </ul>
            <sec:authorize access="isAuthenticated()">
                <p class="nav navbar-nav navbar-right navbar-text sign-in-text">
                    <sec:authentication property="principal.socialSignInProvider" var="signInProvider"/>
                    <c:if test="${signInProvider == 'FACEBOOK'}">
                        <i class="icon-facebook-sign"></i>
                    </c:if>
                    <c:if test="${signInProvider == 'TWITTER'}">
                        <i class="icon-twitter-sign"></i>
                    </c:if>
                    <c:if test="${empty signInProvider}">
                        Signed in as
                    </c:if>
                    <sec:authentication property="principal.username"/>
                </p>
            </sec:authorize>
        </div><!-- /.navbar-collapse -->
    </div>
    

	<div class="page-header">
		<h1>
			Login
		</h1>
	</div>
	<sec:authorize access="isAnonymous()">
		<div class="panel panel-default">
			<div class="panel-body">
				<h2>
					Login User Account
				</h2>
				<c:if test="${param.error eq 'bad_credentials'}">
					<div class="alert alert-danger alert-dismissable">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						Login failed! Invalid email or password!
					</div>
				</c:if>
				<form action="${pageContext.request.contextPath}/login"
					modelAttribute="user" method="POST" role="form">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="row">
						<div id="form-group-email" class="form-group col-lg-4">
							<label class="control-label" for="user-email">Email: *</label> 
							<form:input id="user-email" path="email" class="form-control" />
						</div>
					</div>

					<div class="row">
						<div id="form-group-password" class="form-group col-lg-4">
							<label class="control-label" for="user-password">Password: *</label> <input id="user-password"
								name="password" type="password" class="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="form-group col-lg-4">
							<button type="submit"
								class="btn btn-lg segoe-ui-light ladda-button btn-primary btn-block">
								Login
							</button>
						</div>
					</div>
				</form>
				<div class="row">
					<div class="form-group col-lg-4 loc">
						<a href="${pageContext.request.contextPath}/user/register">New User? Sign Up</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<h2>
					Sign in with social provider
				</h2>
				<div class="row social-button-row">
					<div class="col-lg-4">
						<a href="${pageContext.request.contextPath}/auth/facebook"><button
								class="btn btn-facebook">
								<i class="icon-facebook"></i> |
								Sign in with Facebook
							</button></a>
					</div>
				</div>
				<div class="row social-button-row">
					<div class="col-lg-4">
						<a href="${pageContext.request.contextPath}/auth/twitter"><button
								class="btn btn-twitter">
								<i class="icon-twitter"></i> |
								Sign in with Twitter
							</button></a>
					</div>
				</div>
			</div>
		</div>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<p>
			You cannot login because you are already logged in.
		</p>
	</sec:authorize>
	</div>
</body>
</html>