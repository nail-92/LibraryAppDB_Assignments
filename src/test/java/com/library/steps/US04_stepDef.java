package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Keys;

public class US04_stepDef {
    BookPage bookPage = new BookPage();

    @And("the user clicks edit book button")
    public void theUserClicksEditBookButton() {
        bookPage.editBook(bookPage.getGivenBookName());
    }

    @Then("book information must match the Database")
    public void bookInformationMustMatchTheDatabase() {

    }
}
