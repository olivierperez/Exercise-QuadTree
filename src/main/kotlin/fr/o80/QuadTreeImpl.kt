package fr.o80

class QuadTreeImpl(
    private val maxSize: Int,
    private val zone: Rectangle
) : QuadTree {

    internal var northEast: QuadTreeImpl? = null
    internal var northWest: QuadTreeImpl? = null
    internal var southEast: QuadTreeImpl? = null
    internal var southWest: QuadTreeImpl? = null

    internal val points: MutableList<Point> = mutableListOf()

    override fun add(point: Point) {
        if (point !in zone) return
        
        if (points.size < maxSize) {
            points.add(point)
        } else if (point.x <= zone.center.x && point.y <= zone.center.y) {
            if (northWest == null)
                northWest = QuadTreeImpl(maxSize, Rectangle(zone.min, zone.center))
            northWest!!.add(point)
        } else if (point.x <= zone.center.x && point.y > zone.center.y) {
            if (southWest == null)
                southWest = QuadTreeImpl(maxSize, Rectangle(zone.centerLeft, zone.bottomCenter))
            southWest!!.add(point)
        } else if (point.x > zone.center.x && point.y <= zone.center.y) {
            if (northEast == null)
                northEast = QuadTreeImpl(maxSize, Rectangle(zone.topCenter, zone.centerRight))
            northEast!!.add(point)
        } else if (point.x > zone.center.x && point.y > zone.center.y) {
            if (southEast == null)
                southEast = QuadTreeImpl(maxSize, Rectangle(zone.center, zone.max))
            southEast!!.add(point)
        }
    }

    override fun intersectionWith(zone: Rectangle): List<Point> {
        return if (this.zone.isOverlapping(zone)) {
            val intersection = mutableListOf<Point>()

            intersection.addAll(points.filter { it in zone })

            northEast?.run { intersection.addAll(intersectionWith(zone)) }
            northWest?.run { intersection.addAll(intersectionWith(zone)) }
            southEast?.run { intersection.addAll(intersectionWith(zone)) }
            southWest?.run { intersection.addAll(intersectionWith(zone)) }

            intersection
        } else {
            emptyList()
        }
    }
}