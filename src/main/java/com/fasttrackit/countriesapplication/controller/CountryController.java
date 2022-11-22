package com.fasttrackit.countriesapplication.controller;

import com.fasttrackit.countriesapplication.model.Country;
import com.fasttrackit.countriesapplication.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("countries") // http://host:port/countries
public class CountryController {

    private final CountryService countryService;

    @GetMapping // GET http://host:port/countries?continent={continent}&sortBy=continent&sortDirection=DESC&limit=10&offset=30
    public List<Country> getAll(@RequestParam(required = false) String continent) {
        System.out.println("Requested all countries");
        if (continent != null) {
            return countryService.getByContinent(continent);
        } else {
            return countryService.getAllCountries();
        }
    }

    @GetMapping("{id}") // GET http://host:port/countries/3
    public Country getById(@PathVariable int id) {
        return countryService.getById(id);
    }

    @DeleteMapping("{id}") // DELETE http://host:port/countries/3
    public Country deleteById(@PathVariable int id) {
        return countryService.deleteById(id);
    }

    @PostMapping // POST http://host:port/countries
    public Country add(@RequestBody Country country) {
        return countryService.add(country);
    }

    @PutMapping("{id}")
    public Country update(@PathVariable int id, @RequestBody Country country) {
        return countryService.update(id, country);
    }
}
