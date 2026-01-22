package com.example.url_shortner.Utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Base62EncoderWithSecretKey {

    @Value("${SECRET_KEY}")
    private long secretKey;

    private static final String BASE62 =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String encode(long id){
        long temp=id^secretKey;
        StringBuilder sb = new StringBuilder();
        while (temp > 0) {
            sb.append(BASE62.charAt((int) (temp % 62)));
            temp /= 62;
        }
        return sb.reverse().toString();
    }

    public long decode(String str) {
        long num = 0;
        for (char c : str.toCharArray()) {
            num = num * 62 + BASE62.indexOf(c);
        }
        return num^secretKey;
    }
}
