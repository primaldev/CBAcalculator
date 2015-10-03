package cba.primaldev.org;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PlayerListDBM {
	Context context;
	private SQLiteDatabase db;
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_PLAYERLISTID = "playerlistid";
	public static final String KEY_HANDICAP = "hadicap";
	public static final String KEY_SCORE = "score";
	private static final String PLAYERSLIST_TABLE_NAME = "playerlist";
 
    
	public PlayerListDBM(Context context) {
		super();
		this.context = context;
		
		PlayerListDBA playerListDBA = new PlayerListDBA(context);
		this.db = playerListDBA.getWritableDatabase();
	}
    
	public void addList(String listName, ArrayList<PlayersScore> playersList){
		
		for (PlayersScore player : playersList) {
			ContentValues values = new ContentValues();
			values.put(KEY_PLAYERLISTID, listName);
			values.put(KEY_HANDICAP, player.getHadicap());			
			values.put(KEY_SCORE, player.getScore());
			
			try {
				db.insert(PLAYERSLIST_TABLE_NAME, null, values);
			} catch (Exception e) {
				Log.e("DB ERROR", e.toString());
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void removeList(String listName) {
		try {
			db.delete(PLAYERSLIST_TABLE_NAME, KEY_PLAYERLISTID  + "=" + listName,null);
		} catch (Exception e) {
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
	}
	
	public void updateList(String listName, ArrayList<PlayersScore> playersList) {
		this.removeList(listName);
		this.addList(listName, playersList);
	}

}
