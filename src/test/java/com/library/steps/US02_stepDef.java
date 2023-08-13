package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class US02_stepDef {
    DashBoardPage dashBoardPage = new DashBoardPage();
    int expected_borrowed_books_number;
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        expected_borrowed_books_number = Integer.parseInt(dashBoardPage.borrowedBooksNumber.getText());
    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        String query = "select * from book_borrow where is_returned=0;";
        DB_Util.runQuery(query);
        Assert.assertEquals(DB_Util.getRowCount(), expected_borrowed_books_number);
    }
}
