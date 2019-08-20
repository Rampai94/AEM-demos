package org.namaste.aem.core.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class MultifieldToJson {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultifieldToJson.class);

    @Optional
    @RequestAttribute
    private String name;

    private Resource resource;

    private static String jsonString;

    @Inject
    private SlingHttpServletRequest request;

    private List<String> propertiesToIgnore = new ArrayList<>();

    @PostConstruct
    public void init() {
        // let's test if we are getting the param in here.
        LOGGER.info("request param name :: {}", name);
        setPropertiesToIgnore();
        resource = request.getResource();
    }

    public String getJsonString() {
        return jsonString;
    }

    private void setPropertiesToIgnore() {
        propertiesToIgnore.add("jcr:primaryType");
        propertiesToIgnore.add("sling:resourceType");
        propertiesToIgnore.add("jcr:lastModifiedBy");
        propertiesToIgnore.add("jcr:lastModified");
        propertiesToIgnore.add("jcr:createdBy");
        propertiesToIgnore.add("jcr:created");
    }

    public String getComponentJson() throws RepositoryException {
        return checkForChildren(resource).toString();
    }

    public String getJsonMulti() throws RepositoryException {
        Resource childResource = request.getResource().getChild(name);
        if (childResource != null) {
            JsonObject resourceJson = checkForChildren(childResource);
            return resourceJson.toString();
        }
        return StringUtils.EMPTY;
    }

    private JsonObject checkForChildren(Resource resource) throws RepositoryException {
        Node resNode = resource.adaptTo(Node.class);
        JsonObject resourceJson = new JsonObject();
        if (null != resNode) {
            for (PropertyIterator resProp = resNode.getProperties(); resProp.hasNext(); ) {
                Property property = resProp.nextProperty();
                if (!propertiesToIgnore.contains(property.getName()))
                    resourceJson.addProperty(property.getName(), property.getValue().getString());
            }
            if (resource.hasChildren()) {
                JsonArray multiJson = new JsonArray();
                for (Iterator<Resource> children = resource.listChildren(); children.hasNext(); ) {
                    Resource childResource = children.next();
                    JsonObject obj = checkForChildren(childResource);
                    //if resource has children, list children as json objects.
                    //but for multi use JsonArray
                    if (childResource.getName().startsWith("item"))
                        multiJson.add(obj);
                    else
                        resourceJson.add(childResource.getName(), obj);
                }
                if (multiJson.size() > 0)
                    resourceJson.add("items", multiJson);
            }
        }
        return resourceJson;
    }
}
