package org.namaste.aem.core.servlets;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jcr.Node;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.namaste.aem.core.constants.SampleConstants;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.EmptyDataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;

@Component(service = Servlet.class, immediate = true, property = {
		Constants.SERVICE_DESCRIPTION + "= Component dropdown fetching values from Json in JCR using servlet",
		"sling.servlet.resourceTypes=" + SampleConstants.DROPDOWN_JSON_RESOURCE_TYPE,
		"sling.servlet.methods=" + HttpConstants.METHOD_GET })
public class DataSourceJson extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5115380744447013269L;
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceJson.class);

	protected final String OPTIONS_PROPERTY = "options";

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		try {
			ResourceResolver resolver = request.getResourceResolver();
			// set fallback
			request.setAttribute(DataSource.class.getName(), EmptyDataSource.instance());

			Resource datasource = request.getResource().getChild("datasource");
			ValueMap dsProperties = ResourceUtil.getValueMap(datasource);

			String genericListPath = "data";

			LOGGER.info("In Servlet");

			if (genericListPath != null) {
				// Create a node that represents the root node

				String resourcePath = "/content/jsondata/jsondata.txt/jcr:content";
				Node cfNode = request.getResource().getResourceResolver().getResource(resourcePath).adaptTo(Node.class);
				InputStream in = cfNode.getProperty("jcr:data").getBinary().getStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuilder out = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					out.append(line);
				}
				reader.close();

				JSONArray jsonArray = new JSONArray(out.toString());
				ValueMap vm = null;
				List<Resource> optionResourceList = new ArrayList<Resource>();

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject json = jsonArray.getJSONObject(i);
					String Text = "";
					String Value = "";
					vm = new ValueMapDecorator(new HashMap<String, Object>());
					Text = json.getString("text");
					Value = json.getString("value");

					vm.put("value", Value);
					vm.put("text", Text);
					optionResourceList
							.add(new ValueMapResource(resolver, new ResourceMetadata(), "nt:unstructured", vm));

				}
				DataSource ds = new SimpleDataSource(optionResourceList.iterator());
				request.setAttribute(DataSource.class.getName(), ds);

			}/* else {
				LOGGER.info("JSON file is not found ");
			}*/
		} catch (Exception e) {
			LOGGER.info("Error in Get Drop Down Values", e);
		}
	}

}
