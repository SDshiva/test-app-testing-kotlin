package com.shibam.testAppTesting.presentation.view

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.shibam.testAppTesting.R
import com.shibam.testAppTesting.app.features.login.presentation.view.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testLoginFormInteraction() {
        onView(withId(R.id.et_username)).perform(typeText("shibam"))
        onView(withId(R.id.et_password)).perform(typeText("1234"))
        closeSoftKeyboard()
        onView(withId(R.id.btn_register)).perform(click())
        onView(withId(R.id.btn_login)).perform(click())
        onView(withId(R.id.tv_result)).check(matches(withText("Login Successful")))
    }

    @Test
    fun testEmptyFieldValidation() {
        onView(withId(R.id.et_username)).perform(clearText())
        onView(withId(R.id.et_password)).perform(clearText())
        closeSoftKeyboard()
        onView(withId(R.id.btn_login)).perform(click())
        onView(withId(R.id.et_username)).check(matches(hasErrorText("Username is required")))
    }
}