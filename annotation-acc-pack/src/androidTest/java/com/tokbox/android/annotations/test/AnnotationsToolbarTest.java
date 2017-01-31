package com.tokbox.android.annotations.test;

import com.tokbox.android.annotations.AnnotationsToolbar;
import com.tokbox.android.annotations.config.APIConfig;
import com.tokbox.android.annotations.testbase.TestBase;

import org.junit.Assert;

public class AnnotationsToolbarTest extends TestBase {

    protected void setUp() throws Exception {
        super.setUp(APIConfig.SESSION_ID, APIConfig.TOKEN, APIConfig.API_KEY);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testNewToolbarWithNullContext() {
        AnnotationsToolbar toolbar = null;
        try {

            toolbar = new AnnotationsToolbar(null);
            Assert.fail("Should have thrown an exception with null context.");

        }catch (Exception e){
            Assert.assertNull(toolbar);
        }
    }

    public void testNewToolbar() throws Exception {
        AnnotationsToolbar toolbar = null;

        toolbar = new AnnotationsToolbar(context);
        Assert.assertNotNull(toolbar);
    }

}
