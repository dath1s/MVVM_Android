package com.example.laba3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "LaptopDB"
val TABLE_NAME = "Laptops"
val COL_ID = "id"
val COL_MODEL = "model"
val COL_RAM = "ram"
val COL_PROC = "proc"

class DataBaseHandler(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_MODEL + " VARCHAR(256) NOT NULL," +
                COL_RAM + " INTEGER NOT NULL," +
                COL_PROC + " VARCHAR(256) NOT NULL)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        }
        onCreate(db)
    }

    fun insertData(laptop: Laptop) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_MODEL, laptop.model)
        cv.put(COL_RAM, laptop.ram)
        cv.put(COL_PROC, laptop.proc)
        db.insert(TABLE_NAME, null, cv)
    }

    fun getAll(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

    fun dropDB() {
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun getByRAM(ram: Int): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ram = " + ram.toString(), null)
    }

    fun getByModel(model: String): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE model = " + '"' + model + '"', null)
    }

    fun getByModelRam(model: String, ram: Int): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE model = " + '"' + model + '"' + " AND ram = " + ram.toString(), null)
    }
}