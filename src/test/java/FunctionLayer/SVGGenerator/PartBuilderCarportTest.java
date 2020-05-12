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
        SVG svg = new SVG(1200, 800, "0,0,1000,800", 0,0);
        PartBuilderCarport.drawOuterBox(svg, 100,100, 800, 600);
        PartBuilderCarport.drawRems(svg, 100,100, 800, 600);
        PartBuilderCarport.drawSper(svg, 100,100, 800, 600);
        //PartBuilderCarport.drawPerforatedBandWithoutShed(svg, 100,100, 800, 600);
        PartBuilderCarport.drawDepthArrow(svg, 100,100, 800, 600);
        PartBuilderCarport.drawInnerWidthArrow(svg, 100,100, 800, 600);
        PartBuilderCarport.drawOuterWidthArrow(svg, 100,100, 800, 600);
        PartBuilderCarport.drawPostsWithoutShed(svg, 100,100, 800, 600);
        PartBuilderCarport.drawSperSpaceArrows(svg, 100,100, 800, 600);
        PartBuilderShed.drawShed(svg, 100,100, 800, 600,500,300);
        PartBuilderShed.drawPerforatedBandWithShed(svg, 100, 100, 800, 600, 500, 300);

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
