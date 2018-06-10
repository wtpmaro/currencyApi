package pl.coderslab.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.serialisation.DescriptionTableA;
import pl.coderslab.entity.RateMid;
import pl.coderslab.repository.RateMidRepository;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

@Service
public class JsonParsingToObjectMid {

    @Autowired
    UrlConnection urlConnection;

    @Autowired
    RateMidRepository rateRepository;

    public DescriptionTableA jsonParsing(String currency, int year) {

        try {
            String urlParsing = urlConnection.run("http://api.nbp.pl/api/exchangerates/rates/a/" + currency + "/" + year + "-01-01/" + year + "-12-31/?format=json");
            ObjectMapper mapper = new ObjectMapper();
            DescriptionTableA description = mapper.readValue(urlParsing, DescriptionTableA.class);
            return description;
        } catch (IOException e) {
            return null;
        }
    }

    public DescriptionTableA jsonParsingCurrent(String currency, LocalDate year) {

        try {
            String urlParsing = urlConnection.run("http://api.nbp.pl/api/exchangerates/rates/a/" + currency + "/2018-01-01/"+year+"/?format=json");
            ObjectMapper mapper = new ObjectMapper();
            DescriptionTableA description = mapper.readValue(urlParsing, DescriptionTableA.class);
            return description;
        } catch (IOException e) {
            return null;
        }

    }

    public void setSettersAndSave(DescriptionTableA description, int number, String currency) { //Additional function Validate
        try {
            RateMid rates = new RateMid();
            Date effectiveDate = description.getRates().get(number).getEffectiveDate();

            if ((rateRepository.findFirstByEffectiveDateEqualsAndCurrencyCodeIsLike(effectiveDate, currency) == null)) {
                rates.setEffectiveDate(effectiveDate); //Set effectiveDate


                String tableName = description.getRates().get(number).getNo();
                rates.setNo(tableName);//Set No.

                Double mid = description.getRates().get(number).getMid();
                rates.setMid(mid); // Set Mid

                rates.setCurrencyCode(currency); //Set currency Code

                LocalDateTime dateLocal = LocalDateTime.now();
                rates.setCreated(dateLocal);

                rateRepository.save(rates);
            }

        } catch (NullPointerException e) {

        }
    }


    public int listSizeMethodMid(String currency, int year) {
        DescriptionTableA description = new DescriptionTableA();
        description = jsonParsing(currency, year);
        if (description == null) {
            return 0;
        } else {
            return description.getRates().size();
        }
    }
}


