init();

function init() {
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8080/api/transactions",
        "method": "GET"
    }

    $.ajax(settings).done(function(response) {
        console.log(response);

        for (var i = 0; i < response.length; i++) {

            var newRowContent = "<tr><td>" + response[i].sell_transaction_id + "</td><td>" + response[i].quantity + "</td><td>" + response[i].price + "</td><td>" + response[i].buy_transaction_id + "</td></tr>"
            $("#transactions").append(newRowContent);

        }
    });

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8080/api/buys",
        "method": "GET"
    }

    $.ajax(settings).done(function(response) {
        console.log(response);

        for (var i = 0; i < response.length; i++) {

            var newRowContent = "<tr><td>" + response[i].transaction_id + "</td><td>" + response[i].quantity + "</td><td>" + response[i].price + "</td><td>" + response[i].time + "</td><td>" + response[i].stock + "</td></tr>"
            $("#buy").append(newRowContent);

        }
    });

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8080/api/sells",
        "method": "GET"
    }

    $.ajax(settings).done(function(response) {
        console.log(response);

        for (var i = 0; i < response.length; i++) {
            var newRowContent = "<tr><td>" + response[i].transaction_id + "</td><td>" + response[i].quantity + "</td><td>" + response[i].price + "</td><td>" + response[i].time + "</td><td>" + response[i].stock + "</td></tr>"
            $("#sell").append(newRowContent);

        }
    });

}
