package assertionstest;

import assertions.Service;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ServiceTest {


    @Test
    public void testObjectOfSpy() throws Exception {
        Service service = spy(new Service());

        service.addAccount("Ahmet Mehmet");
        //service nesnesi üzerinden sınıfın metoduna ulaştık ve cagırdıgımızda gercek nesne gibi davrandı ve fonksiyonu yerine getirdi.


        doNothing().when(service).addAccount(anyString());
        service.addAccount("Ali Veli");
        //donothing ile service üzerinde metoda ulaştıgımızda yalancı metod gibi davrandığını kontrol ettik.


        doNothing().when(service).addAccount("Ali");
        doNothing().when(service).addAccount("veli");
        doCallRealMethod().when(service).addAccount("Ahmet");

        service.addAccount("Ali");
        service.addAccount("veli");
        service.addAccount("Ahmet");
        //yukarıda işlemlerde doNothing ile yalancı nesne gibi davranmasını ve doCallRealMethod ile gerçek nesne gibi davrandığını kontrol ettik.
        //burada aslında gelen parametreye gore gercek ya da sahte nesne gibi davran dedık.


        doThrow(new NullPointerException()).when(service).removeAccount(anyInt());
        service.removeAccount(1);
        //doThrow metodu ile service üzerinde cegrılan removeAccount metodunda exception fırlattığını kontrol ettik.


    }


}
