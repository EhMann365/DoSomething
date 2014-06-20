package ch.m335.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import ch.m335.entities.HomeworkItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 18.06.2014.
 */
public class HomeworkDao extends SQLiteOpenHelper {

    public HomeworkDao(Context applicationcontext) {
        super(applicationcontext, "androidsqlite.db", null, 1);
        Log.d("DoSomething: ", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE homework (id INTEGER PRIMARY KEY, title TEXT, subject TEXT, duedate TEXT, picture TEXT, comment TEXT)";
        db.execSQL(query);
        Log.d("DoSomething: ", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS homework");
        onCreate(db);
    }

    public void insertHomework(HomeworkItem homeworkItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", homeworkItem.getTitle());
        values.put("subject", homeworkItem.getSubject());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(homeworkItem.getDueDate());
        values.put("duedate", dateString);
        values.put("picture", homeworkItem.getPicture());
        values.put("comment", homeworkItem.getComment());

        db.insert("homework", null, values);
        db.close();
    }

    public void updateHomework(HomeworkItem homeworkItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", homeworkItem.getTitle());
        values.put("subject", homeworkItem.getSubject());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(homeworkItem.getDueDate());
        values.put("duedate", dateString);
        values.put("picture", homeworkItem.getPicture());
        values.put("comment", homeworkItem.getComment());

        db.update("homework", values, "id=?", new String[] {String.valueOf(homeworkItem.getId())});
        db.close();
    }

    public void deleteHomework(HomeworkItem homeworkItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("homework", "id=?", new String[] {String.valueOf(homeworkItem.getId())});
        db.close();
    }

    @SuppressWarnings("UnusedDeclaration")
    public HomeworkItem selectHomework(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("homework", new String[] {"id", "title", "subject", "duedate", "picture", "comment"}, "id=?", new String[] {String.valueOf(id)}, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        HomeworkItem hwi;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // TODO: Inform user when exception was thrown
            java.util.Date date = sdf.parse(cursor.getString(3));
            hwi = new HomeworkItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2), date, cursor.getString(4), cursor.getString(5));
            db.close();
            return hwi;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        db.close();

        return null;
    }

    public List<HomeworkItem> selectAllHomeworks() {
        ArrayList<HomeworkItem> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM homework ORDER BY duedate";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                HomeworkItem hwi = new HomeworkItem();
                hwi.setId(cursor.getInt(0));
                hwi.setTitle(cursor.getString(1));
                hwi.setSubject(cursor.getString(2));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    java.util.Date date = sdf.parse(cursor.getString(3));
                    hwi.setDueDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                hwi.setPicture(cursor.getString(4));
                hwi.setComment(cursor.getString(5));
                list.add(hwi);
            } while (cursor.moveToNext());
        }
        db.close();

        return list;
    }
}
