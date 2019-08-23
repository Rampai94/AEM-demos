package org.namaste.aem.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

/**
 * sample-aem
 *
 * @author RP53993 (ramamity94@gmail.com) created on 8/20/2019 inside the package - org.namaste.aem.core.models
 **/
@Model(adaptables = Resource.class)
public class City {

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Inject
    @Optional
    private String city;

}
