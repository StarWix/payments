var utils = {
    entities: {
        cardInformation: function() {
            this.number = ko.observable();
            this.firstName = ko.observable();
            this.lastName = ko.observable();
            this.month = ko.observable();
            this.year = ko.observable();
        },
        transaction: function() {
            this.sender = new utils.entities.cardInformation();
            this.receiver = new utils.entities.cardInformation();
            this.amount = ko.observable();
            this.currency = ko.observable();
        }
    }
};