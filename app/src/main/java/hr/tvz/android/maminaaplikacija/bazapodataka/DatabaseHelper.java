package hr.tvz.android.maminaaplikacija.bazapodataka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "todoList";

    // Table Names
    private static final String TABLE_TODO = "todos";


    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    private static final String KEY_TODO = "todo";
    private static final String KEY_TITLE = "title";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TODO + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TODO
            + " TEXT," + KEY_TITLE + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ako ne želite izgubiti podatke, ovo nije najbolji način :)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);

        onCreate(db);
    }

    // ------------------------ "todos" table methods ----------------//

    /**
     * Creating a todo
     **/
    public long createToDo(Proizvodi todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, todo.getTitle());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long todo_id = db.insert(TABLE_TODO, null, values);


        return todo_id;
    }

    /**
     * get single todo
     **/
    public Proizvodi getTodo(long todo_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE "
                + KEY_ID + " = " + todo_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Proizvodi td = new Proizvodi();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));
        td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return td;
    }

    /**
     * getting all todos
     * */
    public List<Proizvodi> getAllToDos() {
        List<Proizvodi> todos = new ArrayList<Proizvodi>();
        String selectQuery = "SELECT  * FROM " + TABLE_TODO;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Proizvodi td = new Proizvodi();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));
                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    /**
     * getting todo count
     **/
    public int getToDoCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TODO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Updating a todo
     **/
    public int updateToDo(Proizvodi todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, todo.getTitle());

        // updating row
        return db.update(TABLE_TODO, values, KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getId()) });
    }

    /**
     * Deleting a todo
     **/
    public void deleteToDo(long tado_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_ID + " = ?",
                new String[] { String.valueOf(tado_id) });
    }
    /**
     * Deleting todos
     **/
    public void deleteToDos() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TODO + ";");
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
