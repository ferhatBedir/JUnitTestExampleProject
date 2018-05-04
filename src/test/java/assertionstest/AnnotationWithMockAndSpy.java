package assertionstest;


import assertions.Service;
import assertions.ServiceInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.matchers.Equals;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnnotationWithMockAndSpy {

    @Mock
    private Service serviceMock;
    @Spy
    private Service serviceSpy;
    @Mock
    private ServiceInterface serviceInterface;


    @Test
    public void testService_WhenAccountIdIsNullShouldThrowNullPointException() throws Exception {
        Integer value = null;

        when(serviceSpy.getUserName(null)).thenThrow(new NullPointerException());
        serviceSpy.getUserName(value);
        //parametre olrak null verıldığınde exception veridiğinin kontrolünu yaptık.(spy ile)


        when(serviceMock.getUserName(null)).thenThrow(new NullPointerException());
        serviceMock.getUserName(value);
        //parametre olrak null verıldığınde exception veridiğinin kontrolünu yaptık.(mock ile)

    }


    @Test
    public void testService_isEquals() throws Exception {
        String value = null;
        value = serviceSpy.getUserName(1);
        assertEquals("(User Name --> 1 )",value);
        //donen değeri assertEquals ile karşılaştırdık.
    }
}
