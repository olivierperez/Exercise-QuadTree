package fr.o80

class QuadTreeImpl(
    private val maxSize: Int,
    private val zone: Rectangle
) : QuadTree {

    private var subTreeInitialized = false
    internal var northEast: QuadTreeImpl? = null
    internal var northWest: QuadTreeImpl? = null
    internal var southEast: QuadTreeImpl? = null
    internal var southWest: QuadTreeImpl? = null

    internal val points: MutableList<Point> = mutableListOf()

    override fun add(point: Point) {
        if (point !in zone) return

        if (points.size < maxSize) {
            points.add(point)
        } else {
            synchronized(subTreeInitialized) {
                if (!subTreeInitialized) {
                    northEast = QuadTreeImpl(maxSize, Rectangle(zone.topCenter, zone.centerRight))
                    northWest = QuadTreeImpl(maxSize, Rectangle(zone.min, zone.center))
                    southEast = QuadTreeImpl(maxSize, Rectangle(zone.center, zone.max))
                    southWest = QuadTreeImpl(maxSize, Rectangle(zone.centerLeft, zone.bottomCenter))
                    subTreeInitialized = true
                }
            }
            northEast!!.add(point)
            northWest!!.add(point)
            southEast!!.add(point)
            southWest!!.add(point)
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