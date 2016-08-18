package startup;

import IndexCal.IndexCal;
import com.mathworks.toolbox.javabuilder.MWException;
import exception.NotInitialedExcepiton;

/**
 * Created by Daniel on 2016/8/18.
 */
public class MatlabBoot {
    private static IndexCal indexCal;

    public static void init() throws MWException {
        indexCal = new IndexCal();
    }

    public static IndexCal getIndexCal() throws NotInitialedExcepiton {
        if (indexCal == null)
            throw new NotInitialedExcepiton("matlab class IndexCal has not initialed");
        return indexCal;
    }

}
