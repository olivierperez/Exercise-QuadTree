package fr.o80

class QuadTreeImpl(
    private val maxSize: Int,
    private val zone: Rectangle
) : QuadTree {

    internal val northWest by lazy { QuadTreeImpl(maxSize, Rectangle(zone.min, zone.center)) }
    internal val northEast by lazy { QuadTreeImpl(maxSize, Rectangle(zone.topCenter, zone.centerRight)) }
    internal val southWest by lazy { QuadTreeImpl(maxSize, Rectangle(zone.centerLeft, zone.bottomCenter)) }
    internal val southEast by lazy { QuadTreeImpl(maxSize, Rectangle(zone.center, zone.max)) }

    internal val points: MutableList<Point> = mutableListOf()

    override fun add(point: Point) {
        if (points.size < maxSize) {
            points.add(point)
        } else if (point.x <= zone.center.x && point.y <= zone.center.y) {
            northWest.add(point)
        } else if (point.x <= zone.center.x && point.y > zone.center.y) {
            southWest.add(point)
        } else if (point.x > zone.center.x && point.y <= zone.center.y) {
            northEast.add(point)
        } else if (point.x > zone.center.x && point.y > zone.center.y) {
            southEast.add(point)
        }
    }

    override fun intersectionWith(zone: Rectangle): List<Point> {
        return if (this.zone.isOverlapping(zone)) {
            TODO()
        } else {
            emptyList()
        }
    }
}