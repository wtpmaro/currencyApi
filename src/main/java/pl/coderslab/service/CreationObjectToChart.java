package pl.coderslab.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Rate;
import pl.coderslab.repository.RateRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreationObjectToChart {


    @Autowired
    RateRepository rateRepository;


    public String object() {

        Gson gsonObj = new Gson();
        Map<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        String dataPoints = null;

        List<Rate> rate = rateRepository.findAllByAskGreaterThanAndCurrencyCodeIsLike(6.20, "gbp");


        for (int i = 0; i < rate.size(); i++) {
            map = new HashMap<Object, Object>();
            map.put("x", rate.get(i).getCurrencyCode());
            map.put("y", rate.get(i).getAsk());
            list.add(map);
            dataPoints = gsonObj.toJson(list);
        }

        return dataPoints;
    }
    public List<Double> ask() {

        List<Rate> rate = rateRepository.findAllByAskGreaterThanAndCurrencyCodeIsLike(6.20, "gbp");

        List<Double> ask = new ArrayList<>();
        for(int i =0; i < rate.size();i++) {
        ask.add(rate.get(i).getAsk());
        }
        return ask;
        }

    }

