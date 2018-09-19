package pl.coderslab.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.serialisation.DescriptionTableA;
import pl.coderslab.service.JsonParsingToObjectMid;
import pl.coderslab.service.ParamValidation;


@Controller
@RequestMapping("/rateA")
public class RateSaveTableAController {



    @Autowired
    JsonParsingToObjectMid jsonParsingToObject;

    @Autowired
    ParamValidation paramValidation;

    @Autowired RateSaveTableCController rateSaveTableCController;

    @GetMapping("/form")
    private String form() {

        return "adminForm";
    }

    /** Save method - method allows to save to database bid and ask archival rates. (2005-2017)
     *
     * @param start - min 2005 year, max 2017 year (period beginning)
     * @param end - min 2005, max 2017 year (period end)
     * @return communicate when upload has been finished successfully
     */
    @PostMapping("/save")
    public String downloadRatesfromWebsiteYearly(@RequestParam int start, int end) {
        int convertedStart = paramValidation.startYear(start);
        int convertedEnd = paramValidation.endYear(end);

        int sizeCurrencyList = rateSaveTableCController.currencyList().size();
        for (int a = 0; a < sizeCurrencyList; a++) {
            String currency = rateSaveTableCController.currencyList().get(a);
            for (int i = convertedStart; i < convertedEnd+1; i++) {
                DescriptionTableA description = jsonParsingToObject.jsonParsing(currency, i);
                int size = jsonParsingToObject.listSizeMethodMid(currency, i);
                for (int j = 0; j < size; j++) {
                    try {
                        jsonParsingToObject.setSettersAndSave(description, j,currency);
                    } catch (NullPointerException e) {
                    }
                }
            }
        }
        return "adminActionAnswer";
    }

    /** Save method - method allows to save to database bid and ask rates. (current year)
     *
     * @return communicate when upload has been finished successfully
     */
    @PostMapping("/saveall")
    public String downloadRatesfromWebsiteCurrent() {
        LocalDate date = LocalDate.now();
        int sizeCurrencyList = rateSaveTableCController.currencyList().size();
        for (int a = 0; a < sizeCurrencyList; a++) {
            String currency = rateSaveTableCController.currencyList().get(a);
            DescriptionTableA description = jsonParsingToObject.jsonParsingCurrent(currency, date);
            int size = description.getRates().size();
            for (int j = 0; j < size; j++) {
                try {
                    jsonParsingToObject.setSettersAndSave(description, j, currency);
                } catch (NullPointerException e) {
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
        return "adminActionAnswer";
    }

}
