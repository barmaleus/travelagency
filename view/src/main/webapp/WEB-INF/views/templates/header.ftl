<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<header>
    <div class="uui-header">
        <nav>
            <!--Responsive html-layuot-->
            <div class="uui-responsive-header">
                <div class="responsive-header">
                    <#--todo only for admin-->
                    <div class="responsive-toggle-box">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                    <div class="responsive-hide-menu">
                        <span></span>
                        <span></span>
                    </div>
                    <#--todo end-->
                    <a href="/" class="responsive-brand-logo">
                        <span class="arrow fa fa-angle-left"></span>
                        <span class="logo">
                            <img src="/resources/styles/images/Travel-Beach-icon.png" alt="travel agency" />
                        </span>
                        <span class="title">Travel Agency</span>
                    </a>
                </div>
                <div class="responsive-menu">
                    <div class="menu-wrapper">
                        <div class="menu-scroll">
                            <ul class="nav navbar-nav">

                            <#--todo only for admin-->
                                <li class="sub-menu">
                                    <a href="#"><span>Manage</span></a>
                                    <ul class="sub">
                                        <li><a href="/tours" onclick="return false;">Tours</a></li>
                                        <li><a href="/users" onclick="return false;">Users</a></li>
                                        <li><a href="/hotels" onclick="return false;">Hotels</a></li>
                                        <li><a href="/reviews" onclick="return false;">Reviews</a></li>
                                        <li><a href="/countries" onclick="return false;">Countries</a></li>
                                    </ul>
                                <li>
                                    <a href="import"><span>Load tours</span></a>
                                </li>
                            <#--todo end-->


                                <li class="sub-menu profile-menu">
                                    <a href="#"><span>Profile</span></a>
                                    <ul class="sub">
                                         <@security.authorize access="isAuthenticated()">
                                             <li class="profile"><a href="/profile"><span>Profile</span></a></li>
                                         </@security.authorize>
                                        <@security.authorize access="! isAuthenticated()">
                                            <li class="sign-up"><a href="/sign-up"><span>Sign up</span></a></li>
                                        </@security.authorize>
                                        <@security.authorize access="isAuthenticated()">
                                            <li class="logout"><a href="/sign-out"><span>Sign out</span></a></li>
                                        </@security.authorize>
                                        <@security.authorize access="! isAuthenticated()">
                                            <li class="login"><a href="/sign-in"><span>Sign in</span></a></li>
                                        </@security.authorize>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!---->
            <a href="/" class="brand-logo">
                <span class="logo">
                    <img src="/resources/styles/images/Travel-Beach-icon.png" alt="travel agency" />
                </span>
                Travel Agency
            </a>
            <#--todo only for admin-->
            <ul class="uui-navigation nav navbar-nav">
                <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            Manage
                            <span class="arrow fa fa-angle-down"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/tours">Tours</a></li>
                            <li><a href="/users">Users</a></li>
                            <li><a href="/hotels">Hotels</a></li>
                            <li><a href="/reviews">Reviews</a></li>
                            <li><a href="/countries">Countries</a></li>
                        </ul>
                    </li>
                <li><a href="/import">Load tours</a></li>
                </@security.authorize>
            </ul>
            <#--todo end-->
            <ul class="uui-header-tools nav navbar-nav">
                <li class="dropdown uui-profile-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <div class="profile-photo">
                            <img src="/resources/styles/images/icons/no_photo.png" alt="" />
                        </div>
                    </a>
                    <div class="dropdown-menu" role="menu">
                        <span class="menu-arrow"></span>
                        <ul class="profile-links">
                            <@security.authorize access="isAuthenticated()">
                                <li class="profile"><a href="/profile"><i class="fa fa-male"></i>Profile</a></li>
                            </@security.authorize>
                            <@security.authorize access="! isAuthenticated()">
                                <li class="sign-up"><a href="/sign-up"><i class="fa fa-registered"></i>Sign up</a></li>
                            </@security.authorize>
                            <@security.authorize access="isAuthenticated()">
                                <li class="logout"><a href="/sign-out"><i class="fa fa-sign-out"></i>Sign out</a></li>
                            </@security.authorize>
                            <@security.authorize access="! isAuthenticated()">
                                <li class="login"><a href="/sign-in"><i class="fa fa-sign-in"></i>Sign in</a></li>
                            </@security.authorize>
                        </ul>
                    </div>
                </li>
            </ul>
        </nav>
    </div>
</header>