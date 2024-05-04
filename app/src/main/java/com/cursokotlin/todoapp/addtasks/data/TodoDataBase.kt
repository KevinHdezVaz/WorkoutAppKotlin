package com.cursokotlin.todoapp.addtasks.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TodoDataBase:RoomDatabase() {
//DAO
    /**
     * se necesita una entidad, algo como el modelo, practicamente lo mismo
     * el modelo de ui, y el modelo de data DEBEN SER DIFERENTES
     *
     * */


//DAO es para hacer consultas a la base de datos (ROOM)

    abstract fun taskDao():TaskDAO
}