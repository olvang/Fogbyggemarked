package FunctionLayer.SVGGenerator;

import com.sun.javafx.binding.StringFormatter;

public class SVG {

    private int width;
    private int height;
    private String viewbox;
    private int x;
    private int y;
    private StringBuilder svg = new StringBuilder();

    private final String headerTemplate = "\n<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" height=\"%s\" width=\"%s\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\" class=\"svg-content\">";
    private final String rectTemplate = "\n<rect x=\"%d\" y=\"%d\" height=\"%d\" width=\"%d\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String arrowTemplate = "\n<defs>\n" +
            "        <marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
            "            <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
            "        </marker>\n" +
            "        <marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
            "            <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
            "        </marker>\n" +
            "    </defs>";
    private final String lineWithArrow = "\n<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000;\n" +
            "        marker-start: url(#beginArrow);\n" +
            "        marker-end: url(#endArrow);\" />";
    private final String dottedLine = "\n<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";
    private final String shedLine = "\n<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-dasharray: 5 2;;stroke-width: 3px\" />";
    private final String line = "\n<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000;\" />";
    private final String text = "\n<text style=\"text-anchor: middle\" font-size=\"16\" transform=\"translate(%d,%d) rotate(%d)\">%s</text>";

    public SVG(int width, int height, String viewbox, int x, int y) {
        this.width = width;
        this.height = height;
        this.viewbox = viewbox;
        this.x = x;
        this.y = y;
        svg.append(String.format(headerTemplate, height, width, viewbox));
        svg.append(arrowTemplate);
    }

    public void addRect(int x, int y, int height, int width){
        svg.append(String.format(rectTemplate, x, y, height, width));
    }
    public void addLineWithArrow(int x1, int y1, int x2, int y2){
        svg.append(String.format(lineWithArrow,x1,y1,x2,y2));
    }
    public void addLine(int x1, int y1, int x2, int y2, boolean dotted){
        if(dotted){
            svg.append(String.format(dottedLine,x1,y1,x2,y2));
        }else{
            svg.append(String.format(line,x1,y1,x2,y2));
        }
    }
    public void addShedLine(int x1, int y1, int x2, int y2){
            svg.append(String.format(shedLine,x1,y1,x2,y2));
    }
    public void addText(int x, int y, int rotate, String text){
        svg.append(String.format(this.text,x,y,rotate,text));
    }

    public void addInnerDrawing(String innerDrawing){
        svg.append(innerDrawing);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getViewbox() {
        return viewbox;
    }

    public void setViewbox(String viewbox) {
        this.viewbox = viewbox;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>" ;
    }
}
