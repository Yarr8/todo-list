package com.pet.todo_list;

import com.pet.todo_list.exception.TaskNotFoundException;
import com.pet.todo_list.model.Task;
import com.pet.todo_list.model.TaskStatus;
import com.pet.todo_list.repository.TaskRepository;
import com.pet.todo_list.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {
    @Mock
    TaskRepository repository;

    @InjectMocks
    TaskService service;

    String testTaskTitle = "Title";
    String testTaskDescription = "Description";
    LocalDate testTaskDueDate = LocalDate.of(2025, 12, 31);
    Task testTask = Task.createNew(testTaskTitle, testTaskDescription, testTaskDueDate);

    @Test
    void shouldAddTask() {
        Task task = service.addTask(testTaskTitle, testTaskDescription, testTaskDueDate);

        assertEquals(testTaskTitle, task.getTitle());
        assertEquals(testTaskDescription, task.getDescription());
        assertEquals(testTaskDueDate, task.getDueDate());

        verify(repository).save(task);
    }

    @Test
    void shouldEditTask() {
        when(repository.findById(testTask.getId())).thenReturn(Optional.of(testTask));

        String updatedTitle = "NewTitle";
        Task editedTask = service.editTask(testTask.getId(), updatedTitle, null, null, null);

        Task withNewTitle = testTask.toBuilder().title(updatedTitle).build();
        assertEquals(updatedTitle, editedTask.getTitle());
        assertEquals(withNewTitle, editedTask);
    }

    @Test
    void shouldThrowWhenTaskIsNotExists() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () ->
                service.editTask(id, null, null, null, null));
    }

    @Test
    void shouldDelete() {
        UUID id = UUID.randomUUID();
        service.deleteTask(id);

        verify(repository).delete(id);
    }

    @Test
    void shouldFilter() {
        service.filterByStatus(TaskStatus.TODO);

        verify(repository).findByStatus(TaskStatus.TODO);
    }

    @Test
    void shouldSortByDate() {
        Task testTaskOne = Task.createNew("title1", "desc1", LocalDate.of(2025, 12, 31));
        Task testTaskTwo = Task.createNew("title2", "desc2", LocalDate.of(2026, 1, 5));
        Task testTaskThree = Task.createNew("title3", "desc3", LocalDate.of(2024, 11, 7));

        when(repository.findAll()).thenReturn(List.of(testTaskOne, testTaskTwo, testTaskThree));

        List<Task> sorted = service.sortByDueDate();

        assertEquals(List.of(testTaskThree, testTaskOne, testTaskTwo), sorted);
    }

    @Test
    void shouldSortByStatus() {
        Task testTaskOne = Task.createNew("title1", "desc1", LocalDate.of(2025, 12, 31)).withStatus(TaskStatus.IN_PROGRESS);
        Task testTaskTwo = Task.createNew("title2", "desc2", LocalDate.of(2025, 12, 31)).withStatus(TaskStatus.DONE);
        Task testTaskThree = Task.createNew("title3", "desc3", LocalDate.of(2025, 12, 31)).withStatus(TaskStatus.TODO);

        when(repository.findAll()).thenReturn(List.of(testTaskOne, testTaskTwo, testTaskThree));

        List<Task> sorted = service.sortByStatus();

        assertEquals(List.of(testTaskThree, testTaskOne, testTaskTwo), sorted);
    }
}
