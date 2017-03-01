package com.tokbox.android.annotations.test;

import android.graphics.Paint;
import android.widget.EditText;

import com.tokbox.android.annotations.Annotatable;
import com.tokbox.android.annotations.AnnotationsManager;
import com.tokbox.android.annotations.AnnotationsPath;
import com.tokbox.android.annotations.AnnotationsText;
import com.tokbox.android.annotations.AnnotationsView;
import com.tokbox.android.annotations.testbase.TestBase;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AnnotationsManagerTest extends TestBase {

    private AnnotationsManager annotationsManager;
    private AnnotationsView.Mode mode;
    private AnnotationsPath path;
    private AnnotationsText text;
    private Paint paint;
    private int canvasWidth = 1;
    private int canvasHeight = 1;
    private String cid;

    protected void setUp() throws Exception {
        super.setUp();

        annotationsManager = new AnnotationsManager();
        path = new AnnotationsPath();
        paint = new Paint();
        text = new AnnotationsText(new EditText(context), 1.0f, 1.0f);
        cid = "123456789-asdfghjkl";
        mode = AnnotationsView.Mode.Pen;
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAddAnnotatable() throws Exception {
        Annotatable annotatable = new Annotatable(mode, path, paint, cid);
        assertNotNull(annotatable);
        annotationsManager.addAnnotatable(annotatable);
        ArrayList<Annotatable> annotatableList = annotationsManager.getAnnotatableList();
        for(int i = 0; i < annotatableList.size(); i++){
            Assert.assertEquals(annotatableList.get(i), annotatable);
        }
    }

    public void testAddNullAnnotatable() throws Exception {
        int sizeList = annotationsManager.getAnnotatableList().size();
        try {
            annotationsManager.addAnnotatable(null);
            Assert.fail("Should have thrown an exception with null annotatable.");
        }catch (Exception e){
            //the annotatable cannot be added to the list if it is null
            Assert.assertEquals(sizeList, annotationsManager.getAnnotatableList().size());
        }


    }
}