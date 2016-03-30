package com.geekonix.edge.others;

/**
 * Created by sucho on 28/3/16.
 */
public class SponsorObjects
{
    String position;
    String link;

    String url;

    public SponsorObjects(String position, String link, String url)
    {
        this.position = position;
        this.link = link;
        this.url = url;
    }


    public String getImage()
    {
        return link;
    }

    public String getPosition() { return position; }

    public String getUrl() { return url; }


}
