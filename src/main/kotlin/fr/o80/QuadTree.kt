package fr.o80

interface QuadTree {
    val zone: Rectangle

    fun add(point: Point)
    fun intersectionWith(zone: Rectangle): List<Point>
    fun forEachTree(block: QuadTree.() -> Unit)
    fun forEachPoint(block: Point.() -> Unit)
}
