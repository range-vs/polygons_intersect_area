package geometry.shapes

import geometry.Point
import system.ITypeble
import kotlin.math.pow
import kotlin.math.sqrt

class Circle(name: String, center: Point, radius: Float): Shape(name, ITypeble.TypeShape.CIRCLE) {

    var radius: Float = radius
    private val pi: Float = 3.14f

    init{
        arr.add(center)
    }

    override fun testPointIn(p: Point): Boolean {
        val s = sqrt((p.x - arr[0].x).pow(2) + (p.y - arr[0].y).pow(2))
        return s > radius
    }

    override fun printInfo() {
        super.printInfo()
        "${arr[0].printInfo()}"
        println("radius: ${radius}")
    }

    override fun getInfo(): String {
        return "${super.getInfo()}${arr[0].getInfo()}\nradius: ${radius}"
    }

    override fun square(): Float {
        return pi * radius * radius
    }

}
