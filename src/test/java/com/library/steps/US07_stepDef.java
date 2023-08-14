package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;


public class US07_stepDef {

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    BorrowedBooksPage borrowedBooksPage= new BorrowedBooksPage();
    String bookName;


    @Given("the {string} on the home page")
    public void theOnTheHomePage(String user) {
        loginPage.login(user);
    }

    @And("the user navigates to {string} page")
    public void theUserNavigatesToPage(String page) {
        bookPage.navigateModule(page);
    }
    @When("the user searches for {string} book")
    public void theUserSearchesForBook(String bookName) {
        bookPage.searchBook(bookName);
    }


    @When("the user clicks Borrow Book")
    public void theUserClicksBorrowBook() {
        BrowserUtil.clickWithJS(bookPage.borrowBook(bookPage.givenBookName));
    }

    @Then("verify that book is shown in {string} page")
    public void verifyThatBookIsShownInPage(String page) {
        bookPage.navigateModule(page);
        Assert.assertTrue(borrowedBooksPage.verifyBorrowedBook(bookPage.givenBookName));
    }

    @And("verify logged student has same book in database")
    public void verifyLoggedStudentHasSameBookInDatabase() {

        Assert.assertEquals(bookPage.actualBorrowedBookName(),bookPage.givenBookName);

    }
}
