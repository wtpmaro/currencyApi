package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Rate;
import pl.coderslab.entity.RateMid;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorRounding {

    public Double rateRoundToTwoDecimals(Double rate) {

        BigDecimal resultRounded = new BigDecimal(Double.toString(rate));
        resultRounded = resultRounded.setScale(2, RoundingMode.HALF_UP);
        Double finalResult = resultRounded.doubleValue();
        return finalResult;
    }
}
