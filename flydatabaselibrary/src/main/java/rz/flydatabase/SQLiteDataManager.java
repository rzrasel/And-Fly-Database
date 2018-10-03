package rz.flydatabase;

import android.content.Context;

public class SQLiteDataManager {
    private Context context;
    private SQLiteDBCopyHelper sqLiteDBCopyHelper = null;
    //private Cursor cursor;
    private String dbFileName = "db-SQLite.sqlite3";
    private String dbDirName = "/database/";
    private int dbVersion = 1;
    private boolean isDebug = false;
    private String debugString = "";

    public SQLiteDataManager(Context argContext) {
        context = argContext;
        isDebug = false;
        /*if (BuildConfig.DEBUG) {
            isDebug = true;
        }*/
    }

    public SQLiteDataManager setIsDebugging(boolean argIsDebugging) {
        isDebug = argIsDebugging;
        return this;
    }

    public SQLiteDataManager setDbFileName(String argDbFileName) {
        dbFileName = argDbFileName;
        return this;
    }

    public SQLiteDataManager setDbDirectoryName(String argDbDirName) {
        dbDirName = argDbDirName;
        return this;
    }

    public SQLiteDataManager setDbVersion(int argDbVersion) {
        dbVersion = argDbVersion;
        return this;
    }

    protected SQLiteDBCopyHelper openDatabase() {
        sqLiteDBCopyHelper = new SQLiteDBCopyHelper(context, dbFileName, dbDirName)
                .setDatabaseVersion(dbVersion);
        if (sqLiteDBCopyHelper != null) {
            sqLiteDBCopyHelper.onOpenDatabase();
        }
        return sqLiteDBCopyHelper;
    }

    protected void closeDatabase(SQLiteDBCopyHelper argSqLiteDBCopyHelper) {
        if (sqLiteDBCopyHelper != null) {
            sqLiteDBCopyHelper.onCloseDatabase();
            sqLiteDBCopyHelper = null;
        }
        if (argSqLiteDBCopyHelper != null) {
            argSqLiteDBCopyHelper = null;
        }
    }

    public void sysLog(String argMsg) {
        if (isDebug) {
            System.out.println();
            System.out.println("|----|--------DEBUG_LOG: " + argMsg);
        }
    }

    public void onBackupDb(boolean argIsForceBackUp) {
        //System.out.println("|----|--------PRINT: DATABASE_BACKUP");
        if (isDebug && !argIsForceBackUp) {
            //System.out.println("|----|--------DEBUG_LOG: DATABASE_BACKUP");
            //SQLiteDBCopyHelper sqLiteDBCopyHelper = new SQLiteDBCopyHelper(context, dbFile, dbDir);
            sqLiteDBCopyHelper.onBackupDb();
        }
    }
}
