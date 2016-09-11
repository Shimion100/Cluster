import edu.muc.jxd.item.Element;
import edu.muc.jxd.item.ElmentNumeralization;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by gwd on 9/11/2016.
 */
public class TestMain {

    private static Logger logger=Logger.getLogger(TestMain.class.getName());
    @Test
    public void test(){
        Element<Number> numberElement=new Element<>();
        numberElement.setElement(11);
        int t=numberElement.plastic(new ElmentNumeralization<Number>() {
            @Override
            public int numberalization(Number number) {
                return number.intValue()+22;
            }
        });

        logger.debug(t);
    }
}
