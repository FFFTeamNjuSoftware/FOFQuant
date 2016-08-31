package util;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by Daniel on 2016/8/21.
 */
public class NumberOpe {
    /**
     * 将对应对象内的double类型数据保留两位小数
     * 将会递归处理对象类的支持类型的成员变量对象或数组
     *
     * @param obj
     * @param decimalNum
     * @param supportType
     */
    public static void controlDecimal(Object obj, int decimalNum, Class... supportType) {
        if (obj == null)
            return;
        try {
            if (obj.getClass().isArray()) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    if (isDoubleType(obj.getClass().getComponentType())) {
                        Array.set(obj, i, controlDecimalDouble((double) Array.get(obj, i), decimalNum));
                    } else {
                        controlDecimal(Array.get(obj, i), decimalNum);
                    }
                }
            } else {
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.getType().isArray()) {
                        controlDecimal(field.get(obj), decimalNum);
                    }
                    if (isDoubleType(field.getType())) {
                        double num = (double) field.get(obj);
                        double after = controlDecimalDouble(num, decimalNum);
                        field.set(obj, after);
                    }
                    if (Arrays.asList(supportType).contains(field.getType())) {
                        controlDecimal(field.get(obj), decimalNum);
                    }

                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static double controlDecimalDouble(double num, int decimalNum) {
        return new Double(String.format("%." + decimalNum + "f", num));
    }

    private static boolean isDoubleType(Class type) {
        return type == double.class || type == Double.class;
    }

//    public static void main(String[] args) {
//        NumberOpe ope = new NumberOpe();
//        Test test = new Test();
//        test.a = 2;
//        test.b = 1.23265626;
//        test.c = 451.62626;
//        test.array = new double[]{5.26656, 26.2626, 1.16165};
//
//        Test t2 = new Test();
//        t2.a = 3;
//        t2.b = 515.5626;
//        t2.c = 69592.26;
//        t2.array = new double[]{1.23, 1265.2, 12.1626, 166.6};
//        test.ta = new Test[]{t2};
//        Test t3 = new Test();
//        t3.a = 2;
//        t3.b = 23.356;
//        t3.c = 12.2365;
//
//        test.tb = t3;
//        System.out.println(new Gson().toJson(test));
//        ope.controlDecimal(test, 2,Test.class);
//        System.out.println(new Gson().toJson(test));
//    }
}
//
//class Test {
//    int a;
//    double b;
//    Double c;
//    double[] array;
//    Test[] ta;
//    Test tb;
//}
