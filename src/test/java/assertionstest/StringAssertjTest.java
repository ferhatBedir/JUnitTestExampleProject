package assertionstest;

import assertions.StringAssertj;
import org.assertj.core.api.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class StringAssertjTest {

    @Spy
    private StringAssertj value;

    @Test
    public void testStringWhitAssertJ() throws Exception {
        System.out.println(value.team);

        assertThat(value.team)
                .isEqualTo("Fenerbahçe") //Eşit mi? kontrol ediyor.
                .startsWith("Fe")        //'Fe' ile mi başlıyor? kontrol diyor.
                .endsWith("çe")          //'çe' ilemi bitiyor? kontrol ediyor
                .isNotEmpty()            //içerisinin boş olmadığını kontrol ediyor.
                .isNotNull()             //içersinin null olmadığını kontrol ediyor.
                .contains("nerba")       //içerisinde 'nerba' iafadesi var mı? kontrol ediyor.
                .containsOnlyOnce("bah");//içerisinde sadece 1 kez 'bah' ifadesinin olduğunu kontrol ediyor.
    }

    @Test
    public void testListWhitAssertJ() throws Exception {

        value.array.add("Ahmet");
        value.array.add("Mehmet");
        value.array.add("Ayşe");
        value.array.add("Fatma");
        value.array.add("Zeynep");

        assertThat(value.array)
                .contains("Ahmet")//dizi içerisinde 'Ahmet' ifadesinin oldup olmadıını kontrol ediyor.
                .doesNotContain("Ferhat")//dizi içerisinde 'ferhat' ifadesinin olmadığını kontrol ediyor.
                .containsAnyOf("ali", "veli", "Zeynep")//dizi içerisinde parametre olrak verilen ifadelerden an az birinin olması gerektiğini kontrol ediyor.(or kapısı)
                .containsExactly("Ahmet", "Mehmet", "Ayşe", "Fatma", "Zeynep")//dizi içerisinde parametre olrak verilen ifadelerin hepsinin olması gerektiğini kontrol ediyor(and kapısı)
                .doesNotHaveDuplicates();//dizi içerisinde tekrar eden değer olmadığını kontrol ediyor.


    }

}
