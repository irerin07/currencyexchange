document.addEventListener('DOMContentLoaded', function() {
    getCurrencyRate();
});


function getCurrencyRate() {
    var from = $('#from').val();
    var to = $('#to').val();
    console.log(from);
    console.log(to);
    var amount = document.getElementById("amount").defaultValue = 0.0;
    $.ajax({
        url: "/api/exchangerate?from=" + from + "&to=" + to + "&amount=" + amount,
        type: 'get',
        success: function (data) {
            console.log(data);
            $('#currency').text(data + " " + to + "/" + from);
        },
        error: function (err) {
            if (err.status === 400) {
                alert("400 Bad Request. 값을 불러올 수 없습니다.");
            } else if (err.status === 500) {
                alert("500 Internal Server Error. 서버에 문제가 발생했습니다.");
            }
        }
    })
}

function getExchangedAmount() {
    var from = $('#from').val();
    var to = $('#to').val();
    var amount = $('#amount').val();

    if (wrongInput()) {
        $('#inputCheckResult').html("<div style='color: #ff6666;'><p>송금액을 확인해주세요.</p></div>");
        return;
    }

    console.log(from);
    console.log(to);
    console.log(amount);
    $.ajax({
        url: "/api/exchangedamount?from=" + from + "&to=" + to + "&amount=" + amount,
        type: 'get',
        success: function (data) {
            console.log(data);
            $('#currency').text(data.currency + " " + to + "/" + from);
            $('#inputCheckResult').html("<div style='color: forestgreen;'><p>" + data.stringAmount + " " + to + "을/를 받으실 수 있습니다.</p></div>");
        },
        error: function (err) {
            if (err.status === 400) {
                alert("400 Bad Request. 값을 불러올 수 없습니다.");
            } else if (err.status === 500) {
                alert("500 Internal Server Error. 서버에 문제가 발생했습니다.");
            }
        }
    })

    function wrongInput() {
        var amount = $('#amount').val();
        var reg = /^(?:0|[1-9][0-9]*)\.[0-9]+$/;
        if (reg.test(amount) == false) {
            return true;
        }
        return (amount.length <= 0 || amount < 0 || amount > 10000);
    }

}