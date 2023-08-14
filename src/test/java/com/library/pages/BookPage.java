package com.library.pages;

import com.library.utility.DB_Util;
import com.library.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookPage extends BasePage {

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(id = "book_categories")
    public WebElement mainCategoryElement;

    @FindBy(name = "name")
    public WebElement bookName;


    @FindBy(xpath = "(//input[@type='text'])[4]")
    public WebElement author;

    @FindBy(xpath = "//div[@class='portlet-title']//a")
    public WebElement addBook;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveChanges;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement toastMessage;

    @FindBy(name = "year")
    public WebElement year;

    @FindBy(name = "isbn")
    public WebElement isbn;

    @FindBy(id = "book_group_id")
    public WebElement categoryDropdown;



    @FindBy(id = "description")
    public WebElement description;


    public static String givenBookName;

//    public String getGivenBookName() {
//        return givenBookName;
//    }
//
//    public void setGivenBookName(String givenBookName) {
//        this.givenBookName = givenBookName;
//    }

    public WebElement editBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement borrowBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public void getAllColumns (){
        String query ="select * from users;";
        DB_Util.runQuery(query);
    }

    public List<String> expected_categories (){
        String query = "select name from book_categories;";
        DB_Util.runQuery(query);
        return DB_Util.getColumnDataAsList(1);

    }
    public void searchBook(String bookName){
        search.sendKeys(bookName + Keys.ENTER);
        this.givenBookName=bookName;
    }

    public String actualBorrowedBookName(){
        String query = "select full_name,b.name,bb.borrowed_date from users u inner\n" +
                "                join book_borrow bb on u.id = bb.user_id\n" +
                "                join books b on bb.book_id = b.id\n" +
                "                where full_name='Test Student 5' and name='Self Confidence'\n" +
                "                order by 3 desc;";

        DB_Util.runQuery(query);
        return DB_Util.getCellValue(1,2);
    }

    public String expectedBookNameInfo (String actualBookName){
        String query = "select id,name,author from books\n" +
                "where name = '"+actualBookName+"'\n" +
                "order by id desc;";
        DB_Util.runQuery(query);
        return DB_Util.getCellValue(1,2);
    }
}
