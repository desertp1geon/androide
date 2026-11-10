package com.example.iscip.factivity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.iscip.basic.concurency.concurency.Fault;
import com.example.iscip.basic.concurency.concurency.TaskCallback;
import com.example.iscip.data.Fruit;
import com.example.iscip.data.FruitRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MainActivityViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    private FruitRepository fruitRepository;
    private MainActivityViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        fruitRepository = mock(FruitRepository.class);
        viewModel = new MainActivityViewModel(fruitRepository);
    }

    @Test
    public void testViewModelInitialization() {
        assertNotNull(viewModel);
        assertNotNull(viewModel.getFruits());
        assertNotNull(viewModel.getErrorMessage());

        assertNotNull(viewModel.getFruits().getValue());
        assertTrue(viewModel.getFruits().getValue().isEmpty());
    }

    @Test
    public void testGetFruitsLiveData() {
        Fruit apple = new Fruit(
                1, "Apple", "Pome", "Red apple", "52"
        );

        List<Fruit> expectedFruits = Collections.singletonList(apple);

        viewModel.getFruits().setValue(expectedFruits);

        assertEquals(
                expectedFruits,
                viewModel.getFruits().getValue()
        );
        assertEquals(
                "Apple",
                viewModel.getFruits().getValue().get(0).getName()
        );
    }

    @Test
    public void testLoadFruitsSuccessfully() {
        Fruit apple = new Fruit(
                1, "Apple", "Pome", "Red apple", "52"
        );

        Fruit banana = new Fruit(
                2, "Banana", "Tropical", "Yellow banana", "89"
        );

        List<Fruit> expectedFruits = Arrays.asList(apple, banana);

        viewModel.loadFruits();

        ArgumentCaptor<TaskCallback<List<Fruit>>> captor =
                ArgumentCaptor.forClass(TaskCallback.class);

        verify(fruitRepository).getAll(captor.capture());

        captor.getValue().onComplete(expectedFruits);

        assertEquals(
                2,
                viewModel.getFruits().getValue().size()
        );
        assertEquals(
                "Apple",
                viewModel.getFruits().getValue().get(0).getName()
        );
        assertEquals(
                "Banana",
                viewModel.getFruits().getValue().get(1).getName()
        );
    }

    @Test
    public void testLoadFruitsError() {
        viewModel.loadFruits();

        ArgumentCaptor<TaskCallback<List<Fruit>>> captor =
                ArgumentCaptor.forClass(TaskCallback.class);

        verify(fruitRepository).getAll(captor.capture());

        captor.getValue().onError(
                new Fault("Database error")
        );

        assertEquals(
                "Не вдалося завантажити колекцію",
                viewModel.getErrorMessage().getValue()
        );
    }

    @Test
    public void testLoadEmptyFruitList() {
        viewModel.loadFruits();

        ArgumentCaptor<TaskCallback<List<Fruit>>> captor =
                ArgumentCaptor.forClass(TaskCallback.class);

        verify(fruitRepository).getAll(captor.capture());

        captor.getValue().onComplete(Collections.emptyList());

        assertNotNull(viewModel.getFruits().getValue());
        assertTrue(viewModel.getFruits().getValue().isEmpty());
    }

    @Test
    public void testReloadFruitList() {
        Fruit apple = new Fruit(
                1, "Apple", "Pome", "Red apple", "52"
        );

        Fruit banana = new Fruit(
                2, "Banana", "Tropical", "Yellow banana", "89"
        );

        viewModel.loadFruits();

        ArgumentCaptor<TaskCallback<List<Fruit>>> firstCaptor =
                ArgumentCaptor.forClass(TaskCallback.class);

        verify(fruitRepository).getAll(firstCaptor.capture());

        firstCaptor.getValue().onComplete(
                Collections.singletonList(apple)
        );

        assertEquals(1, viewModel.getFruits().getValue().size());

        viewModel.loadFruits();

        ArgumentCaptor<TaskCallback<List<Fruit>>> secondCaptor =
                ArgumentCaptor.forClass(TaskCallback.class);

        verify(fruitRepository, times(2)).getAll(secondCaptor.capture());

        secondCaptor.getAllValues().get(1).onComplete(
                Arrays.asList(apple, banana)
        );

        assertEquals(2, viewModel.getFruits().getValue().size());
        assertEquals(
                "Banana",
                viewModel.getFruits().getValue().get(1).getName()
        );
    }
}