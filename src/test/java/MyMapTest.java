import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MyMapTest {
    @Test
    public void mockito_can_set_when_return_value_multiple_times() {
        MyMap mockMap = mock(MyMap.class);
        SomeParameter expectedParameter = new SomeParameter("value1", "value2");
        when(mockMap.get("key1")).thenReturn(null).thenReturn(expectedParameter);

        SomeProcessor someProcessor = new SomeProcessor();
        someProcessor.doProcess(mockMap);

        InOrder inOrder = inOrder(mockMap);
        ArgumentCaptor<SomeParameter> argument = ArgumentCaptor.forClass(SomeParameter.class);
        inOrder.verify(mockMap).put(eq("key1"), argument.capture());
        assertThat(argument.getValue().getValue1(), is(expectedParameter.getValue1()));
        assertThat(argument.getValue().getValue2(), is(expectedParameter.getValue2()));

        inOrder.verify(mockMap).put(eq("key2"), argument.capture());
        assertThat(argument.getValue().getValue1(), is(expectedParameter.getValue1()));
        assertThat(argument.getValue().getValue2(), is(expectedParameter.getValue2()));
    }

    //this is the class to be tested
    class SomeProcessor {
        public void doProcess(MyMap myMap) {
            if (myMap.get("key1") == null) {
                myMap.put("key1", new SomeParameter("value1", "value2"));
            }

            SomeParameter someParameter = (SomeParameter) myMap.get("key1");
            if (someParameter.getValue1().equals("value1")) {
                myMap.put("key2", someParameter);
            }
        }
    }

    class SomeParameter {

        public SomeParameter(String value1, String value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public String getValue1() {
            return value1;
        }

        public String getValue2() {
            return value2;
        }

        private String value1;
        private String value2;
    }
}