package moneychange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Training on 2017. 05. 16..
 */
public enum Currency {

    PESO("peso", Arrays.asList(100, 50, 20, 10, 5, 3, 1)),
    HUF_BEFORE2008("forint", Arrays.asList(20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1)),
    HUF_AFTER2008("forint", Arrays.asList(20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5));

    private String name;
    private List<Integer> values = new ArrayList<>();

    Currency(String name, List<Integer> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getValues() {
        return values;
    }
}
