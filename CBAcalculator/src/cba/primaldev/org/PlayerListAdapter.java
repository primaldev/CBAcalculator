package cba.primaldev.org;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class PlayerListAdapter extends ArrayAdapter<PlayersScore>{
	List<PlayersScore> list;


	public PlayerListAdapter(Context context, int textViewResourceId,
			List<PlayersScore> list) {
		super(context, textViewResourceId, list);
		this.list = list;
		// TODO Auto-generated constructor stub
	}


	public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        return view;
    }
    
    

	
}
