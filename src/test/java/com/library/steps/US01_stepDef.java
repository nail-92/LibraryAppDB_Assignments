package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;

public class US01_stepDef {

    BookPage bookPage = new BookPage();
    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        bookPage.getAllColumns();
    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedColumnNames) {
        List<String> actualColumnNames = DB_Util.getAllColumnNamesAsList();
        Assert.assertEquals(expectedColumnNames,actualColumnNames);
    }
}
