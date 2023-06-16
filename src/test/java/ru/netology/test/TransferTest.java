package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.Dashboard;
import ru.netology.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.Data.generateInvalidAmount;
import static ru.netology.data.Data.generateValidAmount;
import ru.netology.data.Data;


public class TransferTest {
    LoginPage loginPage;
    Dashboard dashboard;


    @BeforeEach
    public void setup() {
        loginPage= open("http://localhost:9999/",LoginPage.class);
        var authInfo= Data.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = Data.getCode();
        dashboard = verificationPage.validVerify(verificationCode);
    }

    @Test
    public void testTransferFromFirstCardToSecondCard() {
        var firstCardInfo= Data.getFirstCard();
        var secondCardInfo = Data.getSecondCard();
        var firstCardBalance = dashboard.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboard.getCardBalance(secondCardInfo);
        var amount = generateValidAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance - amount;
        var expectedBalanceSecondCard = secondCardBalance + amount;
        var transferPage = dashboard.selectCardToTransfer(secondCardInfo);
        dashboard = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashboard.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboard.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard,actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

//    @Test
//    public void testGetErrorMessageIfAmountMoreBalance() {
//        var firstCardInfo= Data.getFirstCard();
//        var secondCardInfo = Data.getSecondCard();
//        var firstCardBalance = dashboard.getCardBalance(firstCardInfo);
//        var secondCardBalance = dashboard.getCardBalance(secondCardInfo);
//        var amount = generateInvalidAmount(secondCardBalance);
//        var transferPage = dashboard.selectCardToTransfer(firstCardInfo);
//        transferPage.makeTransfer(String.valueOf(amount),secondCardInfo);
//        transferPage.findErrorMessage("Выполнена попытка перевода суммы, превышающей остаток на карте списания");
//        var actualBalanceFirstCard = dashboard.getCardBalance(firstCardInfo);
//        var actualBalanceSecondCard = dashboard.getCardBalance(secondCardInfo);
//        assertEquals(firstCardBalance,actualBalanceFirstCard);
//        assertEquals(secondCardBalance, actualBalanceSecondCard);
//
//    }
 }
