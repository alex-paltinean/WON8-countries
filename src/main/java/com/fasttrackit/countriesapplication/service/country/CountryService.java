package com.fasttrackit.countriesapplication.service.country;

import com.fasttrackit.countriesapplication.exception.ResourceNotFoundException;
import com.fasttrackit.countriesapplication.model.country.City;
import com.fasttrackit.countriesapplication.model.country.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryReader countryReader, CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        countryRepository.saveAll(countryReader.getCountries());
        System.out.println("Finished reading countries");
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public List<Country> getByContinent(String continent) {
        return countryRepository.findByContinentByQuery(continent);
    }

    //get countries that neighbor X but not neighbor Y :-> returns list of Country objects
    public List<Country> getByNeighbour(String includedNeighbour, String excludedNeighbour) {
        return getAllCountries().stream()
                .filter(c -> c.getNeighbours() != null && c.getNeighbours().size() > 0)
                .filter(c -> c.getNeighbours().contains(includedNeighbour) && !c.getNeighbours().contains(excludedNeighbour))
                .toList();
    }

    public Country getById(int id) {
        return countryRepository.findById((long) id)
                .orElseThrow(() -> new ResourceNotFoundException("Country missing", id));
    }

    public Country deleteById(int id) {
        Country countryToBeDeleted = getById(id);
        countryRepository.deleteById((long) id);
        return countryToBeDeleted;
    }

    public Country add(Country country) {
        return countryRepository.save(country);
    }

    public Country update(int id, Country country) {
        Country countryToBeUpdated = getById(id);
        countryToBeUpdated.setArea(country.getArea());
        countryToBeUpdated.setCapital(country.getCapital());
        countryToBeUpdated.setContinent(country.getContinent());
        countryToBeUpdated.setName(country.getName());
        countryToBeUpdated.setNeighbours(country.getNeighbours());
        countryToBeUpdated.setPopulation(country.getPopulation());
        return countryRepository.save(countryToBeUpdated);
    }

    public List<Country> getCountriesFiltered(String continent, Long minPopulation, Long maxPopulation) {
        return countryRepository.getByContinentAndMinPopulationAndMaxPopulation(continent, minPopulation, maxPopulation);
    }

    public Country patch(long id, String capital, long diffPopulation) {
        Country countryToBeUpdated = getById((int) id);
        countryToBeUpdated.setCapital(new City(capital));
        countryToBeUpdated.setPopulation(countryToBeUpdated.getPopulation() + diffPopulation);
        return countryRepository.save(countryToBeUpdated);
    }

    public Country addCityToCountry(int id, City city) {
        Country country = getById(id);
        city.setCountry(country);
        country.getCities().add(city);
        return countryRepository.save(country);
    }

    public Country addNeighbourToCountry(int id, int neighbourId) {
        Country country = getById(id);
        Country neighbour = getById(neighbourId);
        country.getNeighboursCountries().add(neighbour);
        neighbour.getNeighboursCountries().add(country);
        return countryRepository.save(country);
    }
}
