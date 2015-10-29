package br.com.iesb.posgraduacao.activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.ieb.posgraduacao.activity.R;
import br.com.iesb.posgraduacao.util.Menu;
/**
 * Created by braiannunes on 10/26/15.
 */
public class MenuAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private final String[] menuNames;
    private final Context context;
    private final int[] imageId;

    public MenuAdapter(final MainActivity mainActivity, final String[] menuNames, final int[] menuImg) {
        this.menuNames = menuNames;
        context = mainActivity;
        imageId = menuImg;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return menuNames.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.menu_list, null);
        holder.textView = (TextView) rowView.findViewById(R.id.textView1);
        holder.imageView = (ImageView) rowView.findViewById(R.id.imageView1);

        holder.textView.setText(menuNames[position]);
        holder.imageView.setImageResource(imageId[position]);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click: " + menuNames[position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Menu.getActivity(imageId[position]));
                v.getContext().startActivity(intent);
            }
        });

        return rowView;
    }

    public class Holder {
        TextView textView;
        ImageView imageView;
    }

}
