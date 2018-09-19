package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.repository.RateMidRepository;
import pl.coderslab.repository.RateRepository;
import pl.coderslab.service.ParamValidation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/showA")
public class RateShowControllerMid {

    @Autowired
    RateMidRepository rateRepository;

    @Autowired
    RateSaveTableCController rateSaveTableCController;

    @Autowired
    ParamValidation paramValidation;

    /**Method returns newest table A for all currencies.
     *
     * @param model
     * @return table A view.
     */
    @GetMapping("/table")
    public String tableNewest(Model model) {

        Date date = rateRepository.findFirstByOrderByEffectiveDateDesc().getEffectiveDate();
        String tableName = rateRepository.findFirstByOrderByEffectiveDateDesc().getNo();
        model.addAttribute("date",date);
        model.addAttribute("table",tableName);
        model.addAttribute("rates",rateRepository.findAllByEffectiveDateEqualsOrderByCurrencyCodeAsc(date));
        return "rateTableNewMid";
    }


    @GetMapping("/date")
    public String show(Model model) {
        model.addAttribute("lists",rateSaveTableCController.currencyList());
        return "searchTableA";
    }

    @PostMapping("/date")
    public String showView(Model model, @RequestParam String startDate, String currencyCode) {

        try {
            Date from = Date.valueOf(startDate);
            Date endDate = paramValidation.period(from);

            model.addAttribute("rates",rateRepository.findFirstByEffectiveDateBetweenAndCurrencyCodeIsLikeOrderByEffectiveDateDesc(endDate,from,currencyCode));
            return "rateRecordMid";

        } catch (IllegalArgumentException ex) {
            return "redirect:/showA/date";
        }
    }

    /** Method that allows to return pbjects from table A for indicated period
     *
     * @param model
     * @param startPeriod - beginning period
     * @param endPeriod - end period
     * @param currencyCode
     * @return view of objects that qualify conditions
     */
    @PostMapping("/period")
    public String ratesPerPeriod (Model model, @RequestParam String startPeriod, String endPeriod, String currencyCode) {
        try {
            Date from = Date.valueOf(startPeriod);
            Date to = Date.valueOf(endPeriod);

            model.addAttribute("rates",rateRepository.findAllByEffectiveDateBetweenAndCurrencyCodeIsLike(from, to, currencyCode));
            return "rateTableMid";

        } catch (IllegalArgumentException ex) {
            return "redirect:/showA/date";
        }
    }


    /** Method that allows to return pbjects that mid price is greater than indicated value
     *
     * @param model
     * @param midPrice - mi price greater than indicated value
     * @param currencyCode
     * @return view of objects that qualify conditions
     */
    @PostMapping("/midGreater")
    public String showMidGreater (Model model, @RequestParam Double midPrice, String currencyCode) {
        if(midPrice == null) {
            return "redirect:/showA/date";
        }

        model.addAttribute("rates",rateRepository.findAllByMidGreaterThanAndCurrencyCodeIsLike(midPrice, currencyCode));
        return "rateTableMid";
    }

    /** Method that allows to return pbjects that mid price is lower than indicated value
     *
     * @param model
     * @param midPrice - mid price lower than indicated value
     * @param currencyCode
     * @return view of objects that qualify conditions
     */
    @PostMapping("/midLower")
    public String showMidLower (Model model, @RequestParam Double midPrice, String currencyCode) {

        if(midPrice == null) {
            return "redirect:/showA/date";
        }
        model.addAttribute("rates",rateRepository.findAllByMidLessThanAndCurrencyCodeIsLike(midPrice, currencyCode));
        return "rateTableMid";
    }

    /**  Method that allows to return pbjects in indicated mid price range
     *
      * @param model
     * @param lowBarrier- low barrier of bid price
     * @param highBarrier - high barriet of bid price
     * @param currencyCode
     * @return view of objects that qualify conditions
     */
    @PostMapping("/periodMidBetween")
    public String ratesPerPeriodAskBetween (Model model, @RequestParam Double lowBarrier, Double highBarrier, String currencyCode) {
        if((lowBarrier == null) || (highBarrier==null)) {
            return "redirect:/showA/date";
        }
        model.addAttribute("rates",rateRepository.findAllByMidBetweenAndCurrencyCodeLike(lowBarrier, highBarrier, currencyCode));
        return "rateTableMid";
    }

    /** Method that allows to return max mid price for indicated currency in period
     *
     * @param model
     * @param startDate -  beginning period
     * @param endDate - end period
     * @param currencyCode
     * @return  view of objects that qualify conditions
     */
    @PostMapping("/midMaxPerPeriod")
    public String askMaxPerPeriod (Model model, @RequestParam String startDate, String endDate, String currencyCode) {
        try {
            Date from = Date.valueOf(startDate);
            Date to = Date.valueOf(endDate);

            model.addAttribute("rates",rateRepository.findFirstByEffectiveDateBetweenAndCurrencyCodeLikeOrderByMidDesc(from, to, currencyCode));
            return "rateRecordMid";

        } catch (IllegalArgumentException ex) {
            return "redirect:/showA/date";
        }
    }

    /** Method that allows to return min mid price for indicated currency in period
     *
     * @param model
     * @param startDate - beginning period
     * @param endDate - end period
     * @param currencyCode
     * @return  view of objects that qualify conditions 
     */

    @PostMapping("/midMinPerPeriod")
    public String askMinPerPeriod (Model model, @RequestParam String startDate, String endDate, String currencyCode) {

        try {
            Date from = Date.valueOf(startDate);
            Date to = Date.valueOf(endDate);

            model.addAttribute("rates",rateRepository.findFirstByEffectiveDateBetweenAndCurrencyCodeLikeOrderByMidAsc(from, to, currencyCode));
            return "rateRecordMid";

        } catch (IllegalArgumentException ex) {
            return "redirect:/showA/date";
        }
    }

}

