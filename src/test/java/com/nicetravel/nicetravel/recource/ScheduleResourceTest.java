package com.nicetravel.nicetravel.recource;

import com.nicetravel.nicetravel.exceptions.EmptyValueException;
import com.nicetravel.nicetravel.resource.ScheduleResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class ScheduleResourceTest {

    private ScheduleResource scheduleResource;

    @Before
    public void setUp() {
        scheduleResource = new ScheduleResource();
    }

    @Test
    public void shouldThrowExceptionWhenIdsWereNull() {
        try {
            scheduleResource.getSchedulesByIds(null);
            Assert.fail("Have to throw error message.");
        } catch (EmptyValueException e) {
            Assert.assertThat("The parameter of 'travelIds' must have at least one element.", equalTo(e.getMessage()));
        }
    }

    @Test
    public void shouldThrowExceptionWhenCityNameWasNull() {
        try {
            scheduleResource.getSchedulesByCity(null, 1);
            Assert.fail("Have to throw error message.");
        } catch (EmptyValueException e) {
            Assert.assertThat("The parameter of 'cityName' must have value.", equalTo(e.getMessage()));
        }
    }
}
