package grsoft.com.br.whattowatch.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import grsoft.com.br.whattowatch.data.entities.*

@Database(entities = [TVShow::class, Genre::class, Details::class, Network::class, Videos::class,
                     Staff::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun seriesDao(): TMDbDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "what-to-watch")
                    .fallbackToDestructiveMigration()
                    .build()
    }
}
