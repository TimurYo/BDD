package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import ru.netology.data.Data;

import java.time.Duration;

public class TransferPage {
    private final SelenideElement fromInput = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("[data-test-id='button-transfer']");
    private final SelenideElement amountInputNew = $("[data=test-id='amount'] input");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private  final SelenideElement errorMessage = $("[data-test-id='error-message']");

    public TransferPage() {
        transferHead.shouldBe(Condition.visible);
    }

    public Dashboard makeValidTransfer(String amountToTransfer, Data.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new Dashboard();
    }

    public void makeTransfer(String amountToTransfer, Data.CardInfo cardInfo) {
        amountInputNew.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
