package com.example.tugaslayout

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user_db"
        private const val TABLE_USER = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_USER ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT UNIQUE, $COLUMN_PASSWORD TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    fun isUserExist(): Boolean {
        val db = readableDatabase
        val query = "SELECT COUNT(*) FROM $TABLE_USER"
        val cursor = db.rawQuery(query, null)
        val exists = if (cursor.moveToFirst()) cursor.getInt(0) > 0 else false
        cursor.close()
        db.close()
        return exists
    }

    fun isUserExists(username: String): Boolean {
        val db = readableDatabase
        val query = "SELECT 1 FROM $TABLE_USER WHERE $COLUMN_USERNAME = ? LIMIT 1"
        val cursor = db.rawQuery(query, arrayOf(username))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    fun addUser(username: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        val result = db.insert(TABLE_USER, null, values)
        db.close()
        return result != -1L
    }

    fun checkLogin(username: String, password: String): Boolean {
        val db = readableDatabase
        val query = "SELECT 1 FROM $TABLE_USER WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ? LIMIT 1"
        val cursor = db.rawQuery(query, arrayOf(username, password))
        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }

    fun deleteAllUsers(): Boolean {
        val db = writableDatabase
        val result = db.delete(TABLE_USER, null, null)
        db.close()
        return result > 0
    }
}
