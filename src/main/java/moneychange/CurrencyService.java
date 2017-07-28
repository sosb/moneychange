package moneychange;

import model.CurrencyRepository;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


public class CurrencyService {

    @Inject
    private CurrencyRepository currencyRepository;

    public Map<Integer, Long> changeCurrency(long amount) {

        Map<Integer, Long> changes = new TreeMap<>();

        Map<Integer, Long> available = new TreeMap<>(Comparator.reverseOrder());
        available.put(10000,5L);
        available.put(5000,4L);


        for (Map.Entry<Integer, Long> entry : available.entrySet()) {

            long counter = 1L;

            int key = entry.getKey();
            long value = entry.getValue();

            while (amount - key >= 0 && available.get(key) != 0){
                if(!changes.containsKey(key)){
                    changes.put(key, counter);
                } else{
                    changes.replace(key, counter, ++counter);
                }
                available.replace(key, value, --value);
                amount = amount - key;
            }
        }
        return changes;
    }

}
