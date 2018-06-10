package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.repository.RateRepository;
import pl.coderslab.service.ParamValidation;
import java.sql.Date;


@Controller
@RequestMapping("/show")
public class RateShowController {

    @Autowired
    RateRepository rateRepository;

    @Autowired
    RateSaveTableCController rateSaveTableCController;

    @Autowired
    ParamValidation paramValidation;




    @PostMapping("/askGreater")
    public String showAskGreater (Model model, @RequestParam Double askPrice, String currencyCode) {
        if (askPrice == null) {
            return "redirect:/show/date";
        }
            model.addAttribute("rates", rateRepository.findAllByAskGreaterThanAndCurrencyCodeIsLike(askPrice, currencyCode));
            return "rateTable";

        }

    @PostMapping("/bidGreater")
    public String showBidGreater (Model model, @RequestParam Double bidPrice, String currencyCode) {
        if (bidPrice == null) {
            return "redirect:/show/date";
        }

        model.addAttribute("rates",rateRepository.findAllByBidGreaterThanAndCurrencyCodeIsLike(bidPrice, currencyCode));
        return "rateTable";
    }

    @PostMapping("/period")
    public String ratesPerPeriod (Model model, @RequestParam String startPeriod, String endPeriod, String currencyCode) {
        try {
            Date from = Date.valueOf(startPeriod);
            Date to = Date.valueOf(endPeriod);

            model.addAttribute("rates",rateRepository.findAllByEffectiveDateBetweenAndAndCurrencyCodeIsLike(from, to, currencyCode));
            return "rateTable";

        } catch (IllegalArgumentException ex) {
            return "redirect:/show/date";
        }
    }

    @PostMapping("/periodAskBetween")
    public String ratesPerPeriodAskBetween (Model model, @RequestParam Double lowBarrier, Double highBarrier, String currencyCode) {
        if ((lowBarrier == null) || (highBarrier ==null))  {
            return "redirect:/show/date";
        }
        model.addAttribute("rates",rateRepository.findAllByAskBetweenAndCurrencyCodeLike(lowBarrier, highBarrier, currencyCode));
        return "rateTable";
    }

    @PostMapping("/periodBidBetween")
    public String ratesPerPeriodBidBetween (Model model, @RequestParam Double lowBarrier, Double highBarrier, String currencyCode) {
        if ((lowBarrier == null) || (highBarrier ==null))  {
            return "redirect:/show/date";
        }
        model.addAttribute("rates",rateRepository.findAllByBidBetweenAndCurrencyCodeLike(lowBarrier, highBarrier, currencyCode));
        return "rateTable";
    }

    @PostMapping("/askMaxPerPeriod")
    public String askMaxPerPeriod (Model model, @RequestParam String startDate, String endDate, String currencyCode) {
        try {
            Date from = Date.valueOf(startDate);
            Date to = Date.valueOf(endDate);

            model.addAttribute("rates",rateRepository.findFirstByEffectiveDateBetweenAndCurrencyCodeLikeOrderByAskDesc(from, to, currencyCode));
            return "rateRecord";


        } catch (IllegalArgumentException ex) {
            return "redirect:/show/date";
        }

    }

    @PostMapping("/bidMaxPerPeriod")
    public String bidMaxPerPeriod (Model model, @RequestParam String startDate, String endDate, String currencyCode) {
        try {
            Date from = Date.valueOf(startDate);
            Date to = Date.valueOf(endDate);

            model.addAttribute("rates",rateRepository.findFirstByEffectiveDateBetweenAndCurrencyCodeLikeOrderByBidDesc(from, to, currencyCode));
            return "rateRecord";

        } catch (IllegalArgumentException ex) {
            return "redirect:/show/date";
        }
    }

    @PostMapping("/askMinPerPeriod")
    public String askMinPerPeriod (Model model, @RequestParam String startDate, String endDate, String currencyCode) {
        try {
            Date from = Date.valueOf(startDate);
            Date to = Date.valueOf(endDate);

            model.addAttribute("rates",rateRepository.findFirstByEffectiveDateBetweenAndCurrencyCodeLikeOrderByAskAsc(from, to, currencyCode));
            return "rateRecord";

        } catch (IllegalArgumentException ex) {
            return "redirect:/show/date";
        }
    }

    @PostMapping("/bidMinPerPeriod")
    public String bidMinPerPeriod (Model model, @RequestParam String startDate, String endDate, String currencyCode) {
        try {
            Date from = Date.valueOf(startDate);
            Date to = Date.valueOf(endDate);

            model.addAttribute("rates",rateRepository.findFirstByEffectiveDateBetweenAndCurrencyCodeLikeOrderByBidAsc(from, to, currencyCode));
            return "rateRecord";

        } catch (IllegalArgumentException ex) {
            return "redirect:/show/date";
        }
    }


    @GetMapping("/date")
    public String show(Model model) {
        model.addAttribute("lists",rateSaveTableCController.currencyList());
        return "searchTableC";
    }

    @PostMapping("/date")
    public String showView(Model model, @RequestParam String startDate, String currencyCode) {

        try {
            Date from = Date.valueOf(startDate);
            Date endDate = paramValidation.period(from);

            model.addAttribute("rates",rateRepository.findFirstByEffectiveDateBetweenAndCurrencyCodeIsLikeOrderByEffectiveDateDesc(endDate,from,currencyCode));
            return "rateRecord";

        } catch (IllegalArgumentException ex) {
            return "redirect:/show/date";
        }
    }

    @GetMapping("/table")
    public String tableNewest(Model model) {

        Date date = rateRepository.findFirstByOrderByEffectiveDateDesc().getEffectiveDate();
        String tableName = rateRepository.findFirstByOrderByEffectiveDateDesc().getNo();
        model.addAttribute("date",date);
        model.addAttribute("table",tableName);
        model.addAttribute("rates",rateRepository.findAllByEffectiveDateEqualsOrderByCurrencyCodeAsc(date));
        return "rateTableNew";
    }


}
