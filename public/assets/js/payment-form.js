var PaymentFormViewModel = function() {
    var self = this;

    self.transaction = new utils.entities.transaction();
    self.transaction.currency("USD");

    self.commission = ko.observable();

    self.errors = ko.observable();

    self.isValid = function () {
        return self.transaction.isValid() && self.commission();
    };

    function updateCommission() {
        if (self.transaction.sender.number() && self.transaction.currency() && self.transaction.amount()) {
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
                error: function (data) {
                    self.errors(ko.mapping.fromJS(data));
                }
            })
        } else {
            self.commission(undefined);
        }
    }

    self.transaction.sender.number.subscribe(updateCommission);
    self.transaction.currency.subscribe(updateCommission);
    self.transaction.amount.subscribe(updateCommission);

    self.send = function() {
        
    }
};