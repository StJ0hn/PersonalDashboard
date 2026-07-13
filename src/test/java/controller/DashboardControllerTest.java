package controller;

import model.Goal;
import model.StudySession;
import model.Task;
import model.TaskPriority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardControllerTest {

    private DashboardController controller;
    private List<Task> tasks;
    private List<Goal> goals;

    @BeforeEach
    public void setUp() {
        controller = new DashboardController();
        tasks = new ArrayList<>();
        goals = new ArrayList<>();
    }

    @Test
    public void testCountPendingTasks() {
        Task t1 = new Task("Task 1", "", TaskPriority.LOW, "Work", LocalDate.now().plusDays(1));
        Task t2 = new Task("Task 2", "", TaskPriority.HIGH, "Work", LocalDate.now().plusDays(1));
        t2.markAsCompleted();

        tasks.add(t1);
        tasks.add(t2);

        assertEquals(1, controller.countPendingTasks(tasks));
    }

    @Test
    public void testCountCompletedTasks() {
        Task t1 = new Task("Task 1", "", TaskPriority.LOW, "Work", LocalDate.now().plusDays(1));
        t1.markAsCompleted();
        tasks.add(t1);

        assertEquals(1, controller.countCompletedTasks(tasks));
    }

    @Test
    public void testCountOverdueTasks() {
        Task t1 = new Task("Task 1", "", TaskPriority.LOW, "Work", LocalDate.now().minusDays(5));
        Task t2 = new Task("Task 2", "", TaskPriority.LOW, "Work", LocalDate.now().plusDays(5));

        tasks.add(t1);
        tasks.add(t2);

        assertEquals(1, controller.countOverdueTasks(tasks));
    }

    @Test
    public void testCountTasksByCategory() {
        tasks.add(new Task("T1", "", TaskPriority.LOW, "Work", LocalDate.now().plusDays(1)));
        tasks.add(new Task("T2", "", TaskPriority.LOW, "Work", LocalDate.now().plusDays(1)));
        tasks.add(new Task("T3", "", TaskPriority.LOW, "Personal", LocalDate.now().plusDays(1)));

        Map<String, Long> categoryMap = controller.countTasksByCategory(tasks);

        assertEquals(2, categoryMap.get("Work"));
        assertEquals(1, categoryMap.get("Personal"));
    }

    @Test
    public void testCalculateStudyMinutesByArea() {
        Goal g1 = new Goal("G1", "Math", 10, LocalDate.now().plusDays(10));
        g1.addStudySession(new StudySession(60, ""));

        Goal g2 = new Goal("G2", "Math", 10, LocalDate.now().plusDays(10));
        g2.addStudySession(new StudySession(30, ""));

        Goal g3 = new Goal("G3", "History", 10, LocalDate.now().plusDays(10));
        g3.addStudySession(new StudySession(45, ""));

        goals.add(g1);
        goals.add(g2);
        goals.add(g3);

        Map<String, Integer> areaMap = controller.calculateStudyMinutesByArea(goals);

        assertEquals(90, areaMap.get("Math"));
        assertEquals(45, areaMap.get("History"));
    }

    @Test
    public void testCalculateCurrentStreakZero() {
        assertEquals(0, controller.calculateCurrentStreak(goals));
    }

    @Test
    public void testCalculateCurrentStreakOne() {
        Goal g1 = new Goal("G1", "Math", 10, LocalDate.now().plusDays(10));
        g1.addStudySession(new StudySession(60, ""));
        goals.add(g1);

        assertEquals(1, controller.calculateCurrentStreak(goals));
    }
}