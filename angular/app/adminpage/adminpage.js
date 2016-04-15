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
            url: 'http://stg-practice-goals-practice.cfapps.io/practices',
            headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : 'http://localhost:8000/app/'}
        }).then(function(response) {
            $scope.practices = response.data;
        });

        $http({
            method:'Get',
            url: 'http://stg-practice-goals-training.cfapps.io/trainingtypes',
            headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : 'http://localhost:8000/app/'}
        }).then(function(response) {
            $scope.types = response.data;
        });
        
        $http({
            method:'Get',
            url: 'http://stg-practice-goals-practice.cfapps.io/practices/goalsreport',
            headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : 'http://localhost:8000/app/'}
        }).then(function(response) {
            $scope.goals = response.data;
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
                url     : 'http://stg-practice-goals-practice.cfapps.io/practices/progressreport',
                data    : submitGoal, //forms user object
                headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin' : 'http://localhost:8000'}
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