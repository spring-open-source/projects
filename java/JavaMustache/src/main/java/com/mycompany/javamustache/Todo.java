package com.mycompany.javamustache;

import java.util.Date;

/**
 *
 * @author hardiku
 */
public class Todo
{

    private String title;
    private String text;
    private boolean done;
    private Date createdOn;
    private Date completedOn;

    public Todo(String title, String text)
    {
        this.title = title;
        this.text = text;
        this.done = false;
        this.createdOn = new Date(System.currentTimeMillis());
        this.completedOn = this.createdOn = new Date(System.currentTimeMillis() + 5522222);;
    }

    public Todo(String title, String text, boolean done, Date createdOn, Date completedOn)
    {
        this.title = title;
        this.text = text;
        this.done = done;
        this.createdOn = createdOn;
        this.completedOn = completedOn;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public boolean isDone()
    {
        return done;
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }

    public Date getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn)
    {
        this.createdOn = createdOn;
    }

    public Date getCompletedOn()
    {
        return completedOn;
    }

    public void setCompletedOn(Date completedOn)
    {
        this.completedOn = completedOn;
    }

}
