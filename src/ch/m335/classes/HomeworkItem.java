package ch.m335.classes;

import java.util.Date;

/**
 * Created by Brian on 18.06.2014.
 */
public class HomeworkItem {

    //
    // Properties
    //
    private String title;
    private String subject;
    private Date dueDate;
    // TODO: Find datatype for saving picture out
    private Object picture;
    private String comment;

    //
    // Constructors
    //
    public HomeworkItem(String title, String subject) {
        this.title = title;
        this.subject = subject;
        this.dueDate = new Date();
        this.picture = new Object();
        this.comment = "";
    }

    public HomeworkItem(String title, String subject, Date dueDate, Object pichture, String comment) {
        this.title = title;
        this.subject = subject;
        this.dueDate = dueDate;
        this.picture = pichture;
        this.comment = comment;
    }

    //
    // Getter / Setter
    //
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

    public Object getPichture() {
        return picture;
    }
    public void setPichture(Object pichture) {
        this.picture = pichture;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
