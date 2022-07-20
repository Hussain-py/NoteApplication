package com.example.notesapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.notesapplication.data.local.Note
import com.example.notesapplication.data.local.NoteDatabase
import com.example.notesapplication.data.local.NotesDao
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class NotesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var database : NoteDatabase
    private lateinit var dao : NotesDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getNotesDao()
    }


    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insert() = runBlockingTest {
        val note = Note("Dao testing","Insert data testing"," 10 Jul, 2022 - 15:11", id = 1)
        dao.insert(note)

        val allNotes = dao.getAllNotes().getOrAwaitValue()
        assertThat(allNotes).contains(note)
//        assertThat(allNotes).isNotNull()

    }

    @Test
    fun deleteNote() = runBlockingTest {
        val note = Note("Dao testing","Insert data testing"," 10 Jul, 2022 - 15:11", id = 1)
        dao.insert(note)
        dao.delete(note)

        val allNotes = dao.getAllNotes().getOrAwaitValue()
        assertThat(allNotes).doesNotContain(note)
    }

    @Test
    fun update() = runBlockingTest {
        val note = Note("Dao testing","Insert data testing"," 10 Jul, 2022 - 15:11", id = 1)
        dao.insert(note)

        val updateNote = Note("Dao testing update","Insert data testing"," 11 Jul, 2022 - 15:11", id = 1)
        dao.update(updateNote)

        val allNotes = dao.getAllNotes().getOrAwaitValue()
        assertThat(allNotes).contains(updateNote)

    }


}