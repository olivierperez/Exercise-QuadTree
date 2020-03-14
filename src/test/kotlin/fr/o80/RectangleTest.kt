package fr.o80

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class RectangleTest {

    @Test
    @DisplayName("The two partially-overlapping rectangles should overlap")
    fun shouldOverlapWhenRectanglesArePartiallyOverlapping() {
        // Given
        val first = Rectangle(0f, 0f, 10f, 10f)
        val second = Rectangle(8f, 5f, 12f, 6f)

        // When
        val overlapping = first.isOverlapping(second)

        // Then
        assertTrue(overlapping, "The two rectangles should overlap: $first / $second")
    }

    @Test
    @DisplayName("Inside Rectangle should be overlapped by the outer one")
    fun shouldOverlapASmallOneByABigOne() {
        // Given
        val first = Rectangle(0f, 0f, 10f, 10f)
        val second = Rectangle(-100f, -100f, 100f, 100f)

        // When
        val overlapping = first.isOverlapping(second)

        // Then
        assertTrue(overlapping, "The two rectangles should overlap: $first / $second")
    }

    @Test
    @DisplayName("Sometimes Rectangle should not overlap each other")
    fun shouldNotBeDetectedAsOverlapping() {
        // Given
        val center = Rectangle(0f, 0f, 10f, 10f)

        val right = Rectangle(15f, -5f, 25f, 9f)
        val left = Rectangle(-25f, -5f, -5f, 9f)
        val top = Rectangle(3f, -10f, 12f, -0.5f)
        val bottom = Rectangle(3f, 12f, 12f, 15f)

        // When
        val overlappingWithRight = center.isOverlapping(right)
        val overlappingWithLeft = center.isOverlapping(left)
        val overlappingWithTop = center.isOverlapping(top)
        val overlappingWithBottom = center.isOverlapping(bottom)

        // Then
        assertFalse(overlappingWithRight)
        assertFalse(overlappingWithLeft)
        assertFalse(overlappingWithTop)
        assertFalse(overlappingWithBottom)
    }

    @Test
    @DisplayName("Compute center of Rectangle")
    fun shouldComputeCenter() {
        // Given
        val easyOne = Rectangle(0f, 0f, 40f, 40f)
        val positiveOne = Rectangle(10f, 10f, 50f, 50f)
        val halfWayOne = Rectangle(-10f, -10f, 90f, 90f)

        // When
        val easyCenter = easyOne.center
        val positiveCenter = positiveOne.center
        val halfWayCEnter = halfWayOne.center

        // Then
        assertEquals(Point(20f, 20f), easyCenter)
        assertEquals(Point(30f, 30f), positiveCenter)
        assertEquals(Point(40f, 40f), halfWayCEnter)
    }
}