<header>
    <div class="uui-header">
        <nav>
            <!--Responsive html-layuot-->
            <div class="uui-responsive-header">
                <div class="responsive-header">
                    <a href="/" class="responsive-brand-logo">
                        <span class="arrow fa fa-angle-left"></span>
                        <span class="logo">
                            <img src="/resources/styles/images/Travel-Beach-icon.png" alt="travel agency" />
                        </span>
                        <span class="title">Travel Agency</span>
                    </a>
                    <ul class="responsive-header-tools nav navbar-nav">
                        <li class="responsive-header-icon search-icon">
                            <a href="#" onclick="return false">
                                <i class="fa fa-search"></i>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="responsive-global-search">
                    <div class="search-header">
                        <div class="header-wrapper">
                            <div class="search-field">
                                <label></label>
                                <input type="search" class="uui-search" placeholder="Search" />
                            </div>
                            <div class="hide-search">
                                <span></span>
                                <span></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="responsive-menu">
                    <div class="menu-wrapper">
                        <div class="menu-scroll">
                            <ul class="nav navbar-nav">
                                <li class="sub-menu profile-menu">
                                    <a href="#"><span>Profile</span></a>
                                    <ul class="sub">
                                        <li class="sign-up"><a href="/sign-up"><span>Sign up</span></a></li>
                                        <li class="login"><a href="/sign-in"><span>Sign in</span></a></li>
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
            <#---->
            <div class="uui-input-group horizontal">
                <button class="uui-button transparent large">Profile</button>
                <button class="uui-button transparent large white">Sign Out</button>
            </div>
            <#--todo add buttons to profile dropdown menu when user is logined-->
            <ul class="uui-header-tools nav navbar-nav">
                <li class="uui-global-search">
                    <div class="search-field">
                        <label></label>
                        <input type="search" class="uui-search" placeholder="Search" />
                    </div>
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
                            <li class="sign-up"><a href="/sign-up"><i class="fa fa-sign-in"></i>Sign up</a></li>
                            <li class="login"><a href="/sign-in"><i class="fa fa-sign-in"></i>Sign in</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </nav>
    </div>
</header>