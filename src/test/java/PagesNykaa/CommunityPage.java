package PagesNykaa;

import DataNykaa.NykaaNetworkData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommunityPage extends BrowserAction {

    public CommunityPage(WebDriver driver) {
        this.driver = driver;
    }

    Locator getStarted() {
        return new Locator(By.xpath("//span[contains(text(),'Get Started')]"), "get started button");
    }


    Locator topicselection() {
        return new Locator(By.xpath("//div[@class='topic-group'][1]//label[1]//div"), "firstTopic");

    }

    Locator proceedButton() {
        return new Locator(By.xpath("//a[@class=\"next-button community-font\"]"), "proceed");
    }

    Locator question() {
        return new Locator(By.xpath("//input[@class='search-query form-control ui-autocomplete-input']"), "question");
    }

    Locator postButton_popup() {
        return new Locator(By.xpath("//div[@class='btn-community btn-post']"), "Post It Button");
    }

    Locator postButton_Confirmation() {
        return new Locator(By.xpath("//button[@class='btn-community btn-post']"), "Post It Button");
    }

    Locator postReply() {
        return new Locator(By.xpath("//*[@class='btn btn-community btn-post']"), "Post Reply Button");
    }

    Locator selectTopic() {
        return new Locator(By.xpath("//div[@class='dropdown-wrapper']"), "Topic Selection List");
    }

    Locator questionDescription() {
        return new Locator(By.xpath("//div[@class='redactor_description redactor_redactor redactor_editor']"), "Desciption");
    }

    Locator questionTopic() {
        return new Locator(By.xpath("//div[@class='dropdown-wrapper']//div[@class='option']"), "Question Related Topics");
    }

    Locator relatedTags() {
        return new Locator((By.xpath("//label[@class='related_topics_tags_labels']")), "relatedTags");
    }

    Locator questionHeading() {
        return new Locator(By.xpath("//div[not(@class='card type-answer pinned-post')]/h4[@class='card-heading']//a"), "questioHeader");
    }

    Locator questionOnHomePage() {
        return new Locator(By.xpath("//div[@class='card type-question '] | //div[@class='card type-answer ']"), "Question Parent");
    }

    Locator exploreButton() {
        return new Locator(By.xpath("//a[@href='/nykaa-network/explore.html']"), "explore");
    }

    Locator myAccount() {
        return new Locator(By.xpath("//div[@class=\"user-icon\"]"), "User");
    }

    Locator followButton() {
        return new Locator(By.xpath("//div[@class='explore-tile'][2]//div[@class='follow-btn'][1]"), "follow button");
    }

    Locator followingButton() {
        return new Locator(By.xpath("//div[@class='explore-tile'][1]//div[@class='following-btn '][1]"), "following button");
    }

    Locator HomeButton() {
        return new Locator(By.xpath("//a[@href='/nykaa-network/questions/home.html']//span[text()='   HOME']"), "homepage");
    }

    Locator loaderAfterPosting(){
        return new Locator(By.xpath("//i[@class='spinner icon-spinner icon-spin icon-2x']"),"Post Question Loader");
    }

    public boolean isgetStaryedWindowopened() {
        return (waitUntilDisplayed(getStarted(), 5));
    }

    public void getStartedWindow() throws IllegalAccessException, InstantiationException {
        click(getStarted());
    }

    public String topicSelectionWindow() throws IllegalAccessException, InstantiationException, InterruptedException {
        click(topicselection());
        String topic = getText(topicselection());
        click(proceedButton());
        return topic;
    }

    public boolean isTopicSelected(String topic) throws IllegalAccessException {
        List<WebElement> questions = getWebElements(questionOnHomePage());
        for (WebElement question : questions){
            Locator questionHeading  = new Locator(question, By.xpath("//div[@class='desc']//strong//a"),"Question Type");
            if(topic.toLowerCase().contains(getText(questionHeading).toLowerCase())){
                return true;
            }else {
                continue;
            }
        }
        return false;
    }

    public String postAQuestion(NykaaNetworkData nykaaNetworkData) throws Throwable {
        EnterValue(question(), nykaaNetworkData.getQuestion());
     //   bringElementIntoView(postButton_popup());
        click(postButton_popup());
        click(selectTopic());
        List<WebElement> radioButtonToppic = getWebElements(questionTopic());
        for (WebElement iterator : radioButtonToppic) {
            Locator radio = new Locator(By.xpath(getAbsoluteXPath(iterator)), "Topic Name");
            click(radio);
            break;
        }
        if(waitUntilDisplayed(relatedTags(),2)) {
            List<WebElement> tags = getWebElements(relatedTags());
            for (WebElement iterator : tags) {
                Locator tag = new Locator(By.xpath(getAbsoluteXPath(iterator)), "tag");
                click(tag);
                break;
            }
        }
        click(postButton_Confirmation());
        waitUntilDisappear(loaderAfterPosting());
        return nykaaNetworkData.getQuestion();
    }

    public boolean isQuestionPosted(String question1) throws Throwable {
        bringElementIntoView(questionHeading());
        waitUntilDisplayed(questionHeading(), 9);
        String question = getText(questionHeading());
        System.out.print(question);
        System.out.print(question1);
        if (question.contains(question1)) {
            return true;
        } else {
            return false;
        }
    }

    public String replyToTheQuestion(NykaaNetworkData nykaaNetworkData) throws IllegalAccessException, InstantiationException {
        waitUntilDisplayed(questionOnHomePage(), 5);
        List<WebElement> reply = getWebElements(questionOnHomePage());
        for (WebElement iterator : reply) {
            Locator replyButton = new Locator(iterator, By.xpath("//a[@class='capitalize']"), "Reply Button");
            click(replyButton);
            Locator repliedText = new Locator(iterator, By.xpath("//div[@class='redactor_form-control redactor_redactor redactor_editor']"), "Reply Text");
            EnterValue(repliedText, nykaaNetworkData.getReply());
            Locator post = new Locator(iterator, postReply().getBy(), "Post Reply Discription");
            click(post);
            break;
        }
        return nykaaNetworkData.getReply();
    }

    public boolean isreplyPosted(String repliedText) throws IllegalAccessException {
        waitForPagetoLoad();
        List<WebElement> reply = getWebElements(questionOnHomePage());
        for(WebElement iterator : reply){
            Locator repliedTextLocator = new Locator(iterator, By.xpath("//div[@class='content-wrapper']//div[@class='card-content expand']//p"),"Replied Text");
            String replyFromUI = getText(repliedTextLocator);
            System.out.print(replyFromUI);
            System.out.print(repliedText);
            if(replyFromUI.equals(repliedText)){
                return true;
            }
        }
        return false;
    }

    public void explore() throws IllegalAccessException, InstantiationException, Throwable {
        bringElementIntoView(exploreButton());
        waitUntilDisplayed(exploreButton(), 10);
        click(exploreButton());
        waitUntilDisplayed(followButton(), 5);
        click(followButton());
        waitUntilDisplayed(followingButton(), 5);
        click(followingButton());
        waitUntilDisplayed(HomeButton(), 5);
        click(HomeButton());

    }

    public void userupdate() throws IllegalAccessException, InstantiationException {
        waitUntilDisplayed(myAccount(),5);
        click(myAccount());
        waitUntilDisplayed(HomeButton(),5);
        click(HomeButton());

    }
}


