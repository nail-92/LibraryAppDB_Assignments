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

    @And("the user searches for {string} book")
    public void theUserSearchesForBook(String bookName) {
        bookPage.search.sendKeys(bookName + Keys.ENTER);
        this.bookName=bookName;
    }

    @When("the user clicks Borrow Book")
    public void theUserClicksBorrowBook() {
        BrowserUtil.clickWithJS(bookPage.borrowBook(bookName));
    }

    @Then("verify that book is shown in {string} page")
    public void verifyThatBookIsShownInPage(String page) {
        bookPage.navigateModule(page);
        Assert.assertTrue(borrowedBooksPage.verifyBorrowedBook(bookName));
    }

    @And("verify logged student has same book in database")
    public void verifyLoggedStudentHasSameBookInDatabase() {
        String query = "select full_name,b.name,bb.borrowed_date from users u inner\n" +
                "                join book_borrow bb on u.id = bb.user_id\n" +
                "                join books b on bb.book_id = b.id\n" +
                "                where full_name='Test Student 5' and name='Self Confidence'\n" +
                "                order by 3 desc;";

        DB_Util.runQuery(query);
        String actualBookName = DB_Util.getCellValue(1,2);
        Assert.assertEquals(actualBookName,bookName);

    }
}
