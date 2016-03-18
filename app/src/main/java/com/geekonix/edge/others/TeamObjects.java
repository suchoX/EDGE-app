package com.geekonix.edge.others;

/**
 * Created by sucho on 18/3/16.
 */
public class TeamObjects
{
    String name;
    String link;
    public TeamObjects(String name, String link)
    {
        this.name = name;
        this.link = link;
    }

    public String getName()
    {
        return name;
    }

    public String getImage()
    {
        return link;
    }
}
