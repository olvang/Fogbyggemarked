package FunctionLayer.BillGenerator;

import Components.MaterialHeightComponent;
import Components.MaterialLengthComponent;
import Components.MaterialWidthComponent;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.Material;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GeneratorUtilitiesTest {
    
    @Test
    public void sortMaterialsByLengthSimple() {
        ArrayList<Material> categoriesUsedInGenerator = new ArrayList<Material>();
        ArrayList<Material> expected = new ArrayList<Material>();

        try {
            Material material1 = new Material(1,new MaterialLengthComponent(10),new MaterialHeightComponent(10),new MaterialWidthComponent(10),"Name1",10,1, 1);
            Material material2 = new Material(2,new MaterialLengthComponent(20),new MaterialHeightComponent(20),new MaterialWidthComponent(20),"Name2",20,1, 1);
            Material material3 = new Material(3,new MaterialLengthComponent(30),new MaterialHeightComponent(30),new MaterialWidthComponent(30),"Name3",30,1, 1);
            Material material4 = new Material(4,new MaterialLengthComponent(40),new MaterialHeightComponent(40),new MaterialWidthComponent(40),"Name4",40,1, 1);
            categoriesUsedInGenerator.add(material1);
            categoriesUsedInGenerator.add(material2);
            categoriesUsedInGenerator.add(material3);
            categoriesUsedInGenerator.add(material4);

            expected.add(material4);
            expected.add(material3);
            expected.add(material2);
            expected.add(material1);
        } catch (ValidationFailedException e) {
            fail("Could not setup test");
        }

        ArrayList<Material> actual = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator);

        assertEquals(expected, actual);

    }

    @Test
    public void sortMaterialsByLengthComplex() {
        ArrayList<Material> categoriesUsedInGenerator = new ArrayList<Material>();
        ArrayList<Material> expected = new ArrayList<Material>();

        try {
            Material material1 = new Material(23,new MaterialLengthComponent(1122),new MaterialHeightComponent(284),new MaterialWidthComponent(1265),"Name1",120,1, 1);
            Material material2 = new Material(27,new MaterialLengthComponent(731),new MaterialHeightComponent(2672),new MaterialWidthComponent(2362),"Name2",731,1, 1);
            Material material3 = new Material(34,new MaterialLengthComponent(3229),new MaterialHeightComponent(2330),new MaterialWidthComponent(855),"Name3",236,1, 1);
            Material material4 = new Material(47,new MaterialLengthComponent(267),new MaterialHeightComponent(3461),new MaterialWidthComponent(243),"Name4",346,1, 1);
            Material material5 = new Material(89,new MaterialLengthComponent(1123),new MaterialHeightComponent(284),new MaterialWidthComponent(1265),"Name5",346,1, 1);
            Material material6 = new Material(74,new MaterialLengthComponent(262),new MaterialHeightComponent(2672),new MaterialWidthComponent(2362),"Name6",2346,1, 1);
            Material material7 = new Material(25,new MaterialLengthComponent(3230),new MaterialHeightComponent(2330),new MaterialWidthComponent(855),"Name7",234,1, 1);
            Material material8 = new Material(78,new MaterialLengthComponent(598),new MaterialHeightComponent(3461),new MaterialWidthComponent(243),"Name8",47310,1, 1);
            categoriesUsedInGenerator.add(material1);
            categoriesUsedInGenerator.add(material2);
            categoriesUsedInGenerator.add(material3);
            categoriesUsedInGenerator.add(material4);
            categoriesUsedInGenerator.add(material5);
            categoriesUsedInGenerator.add(material6);
            categoriesUsedInGenerator.add(material7);
            categoriesUsedInGenerator.add(material8);


            expected.add(material7);
            expected.add(material3);
            expected.add(material5);
            expected.add(material1);
            expected.add(material2);
            expected.add(material8);
            expected.add(material4);
            expected.add(material6);
        } catch (ValidationFailedException e) {
            fail("Could not setup test");
        }

        ArrayList<Material> actual = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator);

        assertEquals(expected, actual);

    }

    @Test
    public void sortMaterialsByWidthSimple() {
        ArrayList<Material> categoriesUsedInGenerator = new ArrayList<Material>();
        ArrayList<Material> expected = new ArrayList<Material>();

        try {
            Material material1 = new Material(1,new MaterialLengthComponent(10),new MaterialHeightComponent(10),new MaterialWidthComponent(10),"Name1",10,1, 1);
            Material material2 = new Material(2,new MaterialLengthComponent(20),new MaterialHeightComponent(20),new MaterialWidthComponent(20),"Name2",20,1, 1);
            Material material3 = new Material(3,new MaterialLengthComponent(30),new MaterialHeightComponent(30),new MaterialWidthComponent(30),"Name3",30,1, 1);
            Material material4 = new Material(4,new MaterialLengthComponent(40),new MaterialHeightComponent(40),new MaterialWidthComponent(40),"Name4",40,1, 1);
            categoriesUsedInGenerator.add(material1);
            categoriesUsedInGenerator.add(material2);
            categoriesUsedInGenerator.add(material3);
            categoriesUsedInGenerator.add(material4);

            expected.add(material4);
            expected.add(material3);
            expected.add(material2);
            expected.add(material1);
        } catch (ValidationFailedException e) {
            fail("Could not setup test");
        }

        ArrayList<Material> actual = GeneratorUtilities.sortMaterialsByWidth(categoriesUsedInGenerator);

        assertEquals(expected, actual);

    }

    @Test
    public void sortMaterialsByWidthComplex() {
        ArrayList<Material> categoriesUsedInGenerator = new ArrayList<Material>();
        ArrayList<Material> expected = new ArrayList<Material>();

        try {
            Material material1 = new Material(23, new MaterialLengthComponent(1122), new MaterialHeightComponent(284), new MaterialWidthComponent(1265), "Name1", 120, 1, 1);
            Material material2 = new Material(27, new MaterialLengthComponent(731), new MaterialHeightComponent(2672), new MaterialWidthComponent(2362), "Name2", 731, 1, 1);
            Material material3 = new Material(34, new MaterialLengthComponent(3229), new MaterialHeightComponent(2330), new MaterialWidthComponent(855), "Name3", 236, 1, 1);
            Material material4 = new Material(47, new MaterialLengthComponent(267), new MaterialHeightComponent(3461), new MaterialWidthComponent(243), "Name4", 346, 1, 1);
            Material material5 = new Material(89, new MaterialLengthComponent(1123), new MaterialHeightComponent(284), new MaterialWidthComponent(1265), "Name5", 346, 1, 1);
            Material material6 = new Material(74, new MaterialLengthComponent(262), new MaterialHeightComponent(2672), new MaterialWidthComponent(2362), "Name6", 2346, 1, 1);
            Material material7 = new Material(25, new MaterialLengthComponent(3230), new MaterialHeightComponent(2330), new MaterialWidthComponent(855), "Name7", 234, 1, 1);
            Material material8 = new Material(78, new MaterialLengthComponent(598), new MaterialHeightComponent(3461), new MaterialWidthComponent(243), "Name8", 47310, 1, 1);
            categoriesUsedInGenerator.add(material1);
            categoriesUsedInGenerator.add(material2);
            categoriesUsedInGenerator.add(material3);
            categoriesUsedInGenerator.add(material4);
            categoriesUsedInGenerator.add(material5);
            categoriesUsedInGenerator.add(material6);
            categoriesUsedInGenerator.add(material7);
            categoriesUsedInGenerator.add(material8);


            expected.add(material2);
            expected.add(material6);
            expected.add(material1);
            expected.add(material5);
            expected.add(material3);
            expected.add(material7);
            expected.add(material4);
            expected.add(material8);
        } catch (ValidationFailedException e) {
            fail("Could not setup test");
        }

        ArrayList<Material> actual = GeneratorUtilities.sortMaterialsByWidth(categoriesUsedInGenerator);

        assertEquals(expected, actual);
    }

}



