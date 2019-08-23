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

/**
 * The type Country.
 */
@Model(adaptables = Resource.class)
public class Country {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Inject
    @Optional
    private String country;

    @Inject
    @Optional
    private List<Resource> states;

    @Optional
    private List<State> stateList = new ArrayList<>();


    public List<State> getStateList() {
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }


    @PostConstruct
    protected void init() {
        logger.debug("In init method of Country model.");
        if (states != null && !states.isEmpty()) {
            for (Resource resource : states) {
                State state = resource.adaptTo(State.class);
                stateList.add(state);
            }
        }
    }

}
