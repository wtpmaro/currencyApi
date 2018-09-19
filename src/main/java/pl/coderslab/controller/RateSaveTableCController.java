package pl.coderslab.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.serialisation.Description;
import pl.coderslab.service.JsonParsingToObject;
import pl.coderslab.service.ParamValidation;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/rateC")
public class RateSaveTableCController {


    @Autowired
    JsonParsingToObject jsonParsingToObject;

    @Autowired
    ParamValidation paramValidation;

    /** Save method - method allows to save to database mid archival rates. (2005-2017)
     *
     * @param start - min 2005 year, max 2017 year (period beginning)
     * @param end - min 2005, max 2017 year (period end)
     * @return communicate when upload has been finished successfully
     */

    @PostMapping("/save")
    public String downloadRatesfromWebsiteYearly(@RequestParam int start, int end) {
        int convertedStart = paramValidation.startYear(start);
        int convertedEnd = paramValidation.endYear(end);
        int sizeCurrencyList = currencyList().size();
        for (int a = 0; a < sizeCurrencyList; a++) {
            String currency = currencyList().get(a);
            for (int i = convertedStart; i < convertedEnd + 1; i++) {
                Description description = jsonParsingToObject.jsonParsing(currency, i);
                int size = jsonParsingToObject.listSizeMethod(currency, i);
                for (int j = 0; j < size; j++) {
                    try {
                        jsonParsingToObject.setSettersAndSave(description, j, currency);
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
        int sizeCurrencyList = currencyList().size();
        for (int a = 0; a < sizeCurrencyList; a++) {
            String currency = currencyList().get(a);
                Description description = jsonParsingToObject.jsonParsingCurrent(currency, date);
                int size = description.getRates().size();
                for (int j = 0; j < size; j++) {
                    try {
                        jsonParsingToObject.setSettersAndSave(description, j, currency);
                    } catch (NullPointerException e) {
                    }
                }
            }
        return "adminActionAnswer";
        }

    /** Currency codes
     *
     * @return list of currency code that are processed.
     */
    public final List<String> currencyList() {
        List<String> list = new ArrayList<>();
        list.add("gbp");
        list.add("cad");
        list.add("chf");
        list.add("sek");
        list.add("jpy");
        list.add("aud");
        list.add("dkk");
        list.add("czk");
        list.add("eur");
        list.add("huf");
        list.add("nok");
        return list;
    }
}

