package com.groovin101.refresher.string;

import com.groovin101.refresher.util.TestUtil;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;

/**
 * A lot of the Formatter functionality is covered in the StringFormatTest. Limiting the
 * scope of these tests to _direct_ Formatter usage
 *
 * @See https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html for details on format options
 */
public class FormatterTest {

    /**
     * Writer class implements Appendable, so can write directly to the Stream
     */
    @Test
    public void format_writeToAnAppendable() throws IOException {
        //not explicitly closed, as this will be handled by the Formatter that wraps it via the try-with-resources setup
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(TestUtil.addOutputDirectoryToFilename("write-to-appendable.txt")));
        try (Formatter f = new Formatter(bufferedWriter)) {
            f.format("Place an arg right.... [%s]. Dollars should look like this: [$%,.2f]", "here eh?", 1000000.0);
        }
    }

}
