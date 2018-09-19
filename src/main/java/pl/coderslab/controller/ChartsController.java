package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.repository.RateRepository;
import pl.coderslab.service.CreationObjectToChart;

import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping("/chart")
public class ChartsController {

@Autowired
CreationObjectToChart creationObjectToChart;

@Autowired
    RateRepository rateRepository;

@Autowired
RateSaveTableCController rateSaveTableCController;

    /** 30 day chart for GBP
     *
     * @param model
     * @return 30 day's chart view for GBP
     */
    @GetMapping("")
    public String firstChart(Model model) {

    LocalDate date = LocalDate.now();
    Date today = java.sql.Date.valueOf(date);

    LocalDate date2 = LocalDate.now().minusDays(30);

    Date dayWeekAgo = java.sql.Date.valueOf(date2);

    model.addAttribute("lists",rateSaveTableCController.currencyList());
    model.addAttribute("rates",rateRepository.findAllByEffectiveDateBetweenAndAndCurrencyCodeIsLike(dayWeekAgo,today,"gbp"));
    model.addAttribute("currency","gbp");
        return "chartView";
    }

    /** 30 day chart for indicated currency
     *
     * @param model
     * @param currencyCode
     * @return 30 day's chart view for indicated currency
     */
    @PostMapping("")
    public String chartPerThirtyDaysPeriod(Model model, @RequestParam String currencyCode) {

        LocalDate date = LocalDate.now();
        Date today = java.sql.Date.valueOf(date);

        LocalDate date2 = LocalDate.now().minusDays(30);

        Date dayWeekAgo = java.sql.Date.valueOf(date2);
        model.addAttribute("lists",rateSaveTableCController.currencyList());
        model.addAttribute("rates",rateRepository.findAllByEffectiveDateBetweenAndAndCurrencyCodeIsLike(dayWeekAgo,today,currencyCode));
        model.addAttribute("currency",currencyCode);
        return "chartView";
    }
}
