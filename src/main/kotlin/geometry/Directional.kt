package geometry

import kotlin.math.absoluteValue

class Directional(p1: Point, p2: Point) {

    private val eps = 0.001

    var a: Float = 0f
    var b: Float = 0f
    var c: Float = 0f

    init{
        createDirectional(p1, p2)
    }

    fun createDirectional(p1: Point, p2: Point){
        a = p2.y - p1.y
        b = p1.x - p2.x
        c = p2.x * p1.y - p1.x * p2.y
    }

    fun getIntersectionPoint(directional: Directional): Point?{
        val d = directional.a
        val e = directional.b
        val f = directional.c
        if((a / d - b / e).absoluteValue > eps) { // не параллельные прямые
            var x = 0f
            var y = 0f
            if((b * d - a * e).absoluteValue > eps && b.absoluteValue > eps){
                x = (c * e - b * f) / (b * d - a * e)
                y = (c * d - a * f) / (a * e - b * d)
                return Point(x, y)
            }
            if(b.absoluteValue <= eps && a.absoluteValue > eps && e.absoluteValue > eps){
                x = -c / a
                y = (c * d - a * f)/ (a * e)
                return Point(x, y)
            }
            // не уверен, нужны ли блоки ниже?
            if(f.absoluteValue <= eps && e.absoluteValue <= eps && d.absoluteValue <= eps && b.absoluteValue > eps){
                y = -(a*x +c) / b
                return Point(x, y)
            }
            if(c.absoluteValue <= eps && b.absoluteValue <= eps && a.absoluteValue <= eps && e.absoluteValue > eps && f.absoluteValue > eps){
                y = -(d*x +f) / e
                return Point(x, y)
            }
            if(e.absoluteValue <= eps && b.absoluteValue <= eps && f.absoluteValue > eps && (a - (c * d / f)).absoluteValue <= eps && (c * d).absoluteValue > eps ){
                x = - f / d
                return Point(x, y)
            }
        }
        return null
    }


}