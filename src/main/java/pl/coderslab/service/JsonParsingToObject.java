package pl.coderslab.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.serialisation.Description;
import pl.coderslab.entity.Rate;
import pl.coderslab.repository.RateRepository;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

@Service
public class JsonParsingToObject {

    @Autowired
    UrlConnection urlConnection;

    @Autowired
    RateRepository rateRepository;

    public Description jsonParsing(String currency, int year) {

        try {
            String urlParsing = urlConnection.run("http://api.nbp.pl/api/exchangerates/rates/c/" + currency + "/" + year + "-01-01/" + year + "-12-31/?format=json");
            ObjectMapper mapper = new ObjectMapper();
            Description description = mapper.readValue(urlParsing, Description.class);
            return description;
        } catch (IOException e) {
            return null;
        }

    }

    public Description jsonParsingDaily(String currency,  LocalDate endDate) {

        try {
            String urlParsing = urlConnection.run("http://api.nbp.pl/api/exchangerates/rates/c/"+currency+"/"+endDate+"/?format=json");
            ObjectMapper mapper = new ObjectMapper();
            Description description = mapper.readValue(urlParsing, Description.class);
            return description;
        } catch (IOException e) {
            return null;
        }

    }

    public Description jsonParsingCurrent(String currency, LocalDate year) {

        try {
            String urlParsing = urlConnection.run("http://api.nbp.pl/api/exchangerates/rates/c/" + currency + "/2018-01-01/"+year+"/?format=json");
            ObjectMapper mapper = new ObjectMapper();
            Description description = mapper.readValue(urlParsing, Description.class);
            return description;
        } catch (IOException e) {
            return null;
        }

    }


    public void setSettersAndSave(Description description, int number, String currency) { //Additional function Validate
        try {
            Rate rates = new Rate();
            Date effectiveDate = description.getRates().get(number).getEffectiveDate();

            if ((rateRepository.findFirstByEffectiveDateEqualsAndCurrencyCodeIsLike(effectiveDate, currency) == null)) {
                rates.setEffectiveDate(effectiveDate); //Set effectiveDate

                String tableName = description.getRates().get(number).getNo();
                rates.setNo(tableName);//Set No.

                Double bid = description.getRates().get(number).getBid();
                rates.setBid(bid); // Set Bid

                Double ask = description.getRates().get(number).getAsk();
                rates.setAsk(ask); //Set Ask

                rates.setCurrencyCode(currency); //Set currency Code

                LocalDateTime dateLocal = LocalDateTime.now();
                rates.setCreated(dateLocal);

                rateRepository.save(rates);
            }

        } catch (NullPointerException e) {

        }
    }


    public int listSizeMethod(String currency, int year) {
        Description description = new Description();
        description = jsonParsing(currency, year);
        if (description == null) {
            return 0;
        } else {
            return description.getRates().size();
        }
    }

}

