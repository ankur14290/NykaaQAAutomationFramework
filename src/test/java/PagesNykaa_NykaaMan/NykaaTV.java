package PagesNykaa_NykaaMan;

import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Abhishek Anand on 2nd August 2018.
 */
public class NykaaTV extends AppAction {


    public NykaaTV(AndroidDriver driver) {
        this.driver = driver;
    }

    /******************************************Locators*****************************************************************************/
    Locator userProfile() {
        return new Locator(By.xpath("//android.widget.RelativeLayout[@index='1']//android.widget.RelativeLayout//android.widget.ImageView"), "UserProfile");
    }

    Locator nykaaTVLabel() {
        return new Locator(By.xpath("//android.widget.TextView[@text='Nykaa TV']"), "nykaa tv");
    }

    Locator nykaaHome() {
        return new Locator(By.xpath("//android.widget.TextView[@text='HOME']"), "Home");
    }

    Locator nykaaHomeRecentlyAdded() {
        return new Locator(By.xpath("//android.widget.TextView[@text='RECENTLY ADDED']"), "Recently Added");
    }

    Locator nykaaTrendingNow() {
        return new Locator(By.xpath("//android.widget.TextView[@text='TRENDING NOW']"), "Trending Now");
    }

    Locator trendingNowField() {
        return new Locator(By.xpath("//android.widget.TextView[@text='TRENDING NOW']"), "Trending Now Field");
    }

    Locator topPicks() {
        return new Locator(By.xpath("//android.widget.TextView[@text='TOP PICKS']"), "Top Picks");
    }

    Locator beautyTutorials() {
        return new Locator(By.xpath("//android.widget.TextView[@text='BEAUTY TUTORIALS']"), "Beauty Tutorials");
    }

    Locator makeUP() {
        return new Locator(By.xpath("//android.widget.TextView[@text='MAKEUP']"), "MakeUP");
    }

    Locator skinAndHair() {
        return new Locator(By.xpath("//android.widget.TextView[@text='SKIN AND HAIR']"), "Skin and Hair");
    }

    Locator expertMaterClass() {
        return new Locator(By.xpath("//android.widget.TextView[@text='EXPERT MASTERCLASS']"), "Expert MasterClass");
    }

    Locator expertMasterClassPage() {
        return new Locator(By.xpath("//android.widget.RelativeLayout"), "Expert Master Page");
    }

    Locator nykaaHauls() {
        return new Locator(By.xpath("//android.widget.TextView[@text='NYKAA HAULS']"), "Nykaa Hauls");
    }

    Locator nykaaSpecials() {
        return new Locator(By.xpath("//android.widget.TextView[@text='NYKAA SPECIALS']"), "Nykaa Specials");
    }

    /******************************************Services*****************************************************************************/
    public void getUserProfile() throws IllegalAccessException, InstantiationException {
        click(userProfile());
    }

    public boolean getNykaaTVLabel() throws IllegalAccessException, InstantiationException, InterruptedException {
        bringElementIntoViewDown(nykaaTVLabel(), 1);
        String label = getText(nykaaTVLabel());
        if (label.equals("Nykaa TV")) {
            //  click(nykaaTVLabel());
            return true;
        } else {
            return false;
        }
    }

    public void clickNykaaTV() throws IllegalAccessException, InstantiationException {
        click(nykaaTVLabel());
    }

    public boolean displayElement() throws IllegalAccessException, InstantiationException, InterruptedException {
        isDisplayed(nykaaHome());
        isDisplayed(nykaaHomeRecentlyAdded());
        Thread.sleep(5000);
        isDisplayed(nykaaTrendingNow());
        click(nykaaTrendingNow());
        Thread.sleep(5000);
        isDisplayed(trendingNowField());
      /*  isDisplayed(topPicks());
        click(topPicks());
        Thread.sleep(5000);
        isDisplayed(topPicks());*/
        isDisplayed(beautyTutorials());
        click(beautyTutorials());
        Thread.sleep(5000);
        isDisplayed(beautyTutorials());
        isDisplayed(makeUP());
        click(makeUP());
        Thread.sleep(2000);
        isDisplayed(makeUP());
        isDisplayed(skinAndHair());
        click(skinAndHair());
        Thread.sleep(2000);
        isDisplayed(skinAndHair());
        isDisplayed(expertMaterClass());
        click(expertMaterClass());
        Thread.sleep(2000);
        isDisplayed(expertMasterClassPage());
        isDisplayed(nykaaHauls());
        click(nykaaHauls());
        Thread.sleep(2000);
        isDisplayed(nykaaHauls());
        isDisplayed(nykaaSpecials());
        click(nykaaSpecials());
        isDisplayed(nykaaSpecials());

        return true;
    }

    /* public ArrayList<String> dispElement () throws IllegalAccessException, InstantiationException {
         Locator[] arr = {nykaaHome(), nykaaTrendingNow(), beautyTutorials(), makeUP(), skinAndHair(), expertMaterClass(), nykaaHauls(), nykaaSpecials()};

         ArrayList<Locator> nykaaTVArr = new ArrayList<Locator>(Arrays.asList(arr));
         nykaaTVArr.add("Home");
         nykaaTVArr.add("Trending Now");
         nykaaTVArr.add("Beauty Tutorials");
         nykaaTVArr.add("MakeUP");
         nykaaTVArr.add("Skin and Hair");
         nykaaTVArr.add("Expert MasterClass");
         nykaaTVArr.add("Nykaa Hauls");
         nykaaTVArr.add("Nykaa Specials");
         ArrayList<String> list = new ArrayList<>();
         for (int i = 0; i < arr.length; i++) {
             if (!isDisplayed(arr[i])) {
                 list.add(arr[i].getName());
             }
         }

         return list;
     }*/
    public ArrayList<String> CheckElement() throws Exception {
        Locator[] arr = {nykaaTrendingNow(), beautyTutorials(), makeUP(), skinAndHair(), expertMaterClass(), nykaaHauls(), nykaaSpecials()};
        ArrayList<Locator> nykaaTVList = new ArrayList<Locator>(Arrays.asList(arr));
        ArrayList<String> nameofLocator = new ArrayList<String>();


        for (Locator loct : nykaaTVList) {
            click(loct);
            if (!isDisplayed(loct)) {
                nameofLocator.add(loct.getName());
            }

        }
        return nameofLocator;

    }
}

