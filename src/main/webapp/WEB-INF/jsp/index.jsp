<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <base href="/WAP3m/">
        <title>WAP - 3 - Java - Spring MVC</title>
        <meta name="author" content="Djordje Gavrilovic">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="resources/main1.css">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet"> 
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  
    </head>
    <body>
        <div ng-app="myApp" ng-controller="myCtrl" data-ng-init="init()" id="mainDiv">                   
            <h1>WebAppsProject - 3 - Java - Spring MVC</h1>
            <!-- input form -->
            <form novalidate ng-submit="sendSave()" name="formX">
                <label>Title:</label><br>
                <input type="text" id="title" placeholder="Up to 45" name="title" ng-model="title"><br>
                <label>Text:</label><br>
                <textarea id="txt" rows="5" placeholder="Up to 500" name="txt" ng-model="txt"></textarea>
                <br><br>
                <button type="submit">Save</button>
            </form>
            <!-- display titles -->
            <table>
                <tr ng-repeat="x in myRes">
                    <td ng-click="displayText(x.id)">{{x.title}}</td>
                </tr>
            </table>
            <!-- display selected text and delete button -->
            <div id="displayDiv">
                {{entry.text}}
                <br>
                <button ng-show="show" ng-click="sendDelete()">Delete</button>
            </div>
            <!-- footer -->
            <p>DJORDJE GAVRILOVIC 2018.</p>
        </div>
        <!-- Angular -->
        <script>
            var app = angular.module('myApp', []);
            app.controller('myCtrl', function ($scope, $http) {

                $scope.myRes;
                $scope.entry;
                $scope.show = false;

                $scope.displayText = function (entryId) {
                    console.log(entryId);
                    console.log($scope.myRes);
                    $scope.entry = $scope.myRes[entryId];
                    $scope.show = true;
                };

                /* 
                 * post req
                 */
                $scope.sendSave = function () {
                    // use $.param jQuery function to serialize data from JSON
                    var data = $.param({
                        title: $scope.title,
                        txt: $scope.txt
                    });
                    var config = {
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                        }
                    };
                    $http.post("new_entry", data, config)
                            .then(
                                    function (response) {
                                        console.log("POST SUCCESS: " + response.data);
                                        init();
                                    },
                                    function (response) {
                                        console.log("POST FAILURE: " + response.data);
                                    }
                            );
                    // clearing the form
                    $scope.title = null;
                    $scope.txt = null;
                    $scope.formX.$setUntouched();
                    $scope.formX.$setPristine();
                };

                /* 
                 * delete req
                 */
                $scope.sendDelete = function () {                    
                    var data = $.param({
                        entryId: $scope.entry.id
                    });
                    var config = {
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                        }
                    };
                    $http.delete("delete?"+ data)
                            .then(
                                    function (response) {
                                        console.log("DELETE SUCCESS: " + response.data);
                                        // updating model
                                        delete $scope.myRes[$scope.entry.id];
                                        $scope.entry={};
                                    },
                                    function (response) {
                                        console.log("DELETE FAILURE: " + response.data);
                                    }
                            );
                };

                /* 
                 * init get req
                 */
                var init = function () {
                    $http.get("entries")
                            .then(
                                    function (response) {
                                        $scope.myRes = response.data;
                                        console.log("GET SUCCESS: " + response.data);
                                    },
                                    function (response) {
                                        console.log("GET FAILURE: " + response.data);
                                    }
                            );
                };
                init();

            });
        </script>
    </body>
</html>
