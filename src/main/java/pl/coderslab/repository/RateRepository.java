package pl.coderslab.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Rate;

import java.sql.Date;
import java.util.List;


@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

  Rate findFirstByEffectiveDateEqualsAndCurrencyCodeIsLike(Date effectiveDate, String currency);

  Rate findFirstByCurrencyCodeIsLikeAndAskGreaterThan(String currencyCode, double barrier);

  Rate findFirstByCurrencyCodeIsLike(String currencyCode);

  Rate findFirstByEffectiveDateBetweenAndCurrencyCodeIsLikeOrderByEffectiveDateDesc(Date startDate, Date endDate, String currency);

  Rate findFirstByOrderByEffectiveDateDesc();

  List<Rate> findAllByAskGreaterThanAndCurrencyCodeIsLike(Double ask, String currency);

  List<Rate> findAllByBidGreaterThanAndCurrencyCodeIsLike(Double ask, String currency);

  List<Rate> findAllByEffectiveDateBetweenAndAndCurrencyCodeIsLike(Date startPeriod, Date endPeriod, String currencyCode);

  List<Rate> findAllByEffectiveDateEqualsOrderByCurrencyCodeAsc(Date date);

  List<Rate> findAllByAskBetweenAndCurrencyCodeLike(Double lowBarrier, Double highBarrier, String currencyCode);

  List<Rate> findAllByBidBetweenAndCurrencyCodeLike(Double lowBarrier, Double highBarrier, String currencyCode);

  Rate findFirstByEffectiveDateBetweenAndCurrencyCodeLikeOrderByAskDesc(Date startPeriod, Date endPeriod, String currencyCode);

  Rate findFirstByEffectiveDateBetweenAndCurrencyCodeLikeOrderByAskAsc(Date startPeriod, Date endPeriod, String currencyCode);

  Rate findFirstByEffectiveDateBetweenAndCurrencyCodeLikeOrderByBidDesc(Date startPeriod, Date endPeriod, String currencyCode);

  Rate findFirstByEffectiveDateBetweenAndCurrencyCodeLikeOrderByBidAsc(Date startPeriod, Date endPeriod, String currencyCode);

  List<Rate> deleteAllByEffectiveDateBetween(Date startPeriod, Date endPeriod);

  List<Rate> deleteAllByCurrencyCodeLike(String currencyCode);

}
