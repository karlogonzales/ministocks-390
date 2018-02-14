/*
 The MIT License

 Copyright (c) 2013 Nitesh Patel http://niteshpatel.github.io/ministocks

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 */

package nitezh.ministock.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import nitezh.ministock.Storage;

@RunWith(RobolectricTestRunner.class)
public class AndroidWidgetTests {

    private Widget widget;

    @Before
    public void setUp() {

        int WIDGET_ID = 1;
        int WIDGET_SIZE = 0;

        widget = new AndroidWidgetRepository(RuntimeEnvironment.application)
                .addWidget(WIDGET_ID, WIDGET_SIZE);
    }

    @Test
    public void testShouldUpdateOnRightTouchReturnsFalseByDefault() {
        // Act and Assert
        assertEquals(false, widget.shouldUpdateOnRightTouch());
    }

    @Test
    public void testShouldUpdateOnRightTouchReturnsTrueIfSet() {
        // Arrange
        Storage storage = widget.getStorage();
        storage.putBoolean("update_from_widget", true);
        storage.apply();

        // Act and Assert
        assertEquals(true, widget.shouldUpdateOnRightTouch());
    }

    @Test
    public void testGetColourStyle(){

        Storage storage = widget.getStorage();

        storage.putString("colour_style", "black");
        storage.apply();
        assertEquals("BLACK", widget.getColourStyle());

        storage.putString("colour_style", "white");
        storage.apply();
        assertEquals("WHITE", widget.getColourStyle());

        storage.putString("colour_style", "red");
        storage.apply();
        assertEquals("RED", widget.getColourStyle());

        storage.putString("colour_style", "green");
        storage.apply();
        assertEquals("GREEN", widget.getColourStyle());

        storage.putString("colour_style", "yellow");
        storage.apply();
        assertEquals("YELLOW", widget.getColourStyle());

        storage.putString("colour_style", "cyan");
        storage.apply();
        assertEquals("CYAN", widget.getColourStyle());

        storage.putString("colour_style", "gray");
        storage.apply();
        assertEquals("GRAY", widget.getColourStyle());
    }

    @Test
    public void testGetTextStyle() {

        Storage storage = widget.getStorage();

        storage.putString("text_style", "normal");
        storage.apply();
        assertEquals("NORMAL", widget.getTextStyle());

        storage.putString("text_style", "bold");
        storage.apply();
        assertEquals("BOLD", widget.getTextStyle());

        storage.putString("text_style", "italic");
        storage.apply();
        assertEquals("ITALIC", widget.getTextStyle());

        storage.putString("text_style", "bold_italic");
        storage.apply();
        assertEquals("BOLD_ITALIC", widget.getTextStyle());
    }
}
