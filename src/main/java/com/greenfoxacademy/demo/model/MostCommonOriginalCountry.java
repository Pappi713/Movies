package com.greenfoxacademy.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
public class MostCommonOriginalCountry {

    private String Country;

    public MostCommonOriginalCountry(String country){
        countryCodeToCountryName(country);
    }



    public void countryCodeToCountryName(String code){
        Locale loc = new Locale("",code);
        this.Country= loc.getDisplayCountry();
    }


}
