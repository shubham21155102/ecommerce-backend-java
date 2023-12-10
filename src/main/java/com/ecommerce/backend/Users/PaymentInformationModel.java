package com.ecommerce.backend.Users;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PaymentInformationModel {
    @Column(name="cardholder_name")
    private String cardholderName;
    @Column(name="card_number")
    private String cardNumber;
    @Column(name = "card_expiry")
    private String cardExpiry;
    @Column(name="card_cvv")
    private String cardCvv;


}

