package com.example.madlevel4example

import androidx.room.*

@Dao
interface ReminderDao {

    @Query("SELECT * FROM reminderTable")
    // Specified that this method cannot be called without using Coroutines.
    suspend fun getAllReminders(): List<Reminder>

    @Insert
    suspend fun insertReminder(reminder: Reminder)

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    @Update
    suspend fun updateReminder(reminder: Reminder)
}