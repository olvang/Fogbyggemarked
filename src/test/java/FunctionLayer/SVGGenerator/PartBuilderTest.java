package FunctionLayer.SVGGenerator;

import Components.DepthComponent;
import Components.HeightComponent;
import Components.InclineComponent;
import Components.WidthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import org.junit.Test;
import testDataSetup.TestDataSetup;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PartBuilderTest {

    //Use this file for testing out partBuilder methods
    //This method will just print the result. Preview it in the test.svg file.
    //Uncomment @Test before running, don't forget to comment it again.

    //@Test
    public void testMethod() {
        SVG svg = new SVG(1000, 1000, "0,0,1000,1000", 0,0);
        PartBuilder.drawRems(svg, 0,0, 800, 600);

        writeToFile(svg.toString());
    }

    private void writeToFile(String content) {
        try {
            String fileName = "src/test/java/FunctionLayer/SVGGenerator/test.svg";
            File file = new File(fileName);
            file.delete();
            if(file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write(content);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
