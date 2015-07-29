package com.wandrell.velocity.tool.testing.test.unit.html;

import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HTMLUtil;

import junit.framework.Assert;

public final class TestFixCodeBlock {

    /**
     * Instance of the utils class being tested.
     */
    private final HTMLUtil util = new HTMLUtil();

    /**
     * Default constructor.
     */
    public TestFixCodeBlock(){
        super();
    }
    
    @Test
    public final void testFixCodeBlock(){
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<div class=\"source\"><pre>Some code</pre></div>";

        result = util.fixCodeBlock(html);

        htmlExpected = "<pre><code>Some code</code></pre>";
        
        System.out.println(result);

        Assert.assertEquals(htmlExpected, htmlExpected);
    }

}
