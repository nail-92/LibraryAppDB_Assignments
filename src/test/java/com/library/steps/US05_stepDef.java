package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US05_stepDef {
    @When("I execute query to find most popular book genre")
    public void iExecuteQueryToFindMostPopularBookGenre() {
        String query = "select bc.name, count(*)\n" +
                "from book_borrow bb\n" +
                "         inner join books b on bb.book_id = b.id\n" +
                "         inner join book_categories bc on b.book_category_id = bc.id\n" +
                "\n" +
                "group by name\n" +
                "order by 2 desc;";
        DB_Util.runQuery(query);
    }

    @Then("verify {string} is the most popular book genre.")
    public void verifyIsTheMostPopularBookGenre(String expectedGenreName) {
        String actualGenreName = DB_Util.getCellValue(1,1);
        Assert.assertEquals(expectedGenreName,actualGenreName);
    }
}
