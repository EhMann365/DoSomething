package ch.m335.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Joshua on 18.06.2014.
 */
public class HomeworkItem implements Serializable {
    //
    // Properties
    //
    private int id;
    private String title;
    private String subject;
    private Date dueDate;   // Date until the homework has to be made
    private String picture; // The file uri to the picture in the storage (SDCard)
    private String comment;

    //
    // Constructors
    //
    public HomeworkItem() { }

    /**
     * Constructor is used when items were are read from database
     * @param id
     * @param title
     * @param subject
     * @param duedate
     * @param picture
     * @param comment
     */
    public HomeworkItem(int id, String title, String subject, Date duedate, String picture, String comment) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.dueDate = duedate;
        this.picture = picture;
        this.comment = comment;
    }

    /**
     * Constructor is used when a new homework item is created from the GUI
     * @param title
     * @param subject
     * @param dueDate
     * @param picture
     * @param comment
     */
    public HomeworkItem(String title, String subject, Date dueDate, String picture, String comment) {
        this.title = title;
        this.subject = subject;
        this.dueDate = dueDate;
        this.picture = picture;
        this.comment = comment;
    }

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

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
