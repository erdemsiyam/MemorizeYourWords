package com.erdemsiyam.memorizeyourwords.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.erdemsiyam.memorizeyourwords.entity.Category;
import com.erdemsiyam.memorizeyourwords.entity.Confuse;
import com.erdemsiyam.memorizeyourwords.entity.NotificationCategory;
import com.erdemsiyam.memorizeyourwords.entity.NotificationWord;
import com.erdemsiyam.memorizeyourwords.entity.Word;
import com.erdemsiyam.memorizeyourwords.repository.ICategoryDAO;
import com.erdemsiyam.memorizeyourwords.repository.IConfuseDAO;
import com.erdemsiyam.memorizeyourwords.repository.INotificationCategoryDAO;
import com.erdemsiyam.memorizeyourwords.repository.INotificationWordDAO;
import com.erdemsiyam.memorizeyourwords.repository.IWordDAO;

@Database(entities = {Word.class, Category.class, Confuse.class, NotificationWord.class, NotificationCategory.class}, version = 2, exportSchema= false)
public abstract class MyDatabase extends RoomDatabase {

    private static final String DB_NAME = "WordNotebook.db";
    public abstract ICategoryDAO getCategoryDAO();
    public abstract IWordDAO getWordDAO();
    public abstract IConfuseDAO getConfuseDAO();
    public abstract INotificationWordDAO getNotificationWordDAO();
    public abstract INotificationCategoryDAO getNotificationCategoryDAO();

    private static MyDatabase myDatabase;
    public static MyDatabase getMyDatabase(Context context) {
        if (myDatabase == null)
            myDatabase =  Room.databaseBuilder(context, MyDatabase.class, DB_NAME).allowMainThreadQueries().build();
        return myDatabase;
    }
    public static void destroyInstance() { myDatabase = null; }
}