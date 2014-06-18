package ch.m335.dao;

import ch.m335.entities.HomeworkItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Admin on 18.06.2014.
 */
public class HomeworkDao {
    public ArrayList<HomeworkItem> getHomeworkItems() {
        return getDummyData();
    }

    private ArrayList<HomeworkItem> getDummyData() {
        ArrayList<HomeworkItem> hwItems = new ArrayList<HomeworkItem>();
        hwItems.add(new HomeworkItem(1, "Aufgabe 1", "Mathematik"));
        hwItems.add(new HomeworkItem(2, "Lese Seite 2 - 3", "Deutsch"));
        hwItems.add(new HomeworkItem(3, "Springe umher!", "Turnen"));
        hwItems.add(new HomeworkItem(4, "Baue Zeitmaschine", "Geschichte"));
        hwItems.add(new HomeworkItem(5, "Lade die Queen ein", "Englisch"));
        hwItems.add(new HomeworkItem(6, "Widerlege die Relativitätstheorie", "Physik"));

        return hwItems;
    }
}
