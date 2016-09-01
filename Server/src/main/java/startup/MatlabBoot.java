package startup;

import CalculateTool.CalculateTool;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import exception.NotInitialedException;
import matlabtool.TypeConverter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Daniel on 2016/8/18.
 */
public class MatlabBoot {
    private static CalculateTool calculateTool;

    public static void init() {
        try {
            calculateTool = new CalculateTool();
        } catch (MWException e) {
            e.printStackTrace();
        }
    }

    public static CalculateTool getCalculateTool() throws NotInitialedException {
        if (calculateTool == null)
            throw new NotInitialedException("matlab class IndexCal has not initialed");
        return calculateTool;
    }

    public static void main(String[] args) throws NotInitialedException {
        List<Double> doubleList = Arrays.asList(1.2, 1.3, 0.5, 1.6, 1.4, 1.12, 0.92);
        MWNumericArray mwNumericArray = TypeConverter.convertList(doubleList);
        try {
            init();
            Object[] objs = getCalculateTool().starndarDeviation(1,mwNumericArray);
            System.out.println(objs[0]);
        } catch (MWException e) {
            e.printStackTrace();
        }
    }
}
