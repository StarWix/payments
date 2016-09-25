var PaymentFormViewModel = function() {
    var self = this;

    self.transaction = new utils.entities.transaction();
    self.transaction.currency("USD");

    self.commission = ko.observable();

    self.errors = {
        fields: ko.observable({}),
        alert: ko.observable()
    };

    self.isValid = function () {
        return self.transaction.isValid() && self.commission();
    };

    function clearErrors() {
        self.errors.fields({});
        self.errors.alert(undefined);
    }

    function updateErrors(error) {
        var data = error.responseJSON;
        if (typeof data === "string") {
            self.errors.alert(data);
        } else {
            self.errors.fields(data);
        }
    }

    function updateCommission() {
        if (self.transaction.sender.number() && self.transaction.currency() && self.transaction.amount()) {
            clearErrors();
            $.ajax({
                url: "/api/commissions/calc",
                method: "POST",
                data: {
                    number: self.transaction.sender.number(),
                    currency: self.transaction.currency(),
                    amount: self.transaction.amount()
                },
                success: function (data) {
                    self.commission(data);
                },
                error: updateErrors
            })
        } else {
            self.commission(undefined);
        }
    }

    self.transaction.sender.number.subscribe(updateCommission);
    self.transaction.currency.subscribe(updateCommission);
    self.transaction.amount.subscribe(updateCommission);

    self.send = function() {
        clearErrors();
        $.ajax({
            url: "/api/transactions",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(ko.mapping.toJS(self.transaction)),
            success: function (data) {

            },
            error: updateErrors
        });
    }
};