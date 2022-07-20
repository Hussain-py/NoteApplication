package com.example.notesapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notesapplication.other.Constants
import com.example.notesapplication.other.Status
import com.example.notesapplication.repositories.FakeNoteRepository
import com.example.notesapplication.ui.NoteViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NoteViewModalTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: NoteViewModel

    @Before
    fun setup() {
        viewModel = NoteViewModel(FakeNoteRepository())
    }

    @Test
    fun `insert Note with empty field, returns error`() {
        viewModel.insertNoteItem("First Note", "", "10 Jul, 2022 - 15:11",id = 1)

        val value = viewModel.insertNoteItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert Note with too long Title, returns error`() {
        val string = buildString {
            for(i in 1..Constants.MAX_NAME_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertNoteItem(string, "", "10 Jul, 2022 - 15:11",id = 1)

        val value = viewModel.insertNoteItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert Note with too long Description, returns error`() {
        val string = buildString {
            for(i in 1..Constants.MAX_DESC_LENGTH+ 1) {
                append(1)
            }
        }
        viewModel.insertNoteItem("First Note", string, "10 Jul, 2022 - 15:11",id = 1)

        val value = viewModel.insertNoteItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert Note with invalid Date, returns error`() {
        val string = buildString {
            for(i in 1..Constants.MAX_DATE_LENGTH+ 1) {
                append(1)
            }
        }

        viewModel.insertNoteItem("First Note", "", string,id = 1)

        val value = viewModel.insertNoteItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert note item with valid input, returns success`() {
        viewModel.insertNoteItem("First Note", "First Note Description", "10 Jul, 2022 - 15:11",id = 1)

        val value = viewModel.insertNoteItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

}

