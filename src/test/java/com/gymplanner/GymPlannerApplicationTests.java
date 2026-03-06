package com.gymplanner;

import com.gymplanner.model.Exercise;
import com.gymplanner.model.User;
import com.gymplanner.model.WeeklySchedule;
import com.gymplanner.repository.ExerciseRepository;
import com.gymplanner.repository.WeeklyScheduleRepository;
import com.gymplanner.service.ExerciseService;
import com.gymplanner.service.WeeklyScheduleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Clasa pentru teste unitare folosind Mockito Standard
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@ExtendWith(MockitoExtension.class)
class GymPlannerApplicationTests {

	@InjectMocks
	private ExerciseService exerciseService;

	@InjectMocks
	private WeeklyScheduleService weeklyScheduleService;

	@Mock
	private ExerciseRepository exerciseRepository;

	@Mock
	private WeeklyScheduleRepository weeklyScheduleRepository;

	// test adaugare exercitiu
	@Test
	void testAddExercise() {
		User user = new User();
		user.setId(1);

		Exercise exercise = new Exercise();
		exercise.setExerciseName("Flotari");

		// apelam metoda
		exerciseService.addExercise(exercise, user);

		// verificam setarea userului
		Assertions.assertEquals(user, exercise.getUser());

		// verificam ca repository.save a fost apelat o data
		Mockito.verify(exerciseRepository).save(exercise);
	}

	// test gasire plan existent
	@Test
	void testGetPlanForDayFound() {
		int userId = 1;
		int dayOfWeek = 1;
		LocalDate date = LocalDate.now();

		WeeklySchedule schedule = new WeeklySchedule();
		schedule.setWorkoutName("Plan Luni");

		List<WeeklySchedule> list = new ArrayList<>();
		list.add(schedule);

		// configuram comportamentul mock-ului
		Mockito.when(weeklyScheduleRepository.findActivePlan(userId, dayOfWeek, date)).thenReturn(list);

		// executam
		WeeklySchedule result = weeklyScheduleService.getPlanForDay(userId, dayOfWeek, date);

		// verificam
		Assertions.assertNotNull(result);
		Assertions.assertEquals("Plan Luni", result.getWorkoutName());
	}

	// test plan inexistent
	@Test
	void testGetPlanForDayNotFound() {
		int userId = 1;
		int dayOfWeek = 2;
		LocalDate date = LocalDate.now();

		// configuram mock-ul sa returneze lista goala
		Mockito.when(weeklyScheduleRepository.findActivePlan(userId, dayOfWeek, date)).thenReturn(Collections.emptyList());

		WeeklySchedule result = weeklyScheduleService.getPlanForDay(userId, dayOfWeek, date);

		Assertions.assertNull(result);
	}

	// test simplu de egalitate
	@Test
	void testSimpleAssertion() {
		Assertions.assertEquals(5, 5);
	}
}