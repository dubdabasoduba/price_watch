package db;

	import android.content.ContentValues;
	import android.content.Context;
	import android.database.Cursor;
	import android.database.sqlite.SQLiteDatabase;
	import android.database.sqlite.SQLiteOpenHelper;
	 
	import java.util.HashMap;
	 
	public class Databasehandler extends SQLiteOpenHelper {
	 
	    // All Static variables
	    // Database Version
	    private static final int DATABASE_VERSION = 1;
	 
	    // Database Name
	    private static final String DATABASE_NAME = "pricewatch";
	 
	    // Login table name
	    private static final String TABLE_PERSONS = "persons";
	 
	    // Login Table Columns names
	    private static final String KEY_ID = "personid";
	    private static final String KEY_FIRSTNAME = "firstname";
	    private static final String KEY_SECONDNAME = "secondname";
	    private static final String KEY_EMAIL = "email";
	    private static final String KEY_TELEPHONE = "telphone";
	    private static final String KEY_POBOX = "pobox";
	    private static final String KEY_PASSWORD = "password";
	 
	    public Databasehandler(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
	 
	    // Creating Tables
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        String CREATE_PERSONS_TABLE = "CREATE TABLE " + TABLE_PERSONS + "("
	                + KEY_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT,"
	                + KEY_FIRSTNAME + " TEXT,"
	                + KEY_SECONDNAME + " TEXT,"
	                + KEY_EMAIL + " TEXT UNIQUE,"
	                + KEY_TELEPHONE + " TEXT,"
	                + KEY_POBOX + " TEXT,"
	                + KEY_PASSWORD + " TEXT" + ")";
	        db.execSQL(CREATE_PERSONS_TABLE);
	    }
	 
	    // Upgrading database
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // Drop older table if existed
	        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONS);
	 
	        // Create tables again
	        onCreate(db);
	    }
	 
	    /**
	     * Storing user details in database
	     * */
	    public void addUser(String fname, String sname, String email, String tel, String pobox, String pass) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
	        values.put(KEY_FIRSTNAME, fname); // FirstName
	        values.put(KEY_SECONDNAME, sname); // LastName
	        values.put(KEY_EMAIL, email); // Email
	        values.put(KEY_TELEPHONE, tel); // UserName
	        values.put(KEY_POBOX, pobox); // Email
	        values.put(KEY_PASSWORD, pass); // Created At
	 
	        // Inserting Row
	        db.insert(TABLE_PERSONS, null, values);
	        db.close(); // Closing database connection
	    }
	 
	    /**
	     * Getting user data from database
	     * */
	    public HashMap getUserDetails(){
	        HashMap user = new HashMap();
	        String selectQuery = "SELECT  * FROM " + TABLE_PERSONS;
	 
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	        // Move to first row
	        cursor.moveToFirst();
	        if(cursor.getCount() > 0){
	            user.put("fname", cursor.getString(1));
	            user.put("sname", cursor.getString(2));
	            user.put("email", cursor.getString(3));
	            user.put("tel", cursor.getString(4));
	            user.put("pobox", cursor.getString(5));
	            user.put("pass", cursor.getString(6));
	        }
	        cursor.close();
	        db.close();
	        // return user
	        return user;
	    }
	 
	    /**
	     * Getting user login status
	     * return true if rows are there in table
	     * */
	    public int getRowCount() {
	        String countQuery = "SELECT  * FROM " + TABLE_PERSONS;
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(countQuery, null);
	        int rowCount = cursor.getCount();
	        db.close();
	        cursor.close();
	 
	        // return row count
	        return rowCount;
	    }
	 
	    /**
	     * Re create database
	     * Delete all tables and create them again
	     * */
	    public void resetTables(){
	        SQLiteDatabase db = this.getWritableDatabase();
	        // Delete All Rows
	        db.delete(TABLE_PERSONS, null, null);
	        db.close();
	    }
	
}
