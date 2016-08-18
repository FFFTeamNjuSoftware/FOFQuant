package matlabtool;

import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWComplexity;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

import java.util.List;

/**
 * Created by Daniel on 2016/8/18.
 */
public class TypeConverter {

    public MWNumericArray convertList(List<Number> numbers) {
        int len = numbers.size();
        MWNumericArray array = MWNumericArray.newInstance(new int[]{1, len}, MWClassID.DOUBLE,
                MWComplexity.REAL);
        for (int i = 0; i < len; i++) {
            array.set(i + 1, numbers.get(i));
        }
        return array;
    }

}
