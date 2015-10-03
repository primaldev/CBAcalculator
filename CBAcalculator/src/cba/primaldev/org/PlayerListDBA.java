package cba.primaldev.org;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayerListDBA extends SQLiteOpenHelper {
	
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_PLAYERLISTID = "playerlistid";
	public static final String KEY_HANDICAP = "hadicap";
	public static final String KEY_SCORE = "score";
	
	private static final String DATABASE_NAME="CBAcalculator";
    private static final int DATABASE_VERSION = 1;
    private static final String PLAYERSLIST_TABLE_NAME = "playerlist";
    private static final String PLAYERSLIST_TABLE_CREATE = "CREATE TABLE " + PLAYERSLIST_TABLE_NAME + " (_id integer primary key autoincrement," +  
    														KEY_PLAYERLISTID + " TEXT not null, " +  
    														KEY_HANDICAP + " numeric , " +  
    														KEY_SCORE + " INTEGER );";

	public PlayerListDBA(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(PLAYERSLIST_TABLE_CREATE);

		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int ver1, int ver2) {
		
		// TODO Auto-generated method stub
		
	}
	

	
	
	

}
