package org.namaste.aem.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * sample-aem
 *
 * @author RP53993 (ramamity94@gmail.com) created on 8/20/2019 inside the package - org.namaste.aem.core.models
 **/
@Model(adaptables = Resource.class)
public class State {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    @Optional
    private List<Resource> cities;

    @Optional
    private List<City> cityList = new ArrayList<>();

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Inject
    @Optional
    private String state;


    public Logger getLogger() {
        return logger;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setStateList(List<City> cityList) {
        this.cityList = cityList;
    }


    @PostConstruct
    protected void init() {
        logger.debug("In init method of Country model.");
        if (cities != null && !cities.isEmpty()) {
            for (Resource resource : cities) {
                City city = resource.adaptTo(City.class);
                cityList.add(city);
            }
        }
    }


}


