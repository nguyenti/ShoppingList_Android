package hu.ait.tiffanynguyen.shoppinglist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hu.ait.tiffanynguyen.shoppinglist.R;
import hu.ait.tiffanynguyen.shoppinglist.data.Item;

/**
 * Created by tiffanynguyen on 10/16/14.
 */
public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<Item> itemsList;

    public ItemAdapter(Context context, List<Item> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(Item p) {
        itemsList.add(p);
    }

    public static class ViewHolder {
        ImageView ivIcon;
        TextView tvName;
        TextView tvQuantity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.row_item, null);
            ViewHolder holder = new ViewHolder();
            holder.ivIcon = (ImageView) v.findViewById(R.id.ivIcon);
            holder.tvQuantity = (TextView) v.findViewById(R.id.tvQuantity);
            holder.tvName = (TextView) v.findViewById(R.id.tvName);
            v.setTag(holder);
        }

        Item item = itemsList.get(position);

        if (item != null) {
            ViewHolder holder = (ViewHolder) v.getTag();
            holder.tvName.setText(item.getName());
            holder.tvQuantity.setText(Short.toString(item.getQuantity()));
            holder.ivIcon.setImageResource(item.getType().getIconId());
        }

        return v;
    }
}
