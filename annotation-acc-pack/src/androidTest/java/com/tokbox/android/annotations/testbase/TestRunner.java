package com.tokbox.android.annotations.testbase;


import android.os.Bundle;

import com.tokbox.android.annotations.config.APIConfig;
import com.zutubi.android.junitreport.JUnitReportTestRunner;

import junit.framework.Assert;

public class TestRunner extends JUnitReportTestRunner {
    private final String FLAG_API_KEY = "api_key";
    private final String FLAG_API_SECRET = "api_secret";
    private final String FLAG_API_SESSION_ID = "api_session_id";
    private final String FLAG_API_TOKEN = "api_token";

    @Override
    public void onCreate(Bundle arguments) {
        APIConfig.API_KEY      = arguments.getString(FLAG_API_KEY, APIConfig.API_KEY);
        APIConfig.API_SECRET   = arguments.getString(FLAG_API_SECRET, APIConfig.API_SECRET);
        APIConfig.SESSION_ID     = arguments.getString(FLAG_API_SESSION_ID, APIConfig.SESSION_ID);
        APIConfig.TOKEN  = arguments.getString(FLAG_API_TOKEN, APIConfig.TOKEN);
        super.onCreate(arguments);
    }
}