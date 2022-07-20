package com.example.notesapplication.data.local

import android.accounts.AuthenticatorDescription
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

// Specify our table Name
//@Entity(tableName = "notesTable")
////Specifying column name of table
//class Note(
//
//    @ColumnInfo(name = "title")val noteTitle :String,
//    @ColumnInfo(name = "description")val noteDescription: String,
//    @ColumnInfo(name = "timestamp")val timestamp: String,
////    @PrimaryKey(autoGenerate = true)
////    var id: Int? = 0
//)
//
//{
//
//    // on below line we are specifying our key and
//    // then auto generate as true and we are
//    // specifying its initial value as 0
//    @PrimaryKey(autoGenerate = true)
//    var id = 0
//
//}

@Entity(tableName = "notesTable")
//Specifying column name of table
data class Note(

    @ColumnInfo(name = "title")val noteTitle :String,
    @ColumnInfo(name = "description")val noteDescription: String,
    @ColumnInfo(name = "timestamp")val timestamp: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0
)