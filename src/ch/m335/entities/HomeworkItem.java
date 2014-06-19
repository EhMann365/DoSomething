package ch.m335.entities;

import java.util.Date;

/**
 * Created by Joshua on 18.06.2014.
 */
public class HomeworkItem {
    //
    // Properties
    //
    private int id;
    private String title;
    private String subject;
    private Date dueDate;
    // TODO: Find datatype for saving picture out
    private Object picture;
    private String comment;

    //
    // Constructors
    //
    public HomeworkItem(int id, String title, String subject, Date duedate, Object picture, String comment) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.dueDate = duedate;
        this.picture = picture;
        this.comment = comment;
    }

    public HomeworkItem(String title, String subject, Date dueDate, Object picture, String comment) {
        this.title = title;
        this.subject = subject;
        this.dueDate = dueDate;
        this.picture = picture;
        this.comment = comment;
    }

    public HomeworkItem() {}

    //
    // Getter / Setter
    //
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


    public Object getPicture() {
        return picture;
    }
    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
