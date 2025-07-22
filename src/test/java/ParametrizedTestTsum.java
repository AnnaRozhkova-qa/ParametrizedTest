import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class ParametrizedTestTsum {

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        open("https://tsum.ru/");
    }


    @ValueSource(strings = {
            "футболка", "джинсы"})

    @ParameterizedTest
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    void successfulSearchTest(String searchQuery) {
        $("[data-test-id=searchButton]").click();
        $("[data-test-id=searchInputWrapper]").setValue(searchQuery).pressEnter();
        $$("[data-test-id=itemWrapper]").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

    ;

    @CsvFileSource(resources = "/FileForSearchTsumTest.csv")

    @ParameterizedTest(name = "Для поискового запроса {0} в карточке товара должен быть результат со словом {1}")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    void searchResultQueryCheckCsvFileTest(String searchQuery, String name) {
        $("[data-test-id=searchButton]").click();
        $("[data-test-id=searchInputWrapper]").setValue(searchQuery).pressEnter();
        $$("[data-test-id=itemWrapper]").shouldBe(CollectionCondition.sizeGreaterThan(0));
        $("[data-test-id=itemWrapper]").shouldHave(text(name));

    }

    @CsvSource(value = {
            "Футболка, Футболка",
            "Джинсы, Джинсы"
    })

    @ParameterizedTest(name = "Для поискового запроса {0} в карточке товара должен быть результат со словом {1}")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })

    void searchResultQueryCheckCsvTest(String searchQuery, String name) {
        $("[data-test-id=searchButton]").click();
        $("[data-test-id=searchInputWrapper]").setValue(searchQuery).pressEnter();
        $$("[data-test-id=itemWrapper]").shouldBe(CollectionCondition.sizeGreaterThan(0));
        $("[data-test-id=itemWrapper]").shouldHave(text(name));

    }


}

