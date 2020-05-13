package FunctionLayer.SVGGenerator;

import org.junit.Test;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PartBuilderCarportTest {

    //Use this file for testing out partBuilder methods
    //This method will just print the result. Preview it in the test.svg file.
    //Uncomment @Test before running, don't forget to comment it again.

    @Test
    public void testMethod() {
        SVG svg = new SVG(1800, 1200, "0,0,1800,1800", 0,0);
        PartBuilderCarport.drawOuterBox(svg, 100,100, 900, 1400);
        PartBuilderCarport.drawRems(svg, 100,100, 900, 1400);
        PartBuilderCarport.drawSper(svg, 100,100, 900, 1400);
        //PartBuilderCarport.drawPerforatedBandWithoutShed(svg, 100,100, 900, 1400);
        PartBuilderCarport.drawDepthArrow(svg, 100,100, 900, 1400);
        PartBuilderCarport.drawInnerWidthArrow(svg, 100,100, 900, 1400);
        PartBuilderCarport.drawOuterWidthArrow(svg, 100,100, 900, 1400);
        PartBuilderCarport.drawSperSpaceArrows(svg, 100,100, 900, 1400);
        PartBuilderShed.drawShed(svg, 100,100, 900, 1400,450,1200);
        PartBuilderShed.drawPostsWithShed(svg, 100,100, 900, 1400,450, 1200);
        PartBuilderShed.drawPerforatedBandWithShed(svg, 100, 100, 900, 1400, 450, 1200);


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
