package com.cursokotlin.todoapp.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.cursokotlin.todoapp.addtasks.data.TaskDAO
import com.cursokotlin.todoapp.addtasks.data.TodoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    fun providesTaskDao(todoDataBase: TodoDataBase): TaskDAO{
        return todoDataBase.taskDao()
    }



    @Provides
    @Singleton
    fun provideTodoDataBase(@ApplicationContext applicationContext: Context):TodoDataBase{
return Room.databaseBuilder(applicationContext, TodoDataBase::class.java,"TaskDataBase").build()

    }

}