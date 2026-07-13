package model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoalTest {

    @Test
    public void testProgressPercentageWithoutSessions() {
        Goal goal = new Goal("Learn Java", "Programming", 10, LocalDate.now().plusDays(10));

        assertEquals(0.0, goal.getProgressPercentage(), 0.01);
    }

    @Test
    public void testProgressPercentageWithSessions() {
        Goal goal = new Goal("Learn Java", "Programming", 10, LocalDate.now().plusDays(10));
        goal.addStudySession(new StudySession(300, ""));

        assertEquals(50.0, goal.getProgressPercentage(), 0.01);
    }

    @Test
    public void testRemainingMinutes() {
        Goal goal = new Goal("Learn Java", "Programming", 10, LocalDate.now().plusDays(10));
        goal.addStudySession(new StudySession(120, ""));

        assertEquals(480, goal.getRemainingMinutes());
    }

    @Test
    public void testRemainingMinutesNeverDropsBelowZero() {
        Goal goal = new Goal("Learn Java", "Programming", 2, LocalDate.now().plusDays(10));
        goal.addStudySession(new StudySession(200, ""));

        assertEquals(0, goal.getRemainingMinutes());
    }
}