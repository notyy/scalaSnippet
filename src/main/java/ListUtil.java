import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public static <A> List<A> filter(List<A> list, FilterFunction<A> f) {
        List<A> result = new ArrayList<A>();
        for (A item : list) {
            if (f.filter(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static <A, B> List<B> map(List<A> list, MapFunction<A,B> f) {
        List<B> result = new ArrayList<B>();
        for(A item: list){
            result.add(f.map(item));
        }
        return result;
    }
}
