package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;


public class DeliveryCardTest {
    LocalDate today = LocalDate.now();
    LocalDate later = today.plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }


    @Test
    void shouldEnteringValidValues() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").setValue("formatter.format(later)");
        $("[data-test-id='name'] input").setValue("Кононов Яков");
        $("[data-test-id='phone'] input").setValue("+79002233222");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно забронирована на " + formatter.format(later)));
    }
}
