package moneychange;

import model.CurrencyRepository;
import model.Denomination;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class CurrencyService {

    @Inject
    private CurrencyRepository cr;

    public Map<Integer, Long> changeCurrency(Currency currency, long amount) {

        Map<Integer, Long> changes = new TreeMap<>();
        Map<Integer, Long> available = mappingDenominationQuantities(currency);


        for (Map.Entry<Integer, Long> entry : available.entrySet()) {

            long counter = 1L;

            int key = entry.getKey();
            long value = entry.getValue();

            while (amount - key >= 0 && available.get(key) != 0){
                if(!changes.containsKey(key)){
                    changes.put(key, counter);
                } else {
                    changes.replace(key, counter, ++counter);
                }
                available.replace(key, value, --value);
                cr.updateDenominationQuantity(key, currency);
                amount = amount - key;
            }
        }
        return changes;
    }


    private Map<Integer, Long> mappingDenominationQuantities(Currency currency){
        return cr.getAllDenomination(currency).stream()
                .collect(Collectors.toMap(Denomination::getDenominationValue, Denomination::getQuantity));
    }

}
