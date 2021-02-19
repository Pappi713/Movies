package com.greenfoxacademy.demo.service;


import com.greenfoxacademy.demo.api.MovieAPI;
import com.greenfoxacademy.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MovieService {

    private MovieAPI movieApi = MovieServiceGenerator.createService(MovieAPI.class);
    private String movieApiKey = "adb74077c3de28b2c5a9406027dcb606";


    public Genres callGenres() throws IOException {
        Call <Genres> call = movieApi.getGenres(movieApiKey);

        Response<Genres> response = call.execute();
        return response.body();
    }

    public TVCollection getTVShows() throws IOException {
        Call<TVCollection> call =movieApi.getTVShows(movieApiKey);

        Response<TVCollection> response =call.execute();
        System.out.println(response.body().getResults().size());
        return response.body();
    }
    public String mostCommonOriginCountry() throws IOException{
        TVCollection tvCollection= getTVShows();
        List<String> countrylist = originalCountries(tvCollection);
        Map<String, Integer> countryHM =hashMapper(countrylist);
        return mostCommonOriginalCountry(countryHM);
    }



    public List <String> originalCountries(TVCollection collection){
        List <TV> tvs = collection.getResults();
        List<List<String>> countires =tvs.stream().map(TV::getOrigin_country).collect(Collectors.toList());
        return countires.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static Map hashMapper(List<String> country) {
        Map<String, Integer> hm = new HashMap<>();
        for (String s : country) {
            if (!hm.containsKey((String) s)) {
                hm.put(s, 1);
            } else {
                hm.put(s, hm.get(s) + 1);
            }
        }
        return hm;
    }

    public String mostCommonOriginalCountry( Map<String, Integer> hm){
        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : hm.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }
}
