package pl.coderslab.converter;

import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;


public class LocalDateConverter implements Converter<String, LocalDate> {


    @Override
    public LocalDate convert(String date) {
        return LocalDate.parse(date);
    }
}
