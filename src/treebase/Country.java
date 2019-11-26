package treebase;

import java.util.ArrayList;

public class Country implements Comparable {
    private String name;
    private String continent;
    private float population;
    private String capital;
    private double latitude;
    private double longitude;
    private ArrayList<String> borders;

    /**
     * Constructor for the country object
     *
     * @param name       country's name
     * @param continent  continent containing the country
     * @param population country's population
     * @param capital    country's capital
     * @param latitude   country's latitude
     * @param longitude  country's longitude
     */
    public Country(String name, String continent, float population, String capital, double latitude, double longitude) {
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * RETURNS THE NAME OF THE COUNTRY
     *
     * @return string containing the name of the country
     */
    public String getName() {
        return name;
    }

    /**
     * DEFINES THE NAME OF THE COUNTRY
     *
     * @param name string containing the name of the country
     *             Code to run on file input
     *             Pattern p = Pattern.compile("^[ A-Za-z]+$");
     *             Matcher m = p.matcher(STRING);
     *             boolean b = m.matches();
     */
    public void setName(String name) throws Exception {
        if (name.matches(".*\\d.*")) {
            throw new Exception("Invalid Country Name");
        }
        this.name = name;
    }

    /**
     * RETURNS THE CONTINENT OF THE COUNTRY
     *
     * @return string containing the continent of the country
     * @todo Check for real continents
     */
    public String getContinent() {
        return continent;
    }

    /**
     * DEFINES THE CONTINENT OF THE COUNTRY
     *
     * @param continent string containing the continent where the country is located
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * RETURNS THE POPULATION OF THE COUNTRY
     *
     * @return long containing the population of the country
     */
    public float getPopulation() {
        return population;
    }

    /**
     * DEFINES THE POPULATION OF THE COUNTRY
     *
     * @param population long containing the population of the country
     */
    public void setPopulation(long population) {
        this.population = population;
    }

    /**
     * RETURNS THE CAPITAL OF THE COUNTRY
     *
     * @return string containing the country's capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * DEFINES THE CAPITAL OF THE COUNTRY
     *
     * @param capital string containing the country's capital
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * RETURNS THE LATITUDE OF THE COUNTRY'S CAPITAL
     *
     * @return double containing the country's capital latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * DEFINES THE COUNTRY'S CAPITAL LATITUDE
     *
     * @param latitude double containing the country's capital latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * RETURNS THE LONGITUDE OF THE COUNTRY'S CAPITAL
     *
     * @return double containing the country's capital longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * DEFINES THE COUNTRY'S CAPITAL LONGITUDE
     *
     * @param longitude double containing the country's capital longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     *  Returns the all the countries that form a border with this country
     *
     * @return ArrayList structure containing the border countries' names as String
     */
    public ArrayList<String> getBorders() {return borders;}

    /**
     * Associates a new country to this one as a border country
     *
     * @param cont String with the name of the new border country
     */
    public void addBorder(String cont)   {borders.add(cont);}

    /**
     * Returns the number of border countries for this country
     *
     * @return int value for number of elements in the border countries list
     */
    public int getBorderNumber()  {return borders.size();}

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        Country temp = (Country) o;
        return temp.getName().equals(this.getName());
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((Country) o).name);
    }
}
