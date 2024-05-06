package com.epam.reportportal.api.helper;

import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.baseUrlForCurrentEnv;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.projectNameForCurrentEnv;
import static java.lang.String.format;

public class Routes {

    private static final String PROJECT = projectNameForCurrentEnv();
    private static final String API_URL = format("/api/v1/%s/launch", PROJECT);
    private static final String API_BASE_LAUNCH_URL = baseUrlForCurrentEnv() + API_URL;
    public static String get_all_launches_url = API_BASE_LAUNCH_URL;
    public static String delete_launches_url = API_BASE_LAUNCH_URL + "/%s";
    public static String launches_compare_url = API_BASE_LAUNCH_URL + "/compare";
    public static String launches_compare_incorrect_url = API_BASE_LAUNCH_URL + "/compares";
    public static String merge_launches_url = API_BASE_LAUNCH_URL + "/merge";
    public static String update_launch_url = API_BASE_LAUNCH_URL + "/%s/update";
    public static String demo_data_generation_url = baseUrlForCurrentEnv() + format("/api/v1/demo/%s/generate", PROJECT);
}
