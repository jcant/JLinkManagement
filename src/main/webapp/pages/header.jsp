<nav class="navbar navbar-expand-lg navbar-light">
	<span class="sitename">JLink Management</span>

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

 <c:if test="${path eq ''}"><c:set var = "home" value = "active"/></c:if>
 <c:if test="${path eq 'profile'}"><c:set var = "profile" value = "active"/></c:if>
 <c:if test="${path eq 'freelinks'}"><c:set var = "freelinks" value = "active"/></c:if>
 <c:if test="${path eq 'links'}"><c:set var = "links" value = "active"/></c:if>
 <c:if test="${path eq 'stats'}"><c:set var = "stats" value = "active"/></c:if>
 <c:if test="${path eq 'articles'}"><c:set var = "articles" value = "active"/></c:if>
 <c:if test="${path eq 'adv'}"><c:set var = "adv" value = "active"/></c:if>
 <c:if test="${path eq 'rootlinks'}"><c:set var = "rootlinks" value = "active"/></c:if>
 <c:if test="${path eq 'users'}"><c:set var = "users" value = "active"/></c:if>


	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto nav-tabs">
			<c:if test="${auth eq true}">
				<li class="nav-item"><a class="nav-link <c:out value = "${home}"/>" href="/">Home</a></li>
				<li class="nav-item"><a class="nav-link <c:out value = "${profile}"/>" href="/profile">Profile</a></li>
				<li class="nav-item"><a class="nav-link <c:out value = "${freelinks}"/>" href="/freelinks">Free Links</a></li>
				<li class="nav-item"><a class="nav-link <c:out value = "${links}"/>" href="/links">Payed Links</a></li>
				<li class="nav-item"><a class="nav-link <c:out value = "${stats}"/>" href="/stats">Statistics</a></li>
				<c:if test="${admin eq true}">
					<li class="nav-item"><a class="nav-link <c:out value = "${articles}"/>" href="/articles">Articles</a></li>
					<li class="nav-item"><a class="nav-link <c:out value = "${adv}"/>" href="/promo">Advertising</a></li>
					<li class="nav-item"><a class="nav-link <c:out value = "${rootlinks}"/>" href="/rootlinks">Root Links</a></li>
					<li class="nav-item"><a class="nav-link <c:out value = "${users}"/>" href="/users">Users</a></li>
				</c:if>
			</c:if>
			<c:if test="${auth eq false}">
				<li class="nav-item"><a class="nav-link active" href="#">Home</a></li>
			</c:if>
		</ul>

		<c:if test="${auth eq false}">
			<button type="button" class="btn btn-outline-primary"
				data-toggle="modal" data-target="#loginFormModal">Login</button>
			<span>&nbsp;</span>
			<button type="button" class="btn btn-outline-success"
				data-toggle="modal" data-target="#registerFormModal">Register
				new</button>
		</c:if>
		<c:if test="${auth eq true}">
			<c:url value="/logout" var="logoutUrl" />
			<span class="badge badge-success">${login}</span>
			<span>&nbsp</span>
			<span><a href="${logoutUrl}">exit</a></span>
		</c:if>
	</div>
</nav>