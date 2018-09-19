package pl.coderslab.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorRounding {

    /** Method allows to convert figure to money format (0,00)
     *
     * @param rate - exchange rate usually in 4 decimal format
     * @return amount in money format(0,00)
     */
    public Double rateRoundToTwoDecimals(Double rate) {

        BigDecimal resultRounded = new BigDecimal(Double.toString(rate));
        resultRounded = resultRounded.setScale(2, RoundingMode.HALF_UP);
        Double finalResult = resultRounded.doubleValue();
        return finalResult;
    }
}
