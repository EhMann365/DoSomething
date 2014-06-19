package ch.m335.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ch.m335.controllers.R;
import ch.m335.entities.HomeworkItem;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 19.06.2014.
 */
public class HomeworkItemArrayAdapter<HomeworkItem> extends ArrayAdapter<HomeworkItem> {

    //
    // Properties
    //
    private final Context context;
    private final ArrayList<HomeworkItem> homeworkItems;

    //
    // Constructor
    //
    public HomeworkItemArrayAdapter(Context context, ArrayList<HomeworkItem> homeworkItems) {
        super(context, R.layout.row, homeworkItems);

        this.context = context;
        this.homeworkItems = homeworkItems;
    }

    //
    // Methods
    //
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);

        String title = ((ch.m335.entities.HomeworkItem) homeworkItems.get(position)).getTitle();
        String subject = ((ch.m335.entities.HomeworkItem) homeworkItems.get(position)).getSubject();
        String formattedDueDate = new SimpleDateFormat("dd.MM.yyyy").format(((ch.m335.entities.HomeworkItem) homeworkItems.get(position)).getDueDate());

        ((TextView) rowView.findViewById(R.id.tvTitle)).setText(title);
        ((TextView) rowView.findViewById(R.id.tvSubject)).setText(subject);
        ((TextView) rowView.findViewById(R.id.tvDueDate)).setText(formattedDueDate);

        return rowView;
    }
}