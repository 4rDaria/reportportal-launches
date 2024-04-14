package com.epam.reportportal.cucumber;

import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.UserCreator;
import lombok.Getter;

import static com.epam.reportportal.cucumber.Context.LAUNCH_PAGE;
import static com.epam.reportportal.services.Login.login;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.baseUrlForCurrentEnv;

public class TestContext {
//    private final String BASE_URL = baseUrlForCurrentEnv();
    //private final String USER_ID = "9b5f49ab-eea9-45f4-9d66-bcf56a531b85";

    //private EndPoints endPoints;
    //private LaunchesPage launchesPage = login(testUser);
    @Getter
    private ScenarioContext scenarioContext;

    @Getter
    private String baseURl;

    public TestContext() {
        //endPoints = new EndPoints(BASE_URL);
        scenarioContext = new ScenarioContext();
        baseURl = baseUrlForCurrentEnv();
        //scenarioContext.setContext(LAUNCH_PAGE, launchesPage());
    }

//    public LaunchesPage launchesPage() {
//        User testUser = UserCreator.withCredentialsFromProperty();
//        return login(testUser);
//    }

}
