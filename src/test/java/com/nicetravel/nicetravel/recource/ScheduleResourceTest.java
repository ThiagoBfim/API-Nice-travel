package com.nicetravel.nicetravel.recource;

import com.nicetravel.nicetravel.exceptions.EmptyValueException;
import com.nicetravel.nicetravel.resource.ScheduleResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;

public class ScheduleResourceTest {

    private ScheduleResource scheduleResource;

    @Before
    public void setUp() {
        scheduleResource = new ScheduleResource();
    }

    @Test
    public void shouldThrowExceptionWhenGetSchedulesByIdsWereEmpty() {
        try {
            scheduleResource.getSchedulesByIds(Collections.emptyList());
            Assert.fail("Have to throw error message.");
        } catch (EmptyValueException e) {
            Assert.assertThat("The parameter of 'scheduleIds' must have at least one element.", equalTo(e.getMessage()));
        }
    }

    @Test
    public void shouldThrowExceptionWhenGetSchedulesByIdsWereNull() {
        try {
            scheduleResource.getSchedulesByIds(null);
            Assert.fail("Have to throw error message.");
        } catch (EmptyValueException e) {
            Assert.assertThat("The parameter of 'scheduleIds' must have value.", equalTo(e.getMessage()));
        }
    }

    @Test
    public void shouldThrowExceptionWhenGetScheduleByIdWereNull() {
        try {
            scheduleResource.getScheduleDaysByScheduleCod(null);
            Assert.fail("Have to throw error message.");
        } catch (EmptyValueException e) {
            Assert.assertThat("The parameter of 'scheduleId' must have value.", equalTo(e.getMessage()));
        }
    }

    @Test
    public void shouldThrowExceptionWhenGetScheduleByCityNameWasNull() {
        try {
            scheduleResource.getSchedulesByCity(null, 1);
            Assert.fail("Have to throw error message.");
        } catch (EmptyValueException e) {
            Assert.assertThat("The parameter of 'cityName' must have value.", equalTo(e.getMessage()));
        }
    }
}
