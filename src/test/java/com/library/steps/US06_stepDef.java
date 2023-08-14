package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US06_stepDef {
    BookPage bookPage = new BookPage();
    @When("the librarian click to add book")
    public void theLibrarianClickToAddBook() {
        bookPage.addBook.click();
    }

    @And("the librarian enter book name {string}")
    public void theLibrarianEnterBookName(String bookName) {
        bookPage.bookName.sendKeys(bookName);

    }

    @When("the librarian enter ISBN {string}")
    public void theLibrarianEnterISBN(String iSBN) {
        bookPage.isbn.sendKeys(iSBN);
    }

    @And("the librarian enter year {string}")
    public void theLibrarianEnterYear(String year) {
        bookPage.year.sendKeys(year);
    }

    @When("the librarian enter author {string}")
    public void theLibrarianEnterAuthor(String authorFN) {
        bookPage.author.sendKeys(authorFN);
    }

    @And("the librarian choose the book category {string}")
    public void theLibrarianChooseTheBookCategory(String categoryName) {
        BrowserUtil.selectOptionDropdown(bookPage.categoryDropdown,categoryName);
    }

    @And("the librarian click to save changes")
    public void theLibrarianClickToSaveChanges() {
        bookPage.saveChanges.click();
    }

    @Then("verify {string} message is displayed")
    public void verifyMessageIsDisplayed(String messageText) {
        bookPage.toastMessage.isDisplayed();
    }

    @And("verify {string} information must match with DB")
    public void verifyInformationMustMatchWithDB(String actualBookName) {

        Assert.assertEquals(actualBookName,bookPage.expectedBookNameInfo(actualBookName));
    }
}
