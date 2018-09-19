<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Tour Update</title>
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
</head>
<body>
<div class="wrapper">
    <#include "../templates/header.ftl">
    <div class="uui-main-container">
        <main>
            <div class="row">
                <div class="col-md-3 col-md-offset-2 text-right">
                    <br>
                    <h1 class="section-title">Tour Update</h1>
                    <form role="form" action="/update-tour" method="post">
                        <input type="hidden" name="id" value="${tour.id?c}"/>
                        <div>
                            <label for="photo"><h2>Tour photo</h2></label>
                            <input name="photo" type="text" value="${tour.photo}" class="uui-form-element large" required autofocus/>
                        </div>
                        <div>
                            <label for="date"><h2>Date</h2></label>
                            <input name="date" type="date" value="${tour.date}" class="uui-form-element large" required/>
                        </div>
                        <div class="uui-slider min-range"></div>
                        <div class="slider-info">
                            Days:
                            <span>${tour.duration} day(s)</span>
                            <input type="hidden" name="duration" value="${tour.duration}"/>
                        </div>

                        <script>
                            $('.uui-slider.min-range').each(
                                function(){
                                    $(this).slider({
                                        range: 'min',
                                        value: ${tour.duration},
                                        min: 1,
                                        max: 60,
                                        slide: function (event, ui) {
                                            $(this).siblings('.slider-info').find('span').text(ui.value + 'day(s)');
                                            $(this).siblings(".slider-info").find('input').value(ui.value);
                                        }
                                    });
                                }
                            );
                        </script>

                        <div>
                            <label for="text"><h2>Description</h2></label>
                            <textarea class="uui-form-element" rows="2" cols="10" name="description" required>${tour.description}</textarea>
                        </div>
                        <div>
                            <label for="cost"><h2>Cost</h2></label>
                            <input name="cost" type="text" value="${tour.cost}" class="uui-form-element large" required/>
                        </div>
                        <div>
                            <label for="tourType"><h2>Type of vacation</h2></label>
                            <select name="tourType" class="selectpicker uui-form-element">
                                <#list tourTypes as tourType>
                                    <#if tourType == tour.tourType>
                                        <option value="${tourType}" selected>${tourType}</option>
                                    <#else>
                                        <option value="${tourType}">${tourType}</option>
                                    </#if>
                                </#list>
                            </select>
                        </div>

                        <div>
                            <label for="hotel"><h2>Hotel</h2></label>
                            <select name="hotel" class="selectpicker uui-form-element large" data-live-search="true" title="Choose one of the following hotels">
                                <#list hotels as hotel>
                                    <#if hotel.id == tour.hotel.id>
                                        <option value="${hotel.id?c}" selected>${hotel.name}</option>
                                    <#else>
                                        <option value="${hotel.id?c}">${hotel.name}</option>
                                    </#if>
                                </#list>
                            </select>
                        </div>
                        <div>
                            <label for="country"><h2>Country</h2></label>
                            <select name="country" class="selectpicker uui-form-element large" data-live-search="true" title="Choose one of the following countries">
                                <#list countries as country>
                                    <#if country.id == tour.country.id>
                                       <option value="${country.id?c}" selected>${country.name}</option>
                                    <#else>
                                        <option value="${country.id?c}">${country.name}</option>
                                    </#if>
                                </#list>
                            </select>
                        </div>
                        <br>
                        <button type="submit" class="uui-button large blue">Update the tour</button>
                    </form>

                    <script>
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