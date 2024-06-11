package com.aa.coolreads.Book.dto;

import com.aa.coolreads.User.models.DefaultBookshelf;
import com.aa.coolreads.User.models.Gender;

import java.util.List;

enum AgeBin {
    child,
    teen,
    youngAdult,
    adult,
    elders
}

class Slice {

    private Double percentage;

    private DefaultBookshelf defaultBookshelf;

    public Slice(Double percentage, DefaultBookshelf defaultBookshelf) {
        this.percentage = percentage;
        this.defaultBookshelf = defaultBookshelf;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}

class GenderSlice extends Slice {

    private Gender gender;

    public GenderSlice(Double percentage, DefaultBookshelf defaultBookshelf, Gender gender) {
        super(percentage, defaultBookshelf);
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

class AgeSlice extends Slice {

    private AgeBin ageBin;

    public AgeSlice(Double percentage, DefaultBookshelf defaultBookshelf, AgeBin ageBin) {
        super(percentage, defaultBookshelf);
        this.ageBin = ageBin;
    }

    public AgeBin getAgeBin() {
        return ageBin;
    }

    public void setAgeBin(AgeBin ageBin) {
        this.ageBin = ageBin;
    }
}

class CountrySlice extends Slice {

    private String Country;

    public CountrySlice(Double percentage, DefaultBookshelf defaultBookshelf, String country) {
        super(percentage, defaultBookshelf);
        Country = country;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}

public class StatisticsPieChartDTO {

    private List<Slice> slices;

    public StatisticsPieChartDTO(List<Slice> slices) {
        this.slices = slices;
    }

    public List<Slice> getSlices() {
        return slices;
    }

    public void setSlices(List<Slice> slices) {
        this.slices = slices;
    }
}
