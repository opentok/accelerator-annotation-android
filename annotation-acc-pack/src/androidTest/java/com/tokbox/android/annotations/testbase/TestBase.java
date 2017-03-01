package com.tokbox.android.annotations.testbase;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.tokbox.android.otsdkwrapper.wrapper.OTAcceleratorSession;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class TestBase extends AndroidTestCase {

    public static String LOGTAG = TestBase.class.getName();

    protected final static int WAIT_TIME = 75;

    protected Context context;
    protected String apiKey;
    protected String apiSecret;
    protected String sessionId;
    protected String token;

    protected OTAcceleratorSession session;

    protected AtomicBoolean sessionConnected = new AtomicBoolean();
    protected AtomicBoolean sessionDisconnected = new AtomicBoolean();
    protected AtomicBoolean sessionError = new AtomicBoolean();
    protected AtomicBoolean publisherStreamCreated = new AtomicBoolean();
    protected AtomicBoolean publisherError = new AtomicBoolean();
    protected AtomicBoolean publisherStreamDestroyed = new AtomicBoolean();

    protected CountDownLatch sessionConnectedLock = new CountDownLatch(1);
    protected CountDownLatch sessionDisconnectedLock = new CountDownLatch(1);
    protected CountDownLatch sessionErrorLock = new CountDownLatch(1);
    protected CountDownLatch publisherStreamCreatedLock = new CountDownLatch(1);
    protected CountDownLatch publisherStreamDestroyedLock = new CountDownLatch(1);
    protected CountDownLatch publisherErrorLock = new CountDownLatch(1);

    protected Stream publisherStreamCreatedStream = null;

    protected OpentokError publisherLastError = null;

    protected OpentokError sessionLastError = null;

    protected Session.SessionListener sessionListener = new Session.SessionListener() {


        @Override
        public void onStreamReceived(Session session, Stream stream) {
            Log.d(LOGTAG,"Session - onStreamReceived");
        }

        @Override
        public void onError(Session session, OpentokError error) {
            Log.d(LOGTAG,"Session - onError");
        }

        @Override
        public void onStreamDropped(Session session, Stream stream) {
            Log.d(LOGTAG,"Session - onStreamDropped");
        }


        @Override
        public void onDisconnected(Session session) {
            Log.d(LOGTAG,"Session - onDisconnected");
            sessionDisconnected.set(true);
            sessionDisconnectedLock.countDown();
            session = null;
        }

        @Override
        public void onConnected(Session session) {
            Log.d(LOGTAG,"Session - onConnected");
            sessionConnected.set(true);
            sessionConnectedLock.countDown();
        }
    };

    protected Publisher.PublisherListener publisherListener = new Publisher.PublisherListener() {

        @Override
        public void onStreamDestroyed(PublisherKit publisher, Stream stream) {
            Log.d(LOGTAG,"Publisher - onStreamDestroyed");
            publisherStreamDestroyed.set(true);
            publisherStreamDestroyedLock.countDown();
        }

        @Override
        public void onStreamCreated(PublisherKit publisher, Stream stream) {
            Log.d(LOGTAG,"Publisher - onStreamCreated");
            publisherStreamCreatedStream = stream;
            publisherStreamCreated.set(true);
            publisherStreamCreatedLock.countDown();
        }

        @Override
        public void onError(PublisherKit publisher, OpentokError error) {
            Log.d(LOGTAG,"Publisher - onError");
            publisherLastError = error;
            publisherError.set(true);
            publisherErrorLock.countDown();
        }

    };

    protected void setUp() throws Exception {
        super.setUp();

        this.context = getContext();
    }

    protected void setUp(String key, String secret) throws Exception {
        super.setUp();

        this.context = getContext();
        this.apiKey = key;
        this.apiSecret = secret;

    }
    protected void setUp(String sessionId, String token, String key) throws Exception {
        this.context = getContext();
        this.apiKey = key;
        this.token = token;
        this.sessionId = sessionId;
    }

    protected void tearDown() throws Exception {
        super.tearDown();

        sessionConnectedLock = new CountDownLatch(1);
        sessionErrorLock = new CountDownLatch(1);
        sessionDisconnectedLock = new CountDownLatch(1);
        sessionConnected.set(false);
        sessionError.set(false);
        sessionDisconnected.set(false);


        publisherStreamCreated.set(false);
        publisherError.set(false);
        publisherStreamDestroyed.set(false);

        publisherStreamCreatedLock = new CountDownLatch(1);
        publisherStreamDestroyedLock = new CountDownLatch(1);
        publisherErrorLock = new CountDownLatch(1);

        this.session = null;
        this.publisherStreamCreatedStream = null;
        this.publisherLastError = null;
        this.sessionLastError = null;
    }

    protected void waitSessionConnected() throws InterruptedException {
        sessionConnectedLock.await(WAIT_TIME, TimeUnit.SECONDS);
        assertTrue("session failed to connect", sessionConnected.get());
        assertFalse(sessionError.get());
        assertNull(sessionLastError);
    }

    protected void waitSessionDisconnected() throws InterruptedException {
        sessionDisconnectedLock.await(WAIT_TIME, TimeUnit.SECONDS);
        assertTrue("session failed to disconnect", sessionDisconnected.get());
        if (!sessionError.get()){
            assertFalse(sessionError.get());
            assertNull(sessionLastError);
        }
    }

    protected void waitPublisherStreamCreated() throws InterruptedException {
        publisherStreamCreatedLock.await(WAIT_TIME, TimeUnit.SECONDS);
        assertTrue(publisherStreamCreated.get());
        assertFalse(publisherError.get());
        assertNull(sessionLastError);
    }
}
