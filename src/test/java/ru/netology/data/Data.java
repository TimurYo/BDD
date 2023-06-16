package ru.netology.data;

import lombok.Value;
import org.checkerframework.checker.units.qual.C;
import ru.netology.pages.Dashboard;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;

public class Data {
    static Dashboard dashboard;
    private Data() {
    }

    public static VerificationCode getCode() {
        return new VerificationCode("12345");
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static CardInfo getFirstCard() {
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardInfo getSecondCard() {
        return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

//    public static int generateValidAmount(CardInfo cardInfo) {
//        int balance = dashboard.getCardBalance(cardInfo);
//        return new Random().nextInt(balance) + 1;
//    }
    public static int generateValidAmount(int balance) {
        return new Random().nextInt(balance) + 1;
    }

    public static int generateInvalidAmount( int balance){
        return Math.abs(balance) + new Random().nextInt(10000);
    }


//    public static int generateInvalidAmount(CardInfo cardInfo) {
//        int balance = dashboard.getCardBalance(cardInfo);
//        return Math.abs(balance) + new Random().nextInt(10000);
//    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String testId;
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }
}
