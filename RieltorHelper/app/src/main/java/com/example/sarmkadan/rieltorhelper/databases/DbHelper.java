package com.example.sarmkadan.rieltorhelper.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sarmkadan.rieltorhelper.databases.dbExceptions.NoSuchTableInDbException;
import com.example.sarmkadan.rieltorhelper.entities.ArendRoom;
import com.example.sarmkadan.rieltorhelper.entities.Entity;
import com.example.sarmkadan.rieltorhelper.utils.FormatingDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Abilis on 25.04.2016.
 */
public class DbHelper extends SQLiteOpenHelper implements DataStore {

    private static final String DB_NAME = "RIELTOR";
    private static final int DB_VERSION = 6;
    private final String TAG = "Sqlite";
    private static DbHelper dbHelper = null;


    private DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DbHelper getDbHelpere(Context context) {
        if (dbHelper == null) {
            dbHelper = new DbHelper(context);
        }
        return dbHelper;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        CreateAndDeleteTables.createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //запись в лог
        Log.w(TAG, "Обновление БД с версии " + oldVersion + " до версии " + newVersion);

        /* Тут бы неплохо вытащить все данные, которые есть в таблицах,
        чтобы импортировать их в новую БД
        */

        //удаляем таблицы
        CreateAndDeleteTables.dropTables(db);

        //создаем таблицы заново
        onCreate(db);
    }

    //метод возращает список объектов класса Entity
    public List<Entity> getEntitiesList(SQLiteDatabase db, String tableName) throws NoSuchTableInDbException {

        List<Entity> result = new ArrayList<>();

        Cursor cursor = db.query(tableName, null, null, null, null, null, null);

        //определяем, какого класса объект нужно создать. Он совпадает с названием таблицы в БД
        switch (tableName) {

            case "ArendRoom":
                //получаем список всех объектов типа ArendRoom
               result = createArendRoomList(cursor);
                break;
            case "Arend":
                //получаем список всех объектов типа Arend
                break;
            case "ArendPrim":
                //получаем список всех объектов типа ArendPrim
                break;
            case "KVsell":
                //получаем список всех объектов типа KVsell
                break;
            case "HostelSell":
                //получаем список всех объектов типа HostelSell
                break;
            case "EarthSell":
                //получаем список всех объектов типа EarthSell
                break;
            case "PrimSell":
                //получаем список всех объектов типа PrimSell
                break;
            case "HouseSell":
                //получаем список всех объектов типа HouseSell
                break;
        }

        cursor.close();

        return result;
    }


    //метод возвращает список созданных объектов класса ArendRoom на основе данных из БД
    private List<Entity> createArendRoomList(Cursor cursor) throws NoSuchTableInDbException {

        List<Entity> result = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                //определяем переменные
                int id = cursor.getInt(cursor.getColumnIndex("ID"));
                String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
                String fullName = cursor.getString(cursor.getColumnIndex("fullName"));
                String dateStr = cursor.getString(cursor.getColumnIndex("date"));
                Date date = FormatingDate.getDateAsDate(dateStr);
                int costUah = cursor.getInt(cursor.getColumnIndex("costUah"));
                int costUsd = cursor.getInt(cursor.getColumnIndex("costUsd"));
                String common = cursor.getString(cursor.getColumnIndex("common"));
                String typeOfRent = cursor.getString(cursor.getColumnIndex("typeOfRent"));
                String numOfRooms = cursor.getString(cursor.getColumnIndex("numOfRooms"));
                String district = cursor.getString(cursor.getColumnIndex("district"));
                String addresses = cursor.getString(cursor.getColumnIndex("addresses"));
                String floor = cursor.getString(cursor.getColumnIndex("floor"));
                String square = cursor.getString(cursor.getColumnIndex("square"));
                String condition = cursor.getString(cursor.getColumnIndex("condition"));
                String heating = cursor.getString(cursor.getColumnIndex("heating"));
                String furniture = cursor.getString(cursor.getColumnIndex("furniture"));
                String furnitureList = cursor.getString(cursor.getColumnIndex("furnitureList"));
                String householdAppliances = cursor.getString(cursor.getColumnIndex("householdAppliances"));
                String householdAppliancesList = cursor.getString(cursor.getColumnIndex("householdAppliancesList"));
                String typeSettle = cursor.getString(cursor.getColumnIndex("typeSettle"));
                String typeOfRoom = cursor.getString(cursor.getColumnIndex("typeOfRoom"));
                String forWhom = cursor.getString(cursor.getColumnIndex("forWhom"));

                //создаем объект класса ArendRoom и добавляем его в список
                ArendRoom arendRoom = new ArendRoom(id, phoneNumber, fullName, date, costUah, costUsd,
                        common, typeOfRent, numOfRooms, district, addresses, floor, square, condition,
                        heating, furniture, furnitureList, householdAppliances, householdAppliancesList,
                        typeSettle, typeOfRoom, forWhom);

                result.add(arendRoom);

            } while (cursor.moveToNext());
        }
        else {
            throw new NoSuchTableInDbException();
        }

        return result;
    }

}
