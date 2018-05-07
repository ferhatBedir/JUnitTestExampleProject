package assertions;

import java.util.ArrayList;
import java.util.List;

public class StringAssertj {

    public String team = "Fenerbahçe";
    public String name = "Alex De Souza";

    public List<String> array = new ArrayList<String>();

    @Override
    public boolean equals(Object obj) {
        return this.team == ((StringAssertj) obj).team;
    }


}
