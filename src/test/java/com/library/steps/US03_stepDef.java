package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class US03_stepDef {
    BookPage bookPage = new BookPage();
    @And("the user clicks book categories")
    public void theUserClicksBookCategories() {
        bookPage.mainCategoryElement.click();
    }

    @Then("verify book categories must match book_categories table from db")
    public void verifyBookCategoriesMustMatchBook_categoriesTableFromDb() {
        List<String> actual_categories = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actual_categories.remove(0);

        Assert.assertEquals(actual_categories,bookPage.expected_categories());


    }
}
