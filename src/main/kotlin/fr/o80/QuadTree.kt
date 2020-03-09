package fr.o80

interface QuadTree {
    fun add(point: Point)
    fun intersectionWith(zone: Rectangle): List<Point>
}
