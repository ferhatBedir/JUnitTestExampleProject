package assertionstest;


import assertions.Service;
import assertions.ServiceInterface;
import org.junit.Test;
import org.mockito.InOrder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


public class ServiceInterfaceTest {

    @Test
    public void testMetodCagrildimi() throws Exception {

        ServiceInterface serviceInterface = mock(ServiceInterface.class);
        serviceInterface.addAccount("Ahmet");
        serviceInterface.accountId(1);

        verify(serviceInterface).addAccount("Ahmet");
        verify(serviceInterface).accountId(1);
        //yukarıda yapılan işlemde metodların "Ahmet" ve '1' parametreleri ile cağrılıp cağrılmadığını kontrol ettik.
    }

    @Test
    public void testMetodKacKezCagrildi() throws Exception {
        ServiceInterface serviceInterface = mock(ServiceInterface.class);
        serviceInterface.removeAccount(1);
        serviceInterface.removeAccount(2);
        serviceInterface.removeAccount(3);

        verify(serviceInterface, times(3)).removeAccount(anyInt());
        //yukarıda yapılan işlemde metodun 3 kez cagrıldığını dogruladık.
        //detay olarak removeAccount(anyInt()) içerisine 'antInt' vermeyip paramtre verirsek metodun o paramtere ile kaç kaç cagrıldını test edebiliriz.

        verify(serviceInterface, never()).addAccount(anyString());
        //yukarıda yapılan işlemde metodun hiç çağrılmadığını dogruladık.
        //detay olarak addAccount(anyString()) içerisine 'antString' vermeyip paramtre verirsek metodun o paramtere ile hiç cagrılmadığını test edebiliriz.

        verify(serviceInterface, atLeast(2)).removeAccount(anyInt());
        //yukarıda yapılan işlemde metodun en az 2 kez cagrıldığını dogruladık.
        //detay olarak removeAccount(anyInt()) içerisine 'antInt' vermeyip paramtre verirsek metodun o paramtere ile kaç kaç cagrıldını test edebiliriz.
    }

    @Test
    public void testMetodlarIsrasiIleCagrildimi() {
        ServiceInterface serviceInterface = mock(ServiceInterface.class);
        serviceInterface.removeAccount(1);           //1.sırada
        serviceInterface.addAccount("ferhat"); //2.sırada
        serviceInterface.getUserName(1);             //3.sırada
        serviceInterface.accountId(1);                  //4.sırada

        InOrder inOrder = inOrder(serviceInterface);
        inOrder.verify(serviceInterface).removeAccount(anyInt());
        inOrder.verify(serviceInterface).addAccount(anyString());
        inOrder.verify(serviceInterface).getUserName(anyInt());
        inOrder.verify(serviceInterface).accountId(anyInt());
        //yukarida yapılan işlemde metodların hangi sıra ile çağrıldığının kontrolunu yaptık.
        //bunun için mock nesnemizi Inorder'a parametre olrak verdik ve sıralama konrolunde inorder'ı kullandık.

    }

    @Test
    public void testBaskaMetodCagirma() throws Exception {
        ServiceInterface serviceInterface = mock(ServiceInterface.class);
        serviceInterface.addAccount("Ahmet");
        serviceInterface.removeAccount(1);

        verify(serviceInterface).addAccount(anyString());
        verify(serviceInterface).removeAccount(anyInt());
        verifyNoMoreInteractions(serviceInterface);
        // verifyNoMoreInteractions() metodu ile yukarıda belirtilen 2 metoddan başka bir methodun cağrılmadığının kontrolünü yaptık.
        //
    }

    @Test
    public void testMetoduCagirma() throws Exception {
        ServiceInterface serviceInterface = mock(ServiceInterface.class);

        verifyZeroInteractions(serviceInterface);
        //verifyZeroInteractions() metodu ile hiçbir metodun cagrılmadığının kontrolünü yaptık.
    }

    @Test
    public void testGeriDonusDegeriBelirtme() throws Exception {
        ServiceInterface serviceInterface = mock(ServiceInterface.class);

        //1. getUserName metodunun geri dönüş değerinin testi yapıldı.
        when(serviceInterface.getUserName(eq(1))).thenReturn("Ahmet");
        String getName = serviceInterface.getUserName(1);
        assertThat(getName).isEqualTo("Ahmet");

        //2. getUserId metodunun geri dönüş değerinin testi yapıldı.
        when(serviceInterface.getUserId(anyInt())).thenReturn(10);
        Integer getId = serviceInterface.getUserId(1);
        assertThat(getId).isEqualTo(10);
        //when ile metod metodun nasıl cagrıldığını belirtiyoruz(özel bir parametreil ya da anyInt/anyString vb.. şekilde cağrıldıgını)
        //thenReturn ile cagrılan metodun geriye dondürdüğü metodun değerini belirliyoruz.(örn; String ise = "Ahmet" ya da Integer ise = 10 gibi)
        //Geri donüş değerini bir değişkende tutmayı unutma!!(kontrol yapmak için)
        //assertThat ile karşılaştıma yapıyoruz.(önce dönüş değeri sonra beklenen değer paramtre olarak verilir ve karşılaştıma işlemi yapılır.)
    }

    @Test
    public void testGeriExceptionFirlatma() throws Exception {
        ServiceInterface serviceInterface = mock(ServiceInterface.class);

        when(serviceInterface.getUserName(anyInt())).thenThrow(new NullPointerException());
        serviceInterface.getUserName(1);
        //yukarıdaki işlemde geri donus değerini thenThrow ile NullPointerException fırlattık.
        //burada farklı exception sınıflarından da hata fırlatılabilir.
        //Ayrıca burada assertThat metodu kullanılmasına gerek yoktur.
    }

    @Test
    public void testGeriVoidDondurenMetodlar() throws Exception {
        ServiceInterface serviceInterface = mock(ServiceInterface.class);

        doNothing().when(serviceInterface).removeAccount(anyInt());
        serviceInterface.removeAccount(1);
        //doNothing ile donusu void olan nesne olduğunu belirttik ve kontrolunu yaptık.

        doReturn("AhmetMehmet").when(serviceInterface).getUserName(anyInt());
        serviceInterface.getUserName(1);
        //donus tipi void olan mwtodlarada geriye nasıl donus yapacaklarını belirtebiliriz.
        //doReturn ile geriye ne dondurceğini belirttik ve kontrolunu yaptık.

        doThrow(new NullPointerException()).when(serviceInterface).accountId(anyInt());
        serviceInterface.accountId(1);
        //Geriye exception fırlatmak istediğimizde bunu doThrow ile yapıyoruz.

    }

}
