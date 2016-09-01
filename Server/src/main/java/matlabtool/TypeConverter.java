package matlabtool;

import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWComplexity;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/18.
 */
public class TypeConverter {

    public static <T> MWNumericArray convertList(List<T> numbers) {
        int len = numbers.size();
        MWNumericArray array = MWNumericArray.newInstance(new int[]{1, len}, MWClassID.DOUBLE,
                MWComplexity.REAL);
        for (int i = 0; i < len; i++) {
            array.set(i + 1, numbers.get(i));
        }
        return array;
    }

    public static double getSingleDoubleResult(Object[] objs) {
        MWNumericArray mwNumericArray = (MWNumericArray) objs[0];
        return mwNumericArray.getDouble(1);
    }

    public static double[] getDoubleResults(Object[] objs, int resultNumber) {
        List<MWNumericArray> mwNumericArrays = new ArrayList<>();
        double[] result = new double[resultNumber];
        for (int i = 0; i < resultNumber; i++) {
            result[i] = ((MWNumericArray) objs[i]).getDouble(1);
        }
        return result;
    }

}
