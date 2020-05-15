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
        int fulldepth = 500;
        int fullwidth = 400;
        int depth = fulldepth - 40;
        int width = fullwidth - 40;
        int x = 100;
        int y = 100;
        int xmoved = x + 20;
        int ymoved = y + 20;

        int shedDepth = 200;
        int shedWidth = 400;

        SVG svg = new SVG(700, 700, "0,0,700,700", 0,0);
        PartBuilderCarport.drawOuterBox(svg, x,y, fulldepth, fullwidth);
        PartBuilderShed.drawShed(svg, xmoved, y -15, fulldepth,fullwidth + 15,shedDepth,shedWidth);

        PartBuilderCarport.drawRems(svg, xmoved,y - 15, depth, fullwidth + 15);
        PartBuilderCarport.drawSper(svg, xmoved,y, depth, fullwidth);
        PartBuilderInclinedRoof.drawLaths(svg, x,y, fulldepth, fullwidth);
        PartBuilderInclinedRoof.drawSterns(svg, x,y, fulldepth, fullwidth);

        PartBuilderShed.drawPostsWithShed(svg, xmoved,y - 15, fulldepth, fullwidth + 15, shedDepth, shedWidth);

        PartBuilderCarport.drawDepthArrow(svg, x,y, fulldepth, fullwidth);
        PartBuilderInclinedRoof.drawInclineInnerWidthArrow(svg, x,y, fulldepth, fullwidth);
        PartBuilderCarport.drawOuterWidthArrow(svg, x,y, fulldepth, fullwidth);
        PartBuilderCarport.drawSperSpaceArrows(svg, xmoved,ymoved, depth, width);


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
