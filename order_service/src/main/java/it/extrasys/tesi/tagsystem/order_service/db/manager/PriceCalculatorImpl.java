package it.extrasys.tesi.tagsystem.order_service.db.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import it.extrasys.tesi.tagsystem.order_service.api.MealDto;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;

@Component
public class PriceCalculatorImpl implements PriceCalculator {
    private List<MealDto> splitMeals(OrderEntity orderEntity,
            List<ConfigurationEntity> configurationEntities,
            List<MealDto> meals) {

        orderEntity.getConfiguration().clear();
        List<ConfigurationEntity> conf = new ArrayList<>();

        conf.add(configurationEntities.stream()
                .max(new Comparator<ConfigurationEntity>() {

                    @Override
                    public int compare(ConfigurationEntity o1,
                            ConfigurationEntity o2) {
                        if (o1.getMealtypes().size() < o2.getMealtypes()
                                .size()) {
                            return -1;
                        }
                        if (o1.getMealtypes().size() > o2.getMealtypes()
                                .size()) {
                            return 1;
                        }
                        return 0;
                    }
                }).get());

        // aggiungo i riferimenti alle configurazioni
        orderEntity.getConfiguration().addAll(conf);

        // split

        // controllo ogni configurazione
        List<MealDto> mealInConfiguration = new ArrayList<>();
        for (ConfigurationEntity configurationEntity : orderEntity
                .getConfiguration()) {

            MealType lastType = null;
            for (MealDto mealDto : meals) {
                // se la configurazione contiene un determinato mealtype
                if (configurationEntity.getMealtypes()
                        .contains(mealDto.getType())) {
                    // se il mealtype non è stato già elaborato
                    if (lastType == null || mealDto.getType() != lastType) {
                        // si flagga come ultimo visto
                        lastType = mealDto.getType();
                        // si filtrano i meals per tipo
                        mealInConfiguration.add(meals.stream().filter(
                                meal -> meal.getType() == mealDto.getType())
                                // successivamente se ne prende quello col
                                // prezzo minore tra tutti
                                .min(new Comparator<MealDto>() {

                                    @Override
                                    public int compare(MealDto o1, MealDto o2) {
                                        if (o1.getPrice()
                                                .compareTo(o2.getPrice()) < 0) {
                                            return -1;
                                        }
                                        if (o1.getPrice().compareTo(
                                                o2.getPrice()) == 0) {
                                            return 0;
                                        }
                                        return 1;

                                    }
                                    // e lo si aggiunge alla lista
                                }).get());
                    }
                }
            }
        }
        // una volta trovati tutti i meals nelle configurazioni dell'ordine,
        // vengono rimossi e si passa i restanti al calcolo del prezzo totale
        meals.removeAll(mealInConfiguration);
        return meals;
    }
    @Override
    public BigDecimal calculatePrice(OrderEntity orderEntity,
            List<ConfigurationEntity> configurationEntities,
            List<MealDto> meals) {
        BigDecimal total = new BigDecimal(0);
        List<MealDto> splittedMeals = splitMeals(orderEntity,
                configurationEntities, meals);

        for (ConfigurationEntity conf : orderEntity.getConfiguration()) {
            total = total.add(conf.getSpecialPrice());
        }

        for (MealDto meal : splittedMeals) {
            total = total.add(meal.getPrice());
        }
        return total;
    }
}
