package com.app.entities;


import org.bson.json.StrictJsonWriter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CarnetIdConverter implements Converter<CarnetId,String> {

    @Override
    public String convert(CarnetId source) {
        return source.getIdClient() + "-" + source.getIdSport();
    }
    
    public CarnetId convert(String source) {
        String[] attributes = source.split("-");
        return new CarnetId(attributes[0], attributes[1]);
    }
}
