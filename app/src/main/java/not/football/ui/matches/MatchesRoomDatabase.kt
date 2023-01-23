package not.football.ui.matches


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.io.File

@Database(entities = [(Matches::class)], version = 1)
abstract class MatchesRoomDatabase: RoomDatabase() {

    abstract fun matchDAO(): MatchDAO

    companion object {

        private var INSTANCE: MatchesRoomDatabase? = null

        fun getInstance(context: Context): MatchesRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MatchesRoomDatabase::class.java,
                        "appdb1"
                    )//.createFromFile(File("/res/matches_database.sql"))//.createFromAsset("")
                        .fallbackToDestructiveMigration()
                        //.addCallback(DatabaseCallback())
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
/*
class DatabaseCallback : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) = db.run {
        // Notice non-ui thread is here
        beginTransaction()
        try {
            execSQL(
                """
                    PRAGMA foreign_keys=OFF;
                    BEGIN TRANSACTION;
                    CREATE TABLE android_metadata (locale TEXT);
                    INSERT INTO android_metadata VALUES('en_IN');
                    CREATE TABLE `matches` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `league` TEXT NOT NULL, `homeTeam` TEXT NOT NULL, `homeGoals` INTEGER NOT NULL, `awayTeam` TEXT NOT NULL, `awayGoals` INTEGER NOT NULL, `matchTime` TEXT NOT NULL, `minutesPlayed` INTEGER NOT NULL);
                    INSERT INTO `matches` VALUES (1, "PL", "ARS", 4, "MCI", 0, "", 90);
                    INSERT INTO `matches` VALUES (2, "PL", "ARS", 4, "MCI", 0, "", 90);
                    INSERT INTO `matches` VALUES (3, "BULI", "ARS", 4, "MCI", 0, "", 90);
                    INSERT INTO `matches` VALUES (4, "BULI", "ARS", 4, "MCI", 0, "", 90);
                    INSERT INTO `matches` VALUES (5, "BULI", "ARS", 4, "MCI", 0, "", 90);
                    CREATE TABLE room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT);
                    INSERT INTO room_master_table VALUES(42,'4e6fdfdc27316ac17b34f51c62356681');
                    DELETE FROM sqlite_sequence;
                    COMMIT;
                """.trimIndent()
            )
            //insert("matches", values = )
            //update(...)
            //delete(...)
            setTransactionSuccessful()
        } finally {
            endTransaction()
        }
    }
}

 */

