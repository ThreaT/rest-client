package rest.client.view;

import java.awt.Point;
import org.apache.commons.lang.StringUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ResultsViewTest {

    private Object expectedObject;
    private String expectedJson;
    private ResultsView resultsView;

    @Before
    public void init() {
        this.expectedObject = new Point(10, 5);
        this.expectedJson
                = "SUCCESS\n"
                + "\n"
                + "{\n"
                + "  \"x\" : 10.0,\n"
                + "  \"y\" : 5.0\n"
                + "}\n"
                + "\n"
                + "";
        this.resultsView = new ResultsView(this.expectedObject);
    }

    /*
     * Happy Path
     */
    @Test
    public void testPrint() {
        assertEquals(this.resultsView.print(true), this.expectedJson);
    }

    /*
     * Unhappy Paths
     */
    @Test
    public void shouldPrintBlankWhenInstantiatedWithNoObjects() {
        this.resultsView = new ResultsView();
        assertEquals(this.resultsView.print(true), "");
    }

    @Test
    public void printShouldSkipNullObjects() {
        Point point1 = new Point(1, 0);
        Point point2 = null;
        Point point3 = new Point(2, 1);
        Point point4 = null;
        Point point5 = new Point(3, 3);
        this.resultsView = new ResultsView(point1, point2, point3, point4, point5);
        assertEquals(StringUtils.countMatches(this.resultsView.print(true), "\n\n"), 4);
    }

    @Test
    public void printShouldOutputSameNumberOfDuplicateObjectsPassed() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(1, 1);
        Point point3 = new Point(1, 1);
        this.resultsView = new ResultsView(point1, point2, point3);
        assertTrue(StringUtils.countMatches(this.resultsView.print(true), "\n\n") == 4);
    }

}
