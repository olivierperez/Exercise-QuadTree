package fr.o80

class Rectangle(
    val min: Point,
    val max: Point
) {

    constructor(minX: Float, minY: Float, maxX: Float, maxY: Float) :
            this(Point(minX, minY), Point(maxX, maxY))

    val center = Point((max.x - min.x) / 2, (max.y - min.y) / 2)
    val topCenter get() = Point(center.x, min.y)
    val bottomCenter get() = Point(center.x, max.y)
    val centerRight get() = Point(max.x, center.y)
    val centerLeft get() = Point(min.x, center.y)

    init {
        check(min.x < max.x && min.y < max.y) { "min point should be les than max point" }
    }

    fun isOverlapping(other: Rectangle): Boolean {
        return min.x <= other.max.x && max.x >= other.min.x &&
                min.y <= other.max.y && max.y >= other.min.y
    }

    override fun toString(): String {
        return "[${min.x},${min.y},${max.x},${max.y}]"
    }

}