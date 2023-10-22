package geometry.shapes

import geometry.ISquare
import geometry.Point
import helper.ILogger
import helper.Logger
import system.INameble
import system.IPrintable
import system.ITypeble

abstract class Shape(name: String, type: ITypeble.TypeShape): IPrintable, ITypeble, INameble, ISquare {

    protected val eps = 0.001f
    protected val arr = ArrayList<Point>()

    override var name: String = name
    override var type: ITypeble.TypeShape = type

    abstract fun testPointIn(p: Point): Boolean

    override fun printInfo(){
        println(type)
        println(name)
    }

    override fun getInfo(): String {
        return "$type\n$name"
    }

    fun getPoints(): Array<Point>{
        return arr.toTypedArray()
    }
}

class ShapeLogger(l: Logger): ILogger by l