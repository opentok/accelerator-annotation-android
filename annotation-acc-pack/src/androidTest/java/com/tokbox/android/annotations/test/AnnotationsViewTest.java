package com.tokbox.android.annotations.test;



import com.opentok.android.Publisher;
import com.opentok.android.Subscriber;
import com.tokbox.android.accpack.AccPackSession;
import com.tokbox.android.annotations.AnnotationsToolbar;
import com.tokbox.android.annotations.AnnotationsView;
import com.tokbox.android.annotations.config.APIConfig;
import com.tokbox.android.annotations.testbase.TestBase;
import com.tokbox.android.annotations.utils.AnnotationsVideoRenderer;

import org.junit.Assert;

public class AnnotationsViewTest extends TestBase {


    protected void setUp() throws Exception {
        super.setUp(APIConfig.SESSION_ID, APIConfig.TOKEN, APIConfig.API_KEY);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testNewAnnotationsView() throws Exception {
        boolean isScreensharing = true;

        try{
            session = new AccPackSession(context, apiKey, sessionId);
            session.setSessionListener(sessionListener);
            session.connect(token);

            waitSessionConnected();

            AnnotationsView annotationsView = new AnnotationsView(mContext, session, apiKey, isScreensharing);
            Assert.assertNotNull(annotationsView);
        } finally {
            session.disconnect();
            waitSessionDisconnected();
        }
    }

    public void testNewAnnotationsViewWithSessionNull() {
        boolean isScreensharing = true;
        AnnotationsView annotationsView = null;

        try {
            annotationsView = new AnnotationsView(mContext, null, apiKey, isScreensharing);
            Assert.fail("Should have thrown an exception with null AccPackSession.");

        } catch (Exception e) {
            Assert.assertNull(annotationsView);
        }
    }

    public void testNewAnnotationsViewPublisher() throws Exception {
        boolean isScreensharing = true;

        try{
            session = new AccPackSession(context, apiKey, sessionId);
            session.setSessionListener(sessionListener);
            session.connect(token);

            waitSessionConnected();

            Publisher publisher = new Publisher(context);

            AnnotationsView annotationsView = new AnnotationsView(mContext, session, apiKey, isScreensharing);
            Assert.assertNotNull(annotationsView);
        } finally {
            session.disconnect();
            waitSessionDisconnected();
        }
    }

    public void testNewAnnotationsViewPublisherWithSessionNull() throws Exception{
        boolean isScreensharing = true;

        AnnotationsView annotationsView = null;
        Publisher publisher = new Publisher(context);

        try {
            annotationsView = new AnnotationsView(mContext, null, apiKey, isScreensharing);
            Assert.fail("Should have thrown an exception with null AccPackSession.");
        }
        catch(Exception e){
            Assert.assertNull(annotationsView);
        }
    }

    public void testNewAnnotationsViewWithSessionNotConnected() throws Exception{
        boolean isScreensharing = true;
        AnnotationsView annotationsView = null;

        try {
            session = new AccPackSession(context, apiKey, sessionId);
            session.setSessionListener(sessionListener);

            annotationsView = new AnnotationsView(mContext, session, apiKey, isScreensharing);
            Assert.fail("Should have thrown an exception with disconnected AccPackSession.");
        }
        catch(Exception e){
            Assert.assertNull(annotationsView);
        }
    }

    public void testAttachAnnotationsToolbar() throws Exception {
        AnnotationsView annotationsView = null;
        boolean isScreensharing = true;

        try{
            session = new AccPackSession(context, apiKey, sessionId);
            session.setSessionListener(sessionListener);
            session.connect(token);

            waitSessionConnected();

            annotationsView = new AnnotationsView(mContext, session, apiKey, isScreensharing);
            Assert.assertNotNull(annotationsView);

            AnnotationsToolbar toolbar = new AnnotationsToolbar(context);
            annotationsView.attachToolbar(toolbar);

            Assert.assertNotNull(annotationsView.getToolbar());
        } finally {
            session.disconnect();
            waitSessionDisconnected();
        }
    }

    public void testAttachNullAnnotationsToolbar() throws Exception{
        AnnotationsView annotationsView = null;
        boolean isScreensharing = true;

        try {
            session = new AccPackSession(context, apiKey, sessionId);
            session.setSessionListener(sessionListener);
            session.connect(token);

            waitSessionConnected();

            annotationsView = new AnnotationsView(mContext, session, apiKey, isScreensharing);
            Assert.assertNotNull(annotationsView);

            annotationsView.attachToolbar(null);
            Assert.fail("Should have thrown an exception with null AnnotationsToolbar");

        }catch (Exception e){
            Assert.assertNull(annotationsView.getToolbar());
        } finally {
            session.disconnect();
            waitSessionDisconnected();
        }
    }

    public void testSetVideoRenderer() throws Exception {
        AnnotationsView annotationsView = null;
        boolean isScreensharing = true;

        try{
            session = new AccPackSession(context, apiKey, sessionId);
            session.setSessionListener(sessionListener);
            session.connect(token);

            waitSessionConnected();

            annotationsView = new AnnotationsView(mContext, session, apiKey, isScreensharing);
            Assert.assertNotNull(annotationsView);

            AnnotationsVideoRenderer videoRenderer = new AnnotationsVideoRenderer(context);
            annotationsView.setVideoRenderer(videoRenderer);

            Assert.assertNotNull(annotationsView.getVideoRenderer());
        } finally {
            session.disconnect();
            waitSessionDisconnected();
        }
    }

    public void testSetNullVideoRenderer() throws Exception{
        AnnotationsView annotationsView = null;
        boolean isScreensharing = true;

        try {
            session = new AccPackSession(context, apiKey, sessionId);
            session.setSessionListener(sessionListener);
            session.connect(token);

            waitSessionConnected();

            annotationsView = new AnnotationsView(mContext, session, apiKey, isScreensharing);
            Assert.assertNotNull(annotationsView);

            annotationsView.setVideoRenderer(null);
            Assert.fail("Should have thrown an exception with null VideoRenderer");

        }catch (Exception e){
            Assert.assertNull(annotationsView.getVideoRenderer());
        } finally {
            session.disconnect();
            waitSessionDisconnected();
        }
    }


}
