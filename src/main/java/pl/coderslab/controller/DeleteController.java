package pl.coderslab.controller;

import org.codehaus.jackson.map.ser.StdSerializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.repository.RateMidRepository;
import pl.coderslab.repository.RateRepository;

import java.sql.Date;

@Controller
@RequestMapping("/delete")
public class DeleteController {

    @Autowired
    RateRepository rateRepository;

    @Autowired
    RateMidRepository rateMidRepository;

    @Autowired
    RateSaveTableCController rateSaveTableCController;

    @GetMapping("/form")
    public String deleteForm(Model model) {
        model.addAttribute("lists",rateSaveTableCController.currencyList());
        return "deleteForm";
    }

    @PostMapping("/currency")
    public String delete(@RequestParam String currencyCode) {
        rateRepository.deleteAllByCurrencyCodeLike(currencyCode);
        rateMidRepository.deleteAllByCurrencyCodeLike(currencyCode);
        return "redirect:/delete/form";
    }

    @PostMapping("/period")
    public String deleteFromPeriod(@RequestParam Date startDate, Date endDate) {
        rateRepository.deleteAllByEffectiveDateBetween(startDate, endDate);
        rateMidRepository.deleteAllByEffectiveDateBetween(startDate, endDate);
        return "redirect:/delete/form";
    }

}
