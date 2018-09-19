package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Rate;
import pl.coderslab.entity.RateMid;
import pl.coderslab.repository.RateMidRepository;
import pl.coderslab.repository.RateRepository;
import pl.coderslab.service.CalculatorRounding;
import pl.coderslab.service.ParamValidation;

import java.sql.Date;


@Controller
@RequestMapping("/calculator")
public class CalculatorController {

    @Autowired
    RateSaveTableCController rateSaveTableCController;

    @Autowired
    ParamValidation paramValidation;

    @Autowired
    RateRepository rateRepository;

    @Autowired
    RateMidRepository rateMidRepository;

    @Autowired
    CalculatorRounding calculatorRounding;


    @GetMapping("/form")
    public String calculatorAskBid(Model model) {
        model.addAttribute("lists", rateSaveTableCController.currencyList());
        return "calculatorForm";
    }

    /** Method allows conversion PLN amount to foreign amount using choosen value date
     *
     * @param model
     * @param currencyCode - currency code containing 3 signs. 
     * @param amountToConversion - amount that user would like to exchange in PLN
     * @param startDate - value date or earlier
     * @param priceType - ask or mid price. It is in which price user would like to make conversion.
     * @return amount after conversion in foreign conversion (view)
     */

    @PostMapping("/buyForeign")
    public String calculator(Model model, @RequestParam String currencyCode, Double amountToConversion, String startDate, String priceType) {
        try {
            if (priceType.equals("ask")) {

                Date from = Date.valueOf(startDate);

                Date endDate = paramValidation.period(from);

                Rate rate = rateRepository.findFirstByEffectiveDateBetweenAndCurrencyCodeIsLikeOrderByEffectiveDateDesc(endDate, from, currencyCode);

                // Ask Rate will be added to View
                Double askRate = rate.getAsk();

                //Result rounding and
                Double result = amountToConversion / askRate;
                Double roundedResult = calculatorRounding.rateRoundToTwoDecimals(result);

                //Add to attributte
                model.addAttribute("amountToconversion", amountToConversion);
                model.addAttribute("exchangeRate", askRate);
                model.addAttribute("rates", rate);
                model.addAttribute("calculation", roundedResult);

                return "rateCalculatorView";
            } else {

                Date from = Date.valueOf(startDate);

                Date endDate = paramValidation.period(from);
                RateMid rate = rateMidRepository.findFirstByEffectiveDateBetweenAndCurrencyCodeIsLikeOrderByEffectiveDateDesc(endDate, from, currencyCode);

                // Mid Rate will be added to View
                Double midRate = rate.getMid();

                //Result rounding
                Double result = amountToConversion / midRate;
                Double roundedResult = calculatorRounding.rateRoundToTwoDecimals(result);

                //Add to attribute
                model.addAttribute("amountToconversion", amountToConversion);
                model.addAttribute("exchangeRate", midRate);
                model.addAttribute("rates", rate);
                model.addAttribute("calculation", roundedResult);

                return "rateCalculatorView";
            }
        } catch (IllegalArgumentException | NullPointerException ex) {
                return "redirect:/calculator/form";
        }
    }

    /** Method allows conversion amount in foreign currency to PLN amount using choosen value date
     *
     * @param model
     * @param currencyCode - currency code containing 3 signs.
     * @param amountToConversion - amount that user would like to exchange in foreign currency
     * @param startDate - value date or earlier
     * @param priceType - bid or mid price. It is in which price user would like to make conversion.
     * @return amount after conversion (view)
     */
    @PostMapping("/buyPln")
    public String calculatorPln(Model model, @RequestParam String currencyCode, Double amountToConversion, String startDate, String priceType) {
        try {
            if (priceType.equals("ask")) {

                Date from = Date.valueOf(startDate);

                Date endDate = paramValidation.period(from);
                Rate rate = rateRepository.findFirstByEffectiveDateBetweenAndCurrencyCodeIsLikeOrderByEffectiveDateDesc(endDate, from, currencyCode);

                // Ask Rate will be added to View
                Double rateBid = rate.getBid();

                //Result rounding and
                Double result = amountToConversion * rateBid;
                Double roundedResult = calculatorRounding.rateRoundToTwoDecimals(result);

                //Add to attributte
                model.addAttribute("amountToconversion", amountToConversion);
                model.addAttribute("exchangeRate", rateBid);
                model.addAttribute("rates", rate);
                model.addAttribute("calculation", roundedResult);

                return "rateCalculatorView";
            } else {


                Date from = Date.valueOf(startDate);

                Date endDate = paramValidation.period(from);
                RateMid rate = rateMidRepository.findFirstByEffectiveDateBetweenAndCurrencyCodeIsLikeOrderByEffectiveDateDesc(endDate, from, currencyCode);

                // Mid Rate will be added to View
                Double midRate = rate.getMid();

                //Result rounding
                Double result = amountToConversion * midRate;
                Double roundedResult = calculatorRounding.rateRoundToTwoDecimals(result);

                //Add to attribute
                model.addAttribute("amountToconversion", amountToConversion);
                model.addAttribute("exchangeRate", midRate);
                model.addAttribute("rates", rate);
                model.addAttribute("calculation", roundedResult);

                return "rateCalculatorViewFromForeign";
            }
        }catch (IllegalArgumentException | NullPointerException ex) {
                return "redirect:/calculator/form";
            }

    }

}



