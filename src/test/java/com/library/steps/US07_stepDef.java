package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;

import java.security.Key;

public class US07_stepDef {

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    String bookName;


    @Given("the {string} on the home page")
    public void theOnTheHomePage(String user) {
        loginPage.login(user);
        //BrowserUtil.waitFor(2);
    }

    @And("the user navigates to {string} page")
    public void theUserNavigatesToPage(String page) {
        bookPage.navigateModule(page);
    }

    @And("the user searches for {string} book")
    public void theUserSearchesForBook(String bookName) {
        bookPage.search.sendKeys(bookName + Keys.ENTER);
        this.bookName=bookName;
    }

    @When("the user clicks Borrow Book")
    public void theUserClicksBorrowBook() {
        bookPage.borrowBook(bookName).click();
    }

    @Then("verify that book is shown in {string} page")
    public void verifyThatBookIsShownInPage(String page) {
        bookPage.navigateModule(page);
        BrowserUtil.waitFor(5);

    }

    @And("verify logged student has same book in database")
    public void verifyLoggedStudentHasSameBookInDatabase() {

    }
}
