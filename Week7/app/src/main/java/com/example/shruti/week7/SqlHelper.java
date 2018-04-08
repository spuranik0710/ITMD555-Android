package com.example.shruti.week7;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by shruti on 4/2/2018.
 */

public class SqlHelper extends SQLiteOpenHelper {
    private static final int DATABSE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "BookDB.db";
    // Books table name
    private static final String TABLE_BOOKS = "books";
    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_RATING = "rating";
    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE = " CREATE TABLE " + TABLE_BOOKS + " " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " + " title TEXT NOT NULL, "+ " author TEXT NOT NULL, " + " rating TEXT NOT NULL );";
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS books");

        this.onCreate(db);
        String upgradeQuery = "ALTER TABLE books ADD COLUMN rating TEXT";
        if (oldVersion == 1 && newVersion == 2)
            db.execSQL(upgradeQuery);
    }

    public  void addBook(Book book){
        Log.d("addBook",book.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle());
        values.put(KEY_AUTHOR, book.getAuthor());
        values.put(KEY_RATING, book.getRating());

        db.insert(TABLE_BOOKS,null,values);
        db.close();
    }

    public List<Book> getAllBooks(){
        List<Book> books = new LinkedList<Book>();
        String query = " SELECT * FROM " + TABLE_BOOKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Book book = null;
        if(cursor.moveToFirst()){
            do{
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setRating(cursor.getString(3));

                books.add(book);
            }while (cursor.moveToNext());
        }
        Log.d("getAllBooks()", books.toString());
        return books;
    }

    public int updateBook(Book book, String newTitle, String newAuthor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",newTitle);
        values.put("author",newAuthor);

        int i = db.update(TABLE_BOOKS,values,KEY_ID+" =? ",
                new String[]{ String.valueOf(book.getId()) });
        db.close();
        Log.d("Update Book", book.toString());
        return i;
    }

    public  void deleteBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKS,KEY_ID+" =? ",
                new String[]{ String.valueOf(book.getId()) });
        db.close();
        Log.d("deleteBook", book.toString());
    }

    public Set<String> getTitle(){
        Set<String> set = new HashSet<String>();
        String selectQuery = " select * from " + TABLE_BOOKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                set.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return set;
    }

    public  String getAuthor(String title){
        StringBuilder s = new StringBuilder();
        String selectQuery = " select *  from " + TABLE_BOOKS + " where title=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{ title });
        if(cursor.moveToFirst()){
            do{
                s.append(cursor.getString(2));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return s.toString();
    }
}
