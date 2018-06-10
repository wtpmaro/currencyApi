package pl.coderslab.service;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class ParamValidation {

    public int startYear(int start) {

        if (start < 2005) {
            return 2005;
        } else {
            return start;
        }
    }

    public int endYear(int end) {

        if (end > 2018) {
            return 2018;
        } else {
            return end;
        }
    }

    public Date period(Date date) {

        LocalDate date1 = date.toLocalDate();
        LocalDate date2 = date1.minusDays(7);
        return java.sql.Date.valueOf(date2);

    }


}