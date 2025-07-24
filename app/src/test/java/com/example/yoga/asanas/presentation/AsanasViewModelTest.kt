package com.example.yoga.asanas.presentation

import com.example.yoga.domain.repository.YogaRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AsanasViewModelTest {

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: AsanasViewModel

    private val repository = mockk<YogaRepository>()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN the repository hasn't returned a value yet WHEN observing asanas THEN observe loading`() {
        viewModel = AsanasViewModel(repository)

        assertEquals(AsanasUiState.Loading, viewModel.asanas.value)
    }

    @Test
    fun `GIVEN the screen has started WHEN observing detail THEN observe success`() = runTest {
        coEvery { repository.fetchPoses() } returns emptyList()

        viewModel = AsanasViewModel(repository)
        advanceUntilIdle()

        assertThat(viewModel.asanas.value, instanceOf(AsanasUiState.Success::class.java))
    }

    @Test
    fun `GIVEN the screen has started AND there is an error WHEN observing detail THEN observe error`() =
        runTest {
            coEvery { repository.fetchPoses() } throws Exception()

            viewModel = AsanasViewModel(repository)
            advanceUntilIdle()

            assertThat(viewModel.asanas.value, instanceOf(AsanasUiState.Error::class.java))
        }
}
