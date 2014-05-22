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

    public List<Integer> funcProcess(List<Integer> list) {
        List<Integer> filterRs = ListUtil.filter(list, new FilterFunction<Integer>() {
            @Override
            public boolean filter(Integer item) {
                return item > 2;
            }
        });
        List<Integer> mapRs = ListUtil.map(filterRs, new MapFunction<Integer, Integer>() {
            @Override
            public Integer map(Integer item) {
                return item * 2;
            }
        });
        return mapRs;
    }
}
