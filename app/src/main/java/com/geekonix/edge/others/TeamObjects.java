package com.geekonix.edge.others;

/**
 * Created by sucho on 18/3/16.
 */
public class TeamObjects
{
    String name;
    String position;
    String link;
    String number;
    String fblink;
    public TeamObjects(String name,String position, String link, String number, String fblink)
    {
        this.name = name;
        this.position = position;
        this.link = link;
        this.number = number;
        this.fblink = fblink;
    }

    public String getName()
    {
        return name;
    }

    public String getImage()
    {
        return link;
    }

    public String getPosition() { return position; }

    public String getNumber() { return number; }

    public String getFblink() { return fblink; }
}
