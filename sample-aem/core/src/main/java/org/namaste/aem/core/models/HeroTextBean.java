package org.namaste.aem.core.models;

public class HeroTextBean {
    /** The heading text. */
    private String headingText;

    /** The description. */
    private String description;

    /** Stores Path information */
    private String path ;

    /** Stores Date information */
    private String startDate ;

    /** Stores Checkbox information */
    private String show ;

    /** Stores Select  information */
    private String type;

    public String getType() {
        return this.type;
    }
    /**
     * @param path the path to set
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getShow() {
        return this.show;
    }
    /**
     * @param path the path to set
     */
    public void setShow(String show) {
        this.show = show;
    }

    public String getDate() {
        return this.startDate;
    }
    /**
     * @param path the path to set
     */
    public void setDate(String date) {
        this.startDate = date;
    }
    public String getPath() {
        return this.path;
    }
    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the headingText
     */

    public String getHeadingText() {
        return headingText;
    }
    /**
     * @param headingText the headingText to set
     */
    public void setHeadingText(String headingText) {
        this.headingText = headingText;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
