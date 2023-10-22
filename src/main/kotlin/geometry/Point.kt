package geometry

import helper.ILogger
import helper.Logger
import system.INameble
import system.IPrintable
import kotlin.math.sqrt

interface IPairNumber: IPrintable{

    var x: Float
    var y: Float

    override fun printInfo()

}

open class Point(x:Float, y:Float, override var name: String = ""): IPairNumber, INameble {

    override var x: Float = x
    override var y: Float = y

    companion object {
        val comparator: Comparator<Point> = Comparator { p1, p2 ->
            if (p1.x != p2.x) {
                p1.x.compareTo(p2.x)
            } else {
                p1.y.compareTo(p2.y)
            }
        }
    }

    override fun printInfo(){
        if(name != ""){
            println(name)
        }
        println("$x,$y")
    }

    override fun getInfo(): String {
        return "${name}\n$x,$y"
    }

}

class PointLogger(l: Logger): ILogger by l

// TODO проверить копланарные, коллинеарные векторы, и тд
class Vector2D(x:Float, y:Float, override var name: String = ""): Point(x, y, name) {

    constructor(p1: Point, p2: Point, name: String = ""):this(0f, 0f, name){
        createVector2DFromTwoPoint(p1, p2)
    }

    constructor(x1: Float, y1: Float, x2: Float, y2: Float, name: String = ""):this(0f, 0f, name){
        createVector2DFromTwoPoint(Point(x1, y1), Point(x2, y2))
    }

    fun getLength(): Float {
        return sqrt(x * x + y * y)
    }

    fun createVector2DFromTwoPoint(p1: Point, p2: Point){
        x = p2.x - p1.x
        y = p2.y - p1.y
    }


}