package fr.o80

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class QuadTreeImplTest {
    // TODO Check we can't add outside of QuadTree

    @Test
    @DisplayName("A QuadTree should add a point")
    fun shouldAddPoint() {
        // Given
        val quadTree = QuadTreeImpl(4, Rectangle(Point(0f, 0f), Point(100f, 100f)))

        // When
        quadTree.add(Point(13f, 12f))

        // Then
        assertEquals(1, quadTree.points.size)
        assertEquals(Point(13f, 12f), quadTree.points[0])
    }

    @Test
    @DisplayName("A QuadTree should add more points than its size")
    fun shouldAddTooManyPoints() {
        // Given
        val quadTree = QuadTreeImpl(4, Rectangle(Point(0f, 0f), Point(100f, 100f)))

        // When
        quadTree.add(Point(13f, 12f))
        quadTree.add(Point(14f, 14f))
        quadTree.add(Point(15f, 15f))
        quadTree.add(Point(16f, 16f))

        // NW
        quadTree.add(Point(1f, 49.5f))
        quadTree.add(Point(29f, 13.01f))
        // SW
        quadTree.add(Point(2.3f, 51.8f))
        // NE
        quadTree.add(Point(51.3f, 12.01f))
        // SE
        quadTree.add(Point(55f, 87f))

        // Then
        assertEquals(4, quadTree.points.size)
        assertEquals(2, quadTree.northWest.points.size)
        assertEquals(1, quadTree.southWest.points.size)
        assertEquals(1, quadTree.northEast.points.size)
        assertEquals(1, quadTree.southEast.points.size)
    }

    @Test
    @DisplayName("Intersect with with small QuadTree")
    fun shouldIntersectSmallQuadTree() {
        // Given
        val quadTree = QuadTreeImpl(4, Rectangle(0f, 0f, 24f, 37f))

        // When
        quadTree.add(Point(13f, 12f))
        quadTree.add(Point(23f, 35f))
        val intersection = quadTree.intersectionWith(Rectangle(10f, 11f, 14f, 27f))

        // Then
        assertEquals(1, intersection.size)
        assertEquals(Point(13f, 12f), intersection[0])
    }

}