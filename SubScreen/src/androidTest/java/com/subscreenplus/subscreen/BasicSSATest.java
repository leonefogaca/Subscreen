package com.subscreenplus.subscreen;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.subscreenplus.FileHelper;
import com.subscreenplus.Subtitles.ASSFormat;
import com.subscreenplus.TextBlock;
import com.subscreenplus.TimeBlock;

import java.util.ArrayList;

/**
 * Created by Nick on 4/21/2015.
 */
public class BasicSSATest extends ApplicationTestCase<Application> {
public BasicSSATest() {
            super(Application.class);
        }
        public void testSSA() {
            ArrayList<TextBlock> blocks = null;
            String path = System.getenv("EXTERNAL_STORAGE") + "/Subtitles/";
            ASSFormat ssa = new ASSFormat(null);
            FileHelper.EncodingWrapper wrapper = FileHelper.readFile(path + "ssa/testSSA.ass", null);
            blocks = ssa.readFile(wrapper.data, wrapper.encoding);
            assertEquals(blocks.get(1).getStartTime(), 2*60*1000 + 36*1000 + 40);
            TimeBlock lastBlock = (TimeBlock) blocks.get(blocks.size()-1);
            assertEquals(lastBlock.text, "End Text.");
        }
        public void testSSAParseTime()
        {
            ASSFormat ssa = new ASSFormat(null);
            long time = ssa.parseTimeStamp("12:34:56.78");
            assertEquals(time,12*60*60*1000+34*60*1000+56*1000+780);
        }
}
