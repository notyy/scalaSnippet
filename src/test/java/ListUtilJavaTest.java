import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilJavaTest {
    @Test
    public void testFilter() throws Exception {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> rs = ListUtil.filter(list, new FilterFunction<Integer>() {
            @Override
            public boolean filter(Integer item) {
                return item > 2;
            }
        });
        assertThat(rs.size(), is(3));
        assertThat(rs, is(Arrays.asList(3, 4, 5)));
    }

    @Test
    public void testMap() throws Exception {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> rs = ListUtil.map(list, new MapFunction<Integer, Integer>() {
            @Override
            public Integer map(Integer item) {
                return item * 2;
            }
        });
        assertThat(rs.size(), is(5));
        assertThat(rs, is(Arrays.asList(2,4,6,8,10)));
    }
}
