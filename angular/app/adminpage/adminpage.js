'use strict';

angular.module('myApp.adminpage', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/adminpage', {
            templateUrl: 'adminpage/adminpage.html',
            controller: 'AdminPageCtrl'
        });
    }])

    .controller('AdminPageCtrl', function($scope, $http){
        $http({
            method: 'Get',
            url: 'http://localhost:8080/api/v1/someotherapi/practices',
            headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : 'http://localhost:8080'}
        }).then(function(response) {
            $scope.practices = response.data;
        });

        $http({
            method:'Get',
            url: 'http://localhost:8080/api/v1/someotherapi/trainingtypes',
            headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : 'http://localhost:8080'}
        }).then(function(response) {
            $scope.types = response.data;
        });


        // calling our submit function.
        $scope.submitPracticeGoal = function() {
            var submitGoal = {
                "typeId": $scope.practicegoal.selectedType,
                "timeSpent": $scope.practicegoal.timeInSeconds,
                "notes": $scope.practicegoal.note,
                "practiceId": $scope.practicegoal.selectedPractice
            };



            // Posting data to php file
            $http({
                method  : 'POST',
                url     : 'http://localhost:8080/api/v1/someotherapi/goal',
                data    : submitGoal, //forms user object
                headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : 'http://localhost:8080'}
            })
                .success(function(data) {
                    if (data.errors) {
                        // Showing errors.
                        $scope.error = "Failed to submit data.";
                        $scope.message = null;
                    } else {
                        $scope.message = "Report saved.";
                        $scope.error = null;
                    }
                });
        };
    });