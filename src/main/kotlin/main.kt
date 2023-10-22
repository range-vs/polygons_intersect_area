import geometry.Point
import geometry.PointLogger
import geometry.shapes.*
import helper.Logger
import java.lang.IndexOutOfBoundsException

fun main(){

    try {
        /*val shapes = ArrayList<Polygon>()
    shapes.add(Polygon("Triangle 1", Point(-2f, 1f), Point(0f, 2f), Point(1f, 0f)))
    shapes.add(Polygon("Pentagon 1", Point(-5f, -2f), Point(-3f, 0f), Point(0f, -2f), Point(0f, -4f)))

    val logger = Logger()
    for(s in shapes){
        //s.printInfo()
        ShapeLogger(logger).log(s.getInfo()) // test delegate
        println("Square: ${s.square()}")
        println("-------\n")
    }

    for(s1 in shapes){
        for(s2 in shapes){
            if(s2 != s1) {
                if (s2.isIntersectPolygons(s1)) {
                    println("${s2.name} is intersect ${s1.name}")
                } else {
                    println("${s2.name} isn't intersect ${s1.name}")
                }
            }
        }
    } */

        val checkIntersect = { p1: Polygon, p2: Polygon, log: Boolean ->
            var points: ArrayList<Point> = ArrayList()
            if (p1 != p2) {
                points = p1.isIntersectPolygons(p2)
                if (points.isNotEmpty()) {
                    if (log) {
                        println("${p1.name} is intersect ${p2.name}")
                    }
                } else {
                    if (log) {
                        println("${p1.name} isn't intersect ${p2.name}")
                    }
                }
            }
            points
        }
        val doubleCheckIntersect = { p1: Polygon, p2: Polygon ->
            checkIntersect(p1, p2, true)
            checkIntersect(p2, p1, true)
        }
        val findAllPointsInShapes = { p1: Polygon, p2: Polygon ->
            val points: ArrayList<Point> = ArrayList()
            var arr = p1.getPoints()
            for (i in 0..<arr.size - 1) {
                val p = arr[i]
                if (p2.testPointIn(p)) {
                    points.add(p)
                }
            }
            arr = p2.getPoints()
            for (i in 0..<arr.size - 1) {
                val p = arr[i]
                if (p1.testPointIn(p)) {
                    points.add(p)
                }
            }
            points
        }
        val calcSquare = { p1: Polygon, p2: Polygon, points: ArrayList<Point> ->
            if (points.isNotEmpty()) {
                val findPointsInShapes = findAllPointsInShapes(p1, p2)
                points.addAll(findPointsInShapes)
                if (points.size <= 2) {
                    println("Square intersect part ${p1.name} and ${p2.name} impossible")
                } else {
                    val sortedPoints = points.sortedWith(Point.comparator)
                    val polyIntersect =
                        Polygon("Polygon intersect part ${p1.name} and ${p2.name}", *sortedPoints.toTypedArray())
                    val polyIntersectSquare = polyIntersect.square()
                    val p1Square = p1.square()
                    val p2Square = p2.square()
                    println("Square ${polyIntersect.name}(intersect part) ${p1.name} and ${p2.name} = $polyIntersectSquare")
                    println("Square ${p1.name} = $p1Square")
                    println("Square ${p2.name} = $p2Square")
                    println("Square ${p1.name} - ${polyIntersect.name} = ${p1Square - polyIntersectSquare}")
                    println("Square ${p2.name} - ${polyIntersect.name} = ${p2Square - polyIntersectSquare}")
                }
            }
        }

        // НЕ перескаются
        var p1 = Polygon("Triangle 1", Point(-7.72f, 2.45f), Point(-8.56f, -1.33f), Point(-4.86f, 1.55f))
        var p2 =
            Polygon("Shape 1", Point(-1.52f, 4.29f), Point(-3.06f, 1.37f), Point(-5.72f, 4.49f), Point(-2.6f, 5.21f))
        doubleCheckIntersect(p1, p2)
        var points = checkIntersect(p1, p2, false)
        calcSquare(p1, p2, points)
        println("---")

        // ПЕРЕСЕКАЮТСЯ
        p2 = Polygon("Shape 1", Point(-1.52f, 4.29f), Point(-3.06f, 1.37f), Point(-5.72f, 4.49f), Point(-2.6f, 5.21f))
        p1 = Polygon("Shape 2", Point(0f, 3f), Point(2.74f, 1.39f), Point(-1.52f, 1.13f), Point(-3.12f, 3.65f))
        doubleCheckIntersect(p1, p2)
        points = checkIntersect(p1, p2, false)
        calcSquare(p1, p2, points)
        println("---")

        // ПЕРЕСЕКАЮТСЯ
        p2 = Polygon("Shape 1", Point(-1.52f, 4.29f), Point(-3.06f, 1.37f), Point(-5.72f, 4.49f), Point(-2.6f, 5.21f))
        p1 = Polygon("Triangle 2", Point(-5.22547f, 0.27803f), Point(-3.44073f, 3.21346f), Point(-2.14914f, 0.48938f))
        doubleCheckIntersect(p1, p2)
        points = checkIntersect(p1, p2, false)
        calcSquare(p1, p2, points)
        println("---")

        // ПЕРЕСЕКАЮТСЯ
        p1 = Polygon("Shape 2", Point(0f, 3f), Point(2.74f, 1.39f), Point(-1.52f, 1.13f), Point(-3.12f, 3.65f))
        p2 = Polygon(
            "Shape 4",
            Point(3.5137f, 5.0963f),
            Point(-1.56206f, 2.7438f),
            Point(0.95067f, 0.37197f),
            Point(3.4937f, 3.4963f)
        )
        doubleCheckIntersect(p1, p2)
        points = checkIntersect(p1, p2, false)
        calcSquare(p1, p2, points)
        println("---")

        // ФИГУРА ВНУТРИ ФИГУРЫ, НО НЕ ПЕРЕСЕКАЮТСЯ
        p1 = Polygon("Shape 5", Point(-7f, -2.43f), Point(-1.84f, -4.17f), Point(-4.2f, -8.43f), Point(-8.5f, -4.09f))
        p2 = Polygon("Triangle 3", Point(-6.4f, -4.81f), Point(-4.08f, -4.79f), Point(-4.54f, -6.11f))
        doubleCheckIntersect(p1, p2)
        points = checkIntersect(p1, p2, false)
        calcSquare(p1, p2, points)
        println("---")
    }catch (ex: IndexOutOfBoundsException){
        println(ex)
    }

}
