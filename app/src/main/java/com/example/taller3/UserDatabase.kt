package com.example.taller3

import androidx.room.*

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "nombre") val nombre: String?
)

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE uid = :uid")
    fun getUserById(uid: Int): User?

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Query("DELETE FROM user")
    fun deleteAll()
}

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}