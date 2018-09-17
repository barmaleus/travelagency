<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#assign tags=JspTaglibs["http://www.springframework.org/tags"] />
<#import "/spring.ftl" as spring/>   <#--todo delete later-->
<header>
    <div class="uui-header">
        <nav>
            <!--Responsive html-layuot-->
            <div class="uui-responsive-header">
                <div class="responsive-header">
                    <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                    <div class="responsive-toggle-box">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                    <div class="responsive-hide-menu">
                        <span></span>
                        <span></span>
                    </div>
                    </@security.authorize>
                    <a href="/" class="responsive-brand-logo">
                        <span class="arrow fa fa-angle-left"></span>
                        <span class="logo">
                            <img src="/resources/styles/images/Travel-Beach-icon.png" alt="travel agency" />
                        </span>
                        <span class="title"><@tags.message code="header.name"/></span>
                    </a>
                </div>
                <div class="responsive-menu">
                    <div class="menu-wrapper">
                        <div class="menu-scroll">
                            <ul class="nav navbar-nav">
                            <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                                <li class="sub-menu">
                                    <a href="#"><span><@tags.message code="header.manage"/></span></a>
                                    <ul class="sub">
                                        <li><a href="/tours" onclick="return false;"><@tags.message code="c.tours"/></a></li>
                                        <li><a href="/users" onclick="return false;"><@tags.message code="c.users"/></a></li>
                                        <li><a href="/hotels" onclick="return false;"><@tags.message code="c.hotels"/></a></li>
                                        <li><a href="/reviews" onclick="return false;"><@tags.message code="c.reviews"/></a></li>
                                        <li><a href="/countries" onclick="return false;"><@tags.message code="c.countries"/></a></li>
                                    </ul>
                                <li>
                                    <a href="import"><span><@tags.message code="header.import"/></span></a>
                                </li>
                            </@security.authorize>
                                <li class="sub-menu profile-menu">
                                    <a href="#"><span><@tags.message code="header.profile"/></span></a>
                                    <ul class="sub">
                                         <@security.authorize access="isAuthenticated()">
                                             <li class="profile"><a href="/profile"><span><@tags.message code="header.profile"/></span></a></li>
                                         </@security.authorize>
                                        <@security.authorize access="! isAuthenticated()">
                                            <li class="sign-up"><a href="/sign-up"><span><@tags.message code="header.signup"/></span></a></li>
                                        </@security.authorize>
                                        <@security.authorize access="isAuthenticated()">
                                            <li class="logout"><a href="/sign-out"><span><@tags.message code="header.signout"/></span></a></li>
                                        </@security.authorize>
                                        <@security.authorize access="! isAuthenticated()">
                                            <li class="login"><a href="/sign-in"><span><@tags.message code="header.signin"/></span></a></li>
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
                <@tags.message code="header.name"/>
            </a>
            <ul class="uui-navigation nav navbar-nav">
                <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <@tags.message code="header.manage"/>
                            <span class="arrow fa fa-angle-down"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/tours"><@tags.message code="c.tours"/></a></li>
                            <li><a href="/users"><@tags.message code="c.users"/></a></li>
                            <li><a href="/hotels"><@tags.message code="c.hotels"/></a></li>
                            <li><a href="/reviews"><@tags.message code="c.reviews"/></a></li>
                            <li><a href="/countries"><@tags.message code="c.countries"/></a></li>
                            <li><a href="/countries"><@spring.message "c.countries"/></a></li>  <#--todo-->
                        </ul>
                    </li>
                <li><a href="/import"><@tags.message code="header.import"/></a></li>
                </@security.authorize>
            </ul>
            <#--right bar-->
            <ul class="uui-header-tools nav navbar-nav">
                <li>
                    <ul class="uui-navigation nav navbar-nav">
                        <li>
                            <a href="?lang=en">English</a>
                        </li>
                        <li>
                            <a href="?lang=ru">Русский</a>
                        </li>
                    </ul>
                </li>
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
                                <li class="profile"><a href="/profile"><i class="fa fa-male"></i><@tags.message code="header.profile"/></a></li>
                            </@security.authorize>
                            <@security.authorize access="! isAuthenticated()">
                                <li class="sign-up"><a href="/sign-up"><i class="fa fa-registered"></i><@tags.message code="header.signup"/></a></li>
                            </@security.authorize>
                            <@security.authorize access="isAuthenticated()">
                                <li class="logout"><a href="/sign-out"><i class="fa fa-sign-out"></i><@tags.message code="header.signout"/></a></li>
                            </@security.authorize>
                            <@security.authorize access="! isAuthenticated()">
                                <li class="login"><a href="/sign-in"><i class="fa fa-sign-in"></i><@tags.message code="header.signin"/></a></li>
                            </@security.authorize>
                        </ul>
                    </div>
                </li>
            </ul>
        <#--end right bar-->
        </nav>
    </div>
</header>