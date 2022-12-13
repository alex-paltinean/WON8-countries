package com.fasttrackit.countriesapplication.model.country;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "something_country")
public class Country {
    //name|capital|population|area|continent|neighbour1~neighbour2
    public Country(long id, String name, City capital, long population, int area, String continent, List<String> neighbours) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbours = neighbours;
    }

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "country_name", unique = true, columnDefinition = "varchar(2000)")
    private String name;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private City capital;
    @OneToMany(mappedBy = "country", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<City> cities;
    @Column
    private long population;
    @Column
    private int area;
    @Column
    private String continent;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Country> neighboursCountries;

    @Transient
    private List<String> neighbours;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
