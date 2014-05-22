import java.util.ArrayList;
import java.util.List;

/**
 * this demo shows simple collection manipulate(filter,map) in java
 */
public class MapSample {
    public List<Integer> process(List<Integer> list) {
        List<Integer> result = new ArrayList<Integer>();
        for (int x : list) {
            if (x > 2) {
                result.add(x * 2);
            }
        }
        return result;
    }
}
