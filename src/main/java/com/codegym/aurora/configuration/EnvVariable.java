package com.codegym.aurora.configuration;

public class EnvVariable {
    public static final String JWT_SECRET = "9a02115a835ee03d5fb83cd8a468ea33e4090aaaec87f53c9fa54512bbef4db8dc656c82a315fa0c785c08b0134716b81ddcd0153d2a7556f2e154912cf5675f";

    public static final long JWT_EXPIRATION_IN_MS = 86400000;

    public static final String VN_PAY_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";

    public static final String VN_PAY_RETURN_URL = "https://aurora-service-3ndqmbuk6q-et.a.run.app/api/vnpay/payment-success";
//    public static final String VN_PAY_RETURN_URL = "http://localhost:8080/api/vnpay/payment-success";

    public  static final String VN_PAY_TERMINAL_CODE = "TBVUJ18L";

    public  static final String VN_PAY_HASH_SECRET = "XFHHNFXEGIXYFZYITNOAFPQSSCQRTCFX";

    public static final String VN_PAY_API_URL = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction";
}
