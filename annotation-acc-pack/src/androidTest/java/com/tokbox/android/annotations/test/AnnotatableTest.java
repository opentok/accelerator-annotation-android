package com.tokbox.android.annotations.test;

import android.graphics.Paint;
import android.widget.EditText;

import com.tokbox.android.annotations.Annotatable;
import com.tokbox.android.annotations.AnnotationsPath;
import com.tokbox.android.annotations.AnnotationsText;
import com.tokbox.android.annotations.AnnotationsView;
import com.tokbox.android.annotations.testbase.TestBase;

import junit.framework.Assert;

//import org.junit.Assert;

import java.lang.Integer;

public class AnnotatableTest extends TestBase{

    private AnnotationsView.Mode mode;
    private AnnotationsPath path;
    private AnnotationsText text;
    private Paint paint;
    private int canvasWidth = 1;
    private int canvasHeight = 1;
    private String cid;

    protected void setUp() throws Exception {
        super.setUp();

        path = new AnnotationsPath();
        paint = new Paint();
        text = new AnnotationsText(new EditText(context), 1.0f, 1.0f);
        cid = "123456789-asdfghjkl";
        mode = AnnotationsView.Mode.Pen;
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testNewAnnotatablePath() throws Exception {
        Annotatable annotatable = new Annotatable(mode, path, paint, cid);

        Assert.assertNotNull(annotatable);

        Assert.assertEquals(annotatable.getMode(), mode);
        Assert.assertEquals(annotatable.getPath(), path);
        Assert.assertEquals(annotatable.getPaint(), paint);
        Assert.assertEquals(annotatable.getCId(), cid);
    }

    public void testNewAnnotatablePathWithNullPath() {
        Annotatable annotatable = null;
        path = null;
        try {
            annotatable  = new Annotatable(mode, path, paint, cid);
            Assert.fail("Should have thrown an exception with null path.");
        }catch (Exception e){
            Assert.assertNull(annotatable);
        }
    }

    public void testNewAnnotatablePathWithNullCid() {
        Annotatable annotatable = null;
        try {
            annotatable  = new Annotatable(mode, path, paint, null);
            Assert.fail("Should have thrown an exception with null connectionId.");
        }catch (Exception e){
            Assert.assertNull(annotatable);
        }
    }

    public void testNewAnnotatablePathWithNullPaint() {
        Annotatable annotatable = null;
        try {
            annotatable  = new Annotatable(mode, path, null, cid);
            Assert.fail("Should have thrown an exception with null paint.");
        }catch (Exception e){
            Assert.assertNull(annotatable);
        }
    }

    public void testNewAnnotatablePathWithNullMode() {
        Annotatable annotatable = null;
        try {
            annotatable = new Annotatable(null, path, paint, cid);
            Assert.fail("Should have thrown an exception with null mode.");
        } catch (Exception e) {
            Assert.assertNull(annotatable);
        }
    }

    public void testNewAnnotatableText() throws Exception {
        mode = AnnotationsView.Mode.Text;
        Annotatable annotatable = new Annotatable(mode, text, paint, cid);

        Assert.assertNotNull(annotatable);

        Assert.assertEquals(annotatable.getMode(), mode);
        Assert.assertEquals(annotatable.getText(), text);
        Assert.assertEquals(annotatable.getPaint(), paint);
        Assert.assertEquals(annotatable.getCId(), cid);
    }

    public void testNewAnnotatableTextWithNullPath() {
        Annotatable annotatable = null;
        path = null;
        mode = AnnotationsView.Mode.Text;
        try {
            annotatable  = new Annotatable(mode, path, paint, cid);
            Assert.fail("Should have thrown an exception with null path.");
        }catch (Exception e){
            Assert.assertNull(annotatable);
        }
    }

    public void testNewAnnotatableTextWithNullCid() {
        Annotatable annotatable = null;
        mode = AnnotationsView.Mode.Text;
        try {
            annotatable  = new Annotatable(mode, text, paint, null);
            Assert.fail("Should have thrown an exception with null connectionId.");
        }catch (Exception e){
            Assert.assertNull(annotatable);
        }
    }

    public void testNewAnnotatableTextWithNullPaint() {
        Annotatable annotatable = null;
        mode = AnnotationsView.Mode.Text;
        try {
            annotatable  = new Annotatable(mode, text, null, cid);
            Assert.fail("Should have thrown an exception with null paint.");
        }catch (Exception e){
            Assert.assertNull(annotatable);
        }
    }

    public void testNewAnnotatableTextWithNullMode() {
        Annotatable annotatable = null;
        mode = AnnotationsView.Mode.Text;
        try {
            annotatable  = new Annotatable(null, text, paint, cid);
            Assert.fail("Should have thrown an exception with null mode.");
        }catch (Exception e){
            Assert.assertNull(annotatable);
        }
    }

    public void testSetMode() throws Exception {
        Annotatable annotatable = new Annotatable(mode, text, paint, cid);
        annotatable.setMode(AnnotationsView.Mode.Capture);
    }

    public void testSetModeNull() throws Exception {
        Annotatable annotatable = null;
        try {
            annotatable  = new Annotatable(mode, text, paint, cid);
            annotatable.setMode(null);
            Assert.fail("Should have thrown an exception when mode is null");
        }catch (Exception e){
            Assert.assertNotNull(annotatable.getMode());
        }
    }
}
