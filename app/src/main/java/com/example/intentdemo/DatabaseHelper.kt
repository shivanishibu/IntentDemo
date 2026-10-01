package com.example.intentdemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = ("CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_PASSWORD + " TEXT)")
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    // Insert or update user details in SQLite
    fun insertOrUpdateUser(username: String, password: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }

        // Clear previous entries to keep the latest logged-in user
        db.delete(TABLE_USERS, null, null)

        val id = db.insert(TABLE_USERS, null, values)
        db.close()
        return id
    }

    // Retrieve stored user details from SQLite
    fun getStoredUser(): Pair<String, String>? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_USERNAME, $COLUMN_PASSWORD FROM $TABLE_USERS LIMIT 1", null)
        var result: Pair<String, String>? = null

        if (cursor.moveToFirst()) {
            val username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME))
            val password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
            result = Pair(username, password)
        }

        cursor.close()
        db.close()
        return result
    }
}
