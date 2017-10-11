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

// TODO: Auto-generated Javadoc
/**
 * The Class PriceCalculatorImpl.
 */
@Component
public class PriceCalculatorImpl implements PriceCalculator {

  /**
   * Split meals.
   *
   * @param orderEntity
   *          the order entity
   * @param meals
   *          the meals
   * @return the list
   */
  private List<MealDto> splitMeals(OrderEntity orderEntity, List<MealDto> meals) {
    // controllo ogni configurazione
    List<MealDto> mealInConfiguration = new ArrayList<>();
    for (ConfigurationEntity configurationEntity : orderEntity.getConfigurations()) {

      MealType lastType = null;
      for (MealDto mealDto : meals) {
        // se la configurazione contiene un determinato mealtype
        if (configurationEntity.getMealtypes().contains(mealDto.getType())) {
          // se il mealtype non è stato già elaborato
          if (lastType == null || mealDto.getType() != lastType) {
            // si flagga come ultimo visto
            lastType = mealDto.getType();
            // si filtrano i meals per tipo
            mealInConfiguration.add(meals.stream().filter(meal -> meal.getType() == mealDto.getType())
                // successivamente se ne prende quello col
                // prezzo minore tra tutti
                .min(new Comparator<MealDto>() {

                  @Override
                  public int compare(MealDto o1, MealDto o2) {
                    if (o1.getPrice().compareTo(o2.getPrice()) < 0) {
                      return -1;
                    }
                    if (o1.getPrice().compareTo(o2.getPrice()) == 0) {
                      return 0;
                    }
                    return 1;

                  }
                  // e lo si aggiunge alla lista
                }).get());
          }
        }
      }
      meals.removeAll(mealInConfiguration);
    }
    // una volta trovati tutti i meals nelle configurazioni dell'ordine,
    // vengono rimossi e si passa i restanti al calcolo del prezzo totale

    return meals;
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.extrasys.tesi.tagsystem.order_service.db.manager.PriceCalculator#
   * calculatePrice(it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.
   * OrderEntity, java.util.List)
   */
  @Override
  public BigDecimal calculatePrice(OrderEntity orderEntity, List<MealDto> meals) {
    BigDecimal total = new BigDecimal(0);
    List<MealDto> splittedMeals = splitMeals(orderEntity, meals);

    for (ConfigurationEntity conf : orderEntity.getConfigurations()) {
      total = total.add(conf.getSpecialPrice());
    }

    for (MealDto meal : splittedMeals) {
      total = total.add(meal.getPrice());
    }
    return total;
  }

}
