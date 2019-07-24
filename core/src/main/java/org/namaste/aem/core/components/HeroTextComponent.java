package org.namaste.aem.core.components;

import com.adobe.cq.sightly.WCMUsePojo;
import org.namaste.aem.core.models.HeroTextBean;

public class HeroTextComponent extends WCMUsePojo {

    /**
     * The Hero Text Bean
     */
    private HeroTextBean heroTextBean = null;

    @Override
    public  void activate(){
        heroTextBean = new HeroTextBean();

        //Get the values that the author entered into the AEM dialog
        String heading = getProperties().get("heading", "");
        String description = getProperties().get("description","");
        String path = getProperties().get("path","");
        String date = getProperties().get("startDate","");
        String show = getProperties().get("show","");
        String type = getProperties().get("size","");

        //Set the Bean with all the dialog values
        heroTextBean.setHeadingText(heading);
        heroTextBean.setDescription(description);
        heroTextBean.setPath(path);
        heroTextBean.setDate(date);
        heroTextBean.setShow(show);
        heroTextBean.setType(type);
        
    }

    public HeroTextBean getHeroTextBean() {
        return this.heroTextBean;
    }


}
