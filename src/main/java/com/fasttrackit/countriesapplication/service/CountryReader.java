package com.fasttrackit.countriesapplication.service;

import com.fasttrackit.countriesapplication.model.Country;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CountryReader {
    @Value("${file.countries}")
    private String fileCountriesPath;

    @Value("${max.countries:30}")
    private int maxCountries;

    public static int countryId = 0;

    public List<Country> getCountries() {
        try {
            return Files.lines(Path.of(fileCountriesPath))
                    .map(this::lineToCountry)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Country lineToCountry(String line) {
        String[] countryParts = line.split("\\|");
        return new Country(countryId++, countryParts[0], countryParts[1], Long.parseLong(countryParts[2]),
                Integer.parseInt(countryParts[3]), countryParts[4],
                countryParts.length > 5 ? parseNeighbours(countryParts[5]) : List.of());
    }

    private List<String> parseNeighbours(String neighboursString) {
        return Arrays.stream(neighboursString.split("~")).toList();
    }
}
