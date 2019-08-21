package org.namaste.aem.core.service;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "My Service Configuration", description = "Service Configuration")
public @interface MyServiceConfiguration {

    @AttributeDefinition(name = "Config Value", description = "config value")
    String configValue();

    @AttributeDefinition(name = "Multiple values", description = "Multi Configuration Values")
    String [] getStringValues();

    @AttributeDefinition(name = "NumberValue", description = "Number values", type = AttributeType.INTEGER)
    int getNumberValue();
}
