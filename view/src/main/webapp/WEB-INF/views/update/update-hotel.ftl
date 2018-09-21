<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Hotel Update</title>
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
                    <h1 class="section-title">Update the hotel</h1>
                    <form role="form" action="/update-hotel" method="post">
                        <input type="hidden" name="id" value="${hotel.id?c}"/>
                        <div>
                            <label for="name"><h2>Hotel name</h2></label>
                            <input name="name" type="text" onkeyup="scriptProtectionName(this)" value="${hotel.name}" class="uui-form-element large" pattern=".{3,40}" required title="3 to 40 characters" autofocus/>
                        </div>
                        <div>
                            <label for="stars"><h2>Stars</h2></label>
                            <select name="stars" class="selectpicker uui-form-element large" required>
                                <#list 1..5 as stars>
                                    <#if stars == hotel.stars>
                                        <option value="${stars}" selected>${stars} stars</option>
                                    <#else>
                                        <option value="${stars}">${stars} stars</option>
                                    </#if>
                                </#list>
                            </select>
                        </div>
                        <div>
                            <label for="website"><h2>Website</h2></label>
                            <input name="website" type="url" onkeyup="scriptProtectionUrl(this)" value="${hotel.website}" onblur="checkURL(this)" class="uui-form-element large" pattern=".{11,256}" required title="11 to 256 characters"/>
                        </div>
                        <div>
                            <label for="latitude"><h2>Latitude</h2></label>
                            <input name="latitude" type="number" max="90" min="-90" step="0.0000001" value="${hotel.latitude?c}" class="uui-form-element large" required/>
                        </div>
                        <div>
                            <label for="longitude"><h2>Longitude</h2></label>
                            <input name="longitude" type="number" max="180" min="-180" step="0.0000001" value="${hotel.longitude?c}" class="uui-form-element large" required/>
                        </div>
                        <div>
                            <label for="features"><h2>Features</h2></label>
                            <select name="features" multiple class="selectpicker uui-form-element">
                                <#list features as feature>
                                    <#if hotel.features?seq_contains(feature)>
                                        <option value="${feature}" selected>${feature}</option>
                                    <#else>
                                        <option value="${feature}">${feature}</option>
                                    </#if>
                                </#list>
                            </select>
                        </div>
                        <br>
                        <button type="submit" class="uui-button large blue">Update the hotel</button>
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