package com.example.demo.lms.paymentInfo;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class PaymentRequest {
    private String amount;
    private String orderId;
    private String orderName;
    private String customerName;
    private String successUrl;
    private String failUrl;
}