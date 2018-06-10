package pl.coderslab.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Rate;
import pl.coderslab.entity.RateMid;

import java.sql.Date;
import java.util.List;


@Repository
public interface RateMidRepository extends JpaRepository<RateMid, Long> {

  RateMid findFirstByEffectiveDateEqualsAndCurrencyCodeIsLike(Date effectiveDate, String currency);

  RateMid findFirstByCurrencyCodeIsLike(String currencyCode);

  RateMid findFirstByEffectiveDateBetweenAndCurrencyCodeIsLikeOrderByEffectiveDateDesc(Date startDate, Date endDate, String currency);

  RateMid findFirstByOrderByEffectiveDateDesc();

  List<RateMid> findAllByEffectiveDateEqualsOrderByCurrencyCodeAsc(Date date);

  List<RateMid> findAllByEffectiveDateBetweenAndCurrencyCodeIsLike(Date startPeriod, Date endPeriod, String currencyCode);

  List<RateMid> findAllByMidGreaterThanAndCurrencyCodeIsLike(Double mid, String currency);

  List<RateMid> findAllByMidLessThanAndCurrencyCodeIsLike(Double mid, String currency);

  List<RateMid> findAllByMidBetweenAndCurrencyCodeLike(Double lowBarrier, Double highBarrier, String currencyCode);

  RateMid findFirstByEffectiveDateBetweenAndCurrencyCodeLikeOrderByMidDesc(Date startPeriod, Date endPeriod, String currencyCode);

  RateMid findFirstByEffectiveDateBetweenAndCurrencyCodeLikeOrderByMidAsc(Date startPeriod, Date endPeriod, String currencyCode);

  List<RateMid> deleteAllByCurrencyCodeLike(String currencyCode);

  List<RateMid> deleteAllByEffectiveDateBetween(Date startDate, Date endDate);

}
