<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Tour Creator</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <script src="../../../resources/styles/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="../../../resources/styles/bootstrap/css/bootstrap.min.css" />
    <script src="../../../resources/styles/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../resources/styles/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="../../../resources/styles/css/uui-all.css" />
    <link rel="stylesheet" href="../../../resources/styles/fonts/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" href="../../../resources/styles/css/lib/components/bootstrap-select.min.css" />
    <script src="../../../resources/styles/js/lib/components/bootstrap-select.min.js"></script>
    <script src="../../../resources/styles/js/uui-dropdown.min.js"></script>
    <link rel="stylesheet" href="../../../resources/styles/jquery-ui/jquery-ui.min.css" />
    <script src="../../../resources/styles/jquery-ui/jquery-ui.min.js"></script>
    <script src="../../../resources/styles/custom/script-protection.js"></script>
</head>
<body>
<div class="wrapper">
    <#include "../templates/header.ftl">
    <div class="uui-main-container">
        <main>
            <div class="row">
                <div class="col-md-3 col-md-offset-2 text-right">
                    <br>
                    <h1 class="section-title">Create new tour</h1>
                    <form role="form" action="/reg-tour" method="post">
                        <div>
                            <label for="photo"><h2>Tour photo</h2></label>
                            <input name="photo" type="url" onkeyup="scriptProtectionUrl(this)" onblur="checkURL(this)" placeholder="tour photo" class="uui-form-element large" pattern=".{11,256}" required title="11 to 256 characters" autofocus/>
                        </div>
                        <div>
                            <label for="date"><h2>Date</h2></label>
                            <input id="date" name="date" type="date" class="uui-form-element large" min="2018-01-01" maxlength="2050-01-01" required/>
                        </div>
                        <div class="uui-slider min-range"></div>
                        <div class="slider-info">
                            Days:
                            <span>9 day(s)</span>
                            <input type="hidden" name="duration" value="9"/>
                        </div>

                        <div>
                            <label for="description"><h2>Description</h2></label>
                            <textarea class="uui-form-element" rows="2" cols="10" name="description" pattern=".{20,}" required title="minimum 20 characters"></textarea>
                        </div>
                        <div>
                            <label for="cost"><h2>Cost</h2></label>
                            <input name="cost" type="number" min="10" max="100000000" step="0.01" placeholder="cost" class="uui-form-element large" required/>
                        </div>
                        <div>
                            <label for="tourType"><h2>Type of vacation</h2></label>
                            <select id="tourType" name="tourType" class="selectpicker uui-form-element" required>
                            <#list tourTypes as tourType>
                                <option value="${tourType}">${tourType}</option>
                            </#list>
                            </select>
                        </div>

                        <div>
                            <label for="hotel"><h2>Hotel</h2></label>
                            <select name="hotel" class="selectpicker uui-form-element large" data-live-search="true" title="Choose one of the following hotels" required>
                                <#list hotels as hotel>
                                    <option value="${hotel.id?c}">${hotel.name}</option>
                                </#list>
                            </select>
                        </div>
                        <div>
                            <label for="country"><h2>Country</h2></label>
                            <select name="country" class="selectpicker uui-form-element large" data-live-search="true" title="Choose one of the following countries" required>
                                <#list countries as country>
                                    <option value="${country.id?c}">${country.name}</option>
                                </#list>
                            </select>
                        </div>
                        <br>
                        <button type="submit" class="uui-button large blue">Register a tour</button>
                    </form>

                    <script>
                        var today = new Date();
                        var maxDay = new Date();
                        var dd = today.getDate();
                        var mm = today.getMonth()+1;
                        var yyyyMin = today.getFullYear();
                        var yyyyMax = today.getFullYear() + 10;
                        if(dd<10){
                            dd='0'+dd
                        }
                        if(mm<10){
                            mm='0'+mm
                        }
                        today = yyyyMin+'-'+mm+'-'+dd;
                        maxDay = yyyyMax+'-'+mm+'-'+dd;
                        document.getElementById("date").setAttribute("min", today);
                        document.getElementById("date").setAttribute("max", maxDay);

                        $('.uui-slider.min-range').each(
                                function(){
                                    $(this).slider({
                                        range: 'min',
                                        value: 9,
                                        min: 1,
                                        max: 60,
                                        slide: function (event, ui) {
                                            $(this).siblings('.slider-info').find('span').text(ui.value + 'day(s)');
                                            $(this).siblings(".slider-info").find('input').value(ui.value);
                                        }
                                    });
                                }
                        );

                        $('.selectpicker').uui_dropdown();
                    </script>
                </div>
            </div>
        </main>
    </div>
    <#include "../templates/footer.ftl">
</div>
</body>
</html>