<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Hotel Creator</title>
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
</head>
<body>
<div class="wrapper">
    <#include "../templates/header.ftl">
    <div class="uui-main-container">
        <main>
            <div class="row">
                <div class="col-md-3 col-md-offset-2 text-right">
                    <br>
                    <h1 class="section-title">Create new review</h1>
                    <form role="form" action="/reg-hotel" method="post">
                        <div>
                            <label for="name"><h2>Hotel name</h2></label>
                            <input name="name" type="text" placeholder="hotel name" class="uui-form-element large" required autofocus/>
                        </div>
                        <div>
                            <label for="stars"><h2>Stars</h2></label>
                            <select name="stars" class="selectpicker uui-form-element large" title="Choose amount of stars">
                                    <#list 1..5 as stars>
                                        <option value="${stars}">${stars} stars</option>
                                    </#list>
                            </select>
                        </div>
                        <div>
                            <label for="website"><h2>Website</h2></label>
                            <input name="website" type="text" placeholder="website address" class="uui-form-element large" required/>
                        </div>
                        <div>
                            <label for="latitude"><h2>Latitude</h2></label>
                            <input name="latitude" type="text" placeholder="latitude" class="uui-form-element large" required/>
                        </div>
                        <div>
                            <label for="longitude"><h2>Longitude</h2></label>
                            <input name="longitude" type="text" placeholder="longitude" class="uui-form-element large" required/>
                        </div>
                        <div>
                            <label for="features"><h2>Features</h2></label>
                            <select name="features" multiple class="selectpicker uui-form-element">
                                    <#list features as feature>
                                        <option value="${feature}">${feature}</option>
                                    </#list>
                            </select>
                        </div>
                        <br>
                        <button type="submit" class="uui-button large blue">Register a hotel</button>
                    </form>
                </div>
            </div>
            <script>

                $('.selectpicker').uui_dropdown();
            </script>
        </main>
    </div>
    <#include "../templates/footer.ftl">
</div>
</body>
</html>