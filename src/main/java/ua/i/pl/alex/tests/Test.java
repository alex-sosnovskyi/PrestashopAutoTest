package ua.i.pl.alex.tests;

import ua.i.pl.alex.models.PruductData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static List<String> currList = new ArrayList<>();
    private static List<String> productPrices = new ArrayList<>();
    private static List<PruductData> prodData = new ArrayList<>();

    public static void main(String[] args) {
        try {
            currList.add("$");
            currList.add("$");
            currList.add("$");
            currList.add("$");
            currList.add("$");

            productPrices.add("1,15 $");
            productPrices.add("1,10 $");
            productPrices.add("1,05 $");
            productPrices.add("1,0 $");
            productPrices.add("0,15 $");

            PruductData pruductData = new PruductData();
            pruductData.setPrice("1,16");
            prodData.add(pruductData);

            PruductData pruductData1 = new PruductData();
            pruductData1.setPrice("5,0");
            pruductData1.setRegPrice("10,0");
            pruductData1.setDiscount("-50%");
            prodData.add(pruductData1);

            PruductData pruductData2 = new PruductData();
            pruductData2.setRegPrice("0,0");
            pruductData2.setDiscount("0,0");
            pruductData2.setPrice("0,16");
            prodData.add(pruductData2);

            PruductData pruductData3 = new PruductData();
            pruductData3.setPrice("1,76");
            prodData.add(pruductData3);

            SearchPageTest searchPageTest = new SearchPageTest();
            Method[] declaredMethods = searchPageTest.getClass().getDeclaredMethods();
            for(int i=0; i< declaredMethods.length; i++){
                String name = declaredMethods[i].getName();
                declaredMethods[i].setAccessible(true);
                switch (name){
                    case "currencyControl":{
                        declaredMethods[i].invoke(searchPageTest, "$", currList); //Ok
                        break;
                    }
                    case "countTest":{
                        declaredMethods[i].invoke(searchPageTest, 4, 4);  //Ok
                        break;
                    }
                    case "sortControl":{
                        declaredMethods[i].invoke(searchPageTest,  productPrices); //Ok
                        break;
                    }
                    case "testDiscount":{
                        declaredMethods[i].invoke(searchPageTest, prodData);  //ok
                        break;
                    }
                    default:{
                        System.out.println(name+" do nothing");
                    }
                }
            }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
