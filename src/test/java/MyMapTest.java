import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyMapTest {
    @Test
    public void mockito_can_set_when_return_value_multiple_times(){
        MyMap mockMap = mock(MyMap.class);
        when(mockMap.get("key1")).thenReturn(null);
        assertNull(mockMap.get("key1"));
        mockMap.put("key1","value1");
        when(mockMap.get("key1")).thenReturn("value1");
        assertThat(mockMap.get("key1"),is("value1"));
    }
}