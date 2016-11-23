package rest.client;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {

    private ByteArrayOutputStream output;

    @Before
    public void init() {
        this.output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    /**
     * Happy Path
     */
    @Test
    public void printsFetchedData() {
        Application.main(new String[]{"movie", "Godzilla"});
        assertTrue(this.output.toString().contains("SUCCESS"));
    }

    /**
     * Unhappy Paths
     */
    @Test
    public void errorViewPrintedWhenEmptyValuesAreProvided() {
        Application.main(new String[]{"", ""});
        assertTrue(this.output.toString().contains("FAILURE"));
    }

    @Test
    public void errorViewPrintedWhenInvalidValuesAreProvided() {
        Application.main(new String[]{"movies", "musics"});
        assertTrue(this.output.toString().contains("FAILURE"));
    }

    @Test
    public void errorViewPrintedWhenNullValuesAreProvided() {
        Application.main(new String[]{null, null});
        assertTrue(this.output.toString().contains("FAILURE"));
    }

    @Test
    public void errorViewPrintedWhenValuesAreOrderedIncorrectly() {
        Application.main(new String[]{"Godzilla", "movie"});
        assertTrue(this.output.toString().contains("FAILURE"));
    }

    @Test
    public void errorViewSkippedPrintedWhenExcessValuesAreProvided() {
        Application.main(new String[]{"movie", "Godzilla", "movie", "movie", "movie", "movie"});
        assertTrue(this.output.toString().contains("SUCCESS"));
    }
}
