package com.asser.learningapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun subject_isCorrect() {
        assertEquals("Mathematics", "Mathematics")
        assertEquals("Computer Science", "Computer Science")
        assertEquals("Physics", "Physics")
        assertEquals("Japanese", "Japanese")
    }

    @Test
    fun subsubject_for_Mathematics_isCorrect() {
        assertEquals("Pre-Calculus", "Pre-Calculus")
        assertEquals("Calculus", "Calculus")
        assertEquals("Statistics", "Statistics")
    }

    @Test
    fun subsubject_for_ComputerScience_isCorrect() {
        assertEquals("Mobile Apps", "Mobile Apps")
        assertEquals("Linux Programming", "Linux Programming")
        assertEquals("Discrete Structure", "Discrete Structure")
    }

    @Test
    fun subsubject_for_Physics_isCorrect() {
        assertEquals("Classic Physics", "Classic Physics")
        assertEquals("Waves", "Waves")
        assertEquals("Nuclear Physics", "Nuclear Physics")
    }

    @Test
    fun subsubject_for_Japanese_isCorrect() {
        assertEquals("Hiragana", "Hiragana")
        assertEquals("Kanji", "Kanji")
        assertEquals("Statistics", "Statistics")
    }
}