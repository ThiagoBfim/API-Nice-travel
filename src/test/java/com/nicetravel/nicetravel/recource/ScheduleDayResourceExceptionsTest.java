package com.nicetravel.nicetravel.recource;

import com.nicetravel.nicetravel.exceptions.EmptyValueException;
import com.nicetravel.nicetravel.resource.ScheduleDayResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class ScheduleDayResourceExceptionsTest {

    private ScheduleDayResource scheduleDayResource;

    @Before
    public void setUp() {
        scheduleDayResource = new ScheduleDayResource();
    }

    @Test
    public void shouldThrowExceptionWhenGetScheduleByIdWereNull() {
        try {
            scheduleDayResource.getScheduleDaysByScheduleCod(null);
            Assert.fail("Have to throw error message.");
        } catch (EmptyValueException e) {
            Assert.assertThat("The parameter of 'scheduleId' must have value.", equalTo(e.getMessage()));
        }
    }

}
