package geometry.shapes

import geometry.Directional
import geometry.Point
import geometry.Vector2D
import system.ITypeble
import kotlin.math.absoluteValue
import kotlin.math.sqrt

// TODO только не вырожденный

class Polygon(name: String, vararg points: Point, type: ITypeble.TypeShape = ITypeble.TypeShape.POLYGON): Shape(name, type) {

    init{
        if(arr.size < 3){
            throw IndexOutOfBoundsException("Input points < 3!")
        }
        for(p in points) {
            arr.add(p)
        }
        arr.add(points[0])
    }

    override fun testPointIn(p: Point): Boolean {
        val px3 = p.x
        val py3 = p.y
        val results: ArrayList<Boolean> = ArrayList()
        var i = 0
        while(i < arr.size - 1){
            val fx1 = arr[i].x
            val fy1 = arr[i].y
            val fx2 = arr[i+1].x
            val fy2 = arr[i+1].y
            val d = (px3 - fx1) * (fy2 - fy1) - (py3 - fy1) * (fx2 - fx1)
            if(d >= eps){
                results.add(true)
            } else {
                results.add(false)
            }
            ++i
        }
        for (r in results){
            if(!r){
                return false
            }
        }
        return true
    }

    override fun printInfo() {
        super.printInfo()
        for(p in arr) {
            p.printInfo()
        }
    }

    override fun getInfo(): String {
        var out = super.getInfo()
        for(p in arr) {
            out += p.getInfo()
        }
        return out
    }

    override fun square(): Float {
        var i = 0
        var sShape = 0f
        while(i < arr.size - 2){
            val fx1 = arr[0].x
            val fy1 = arr[0].y
            val fx2 = arr[i].x
            val fy2 = arr[i].y
            val fx3 = arr[i+1].x
            val fy3 = arr[i+1].y
            val vector1 = Vector2D(fx1, fy1, fx2, fy2)
            val vector2 = Vector2D(fx2, fy2, fx3, fy3)
            val vector3 = Vector2D(fx3, fy3, fx1, fy1)
            val sg1 = vector1.getLength().absoluteValue
            val sg2 = vector2.getLength().absoluteValue
            val sg3 = vector3.getLength().absoluteValue
            val pTriangle = perimeterTriangle(sg1, sg2, sg3)
            sShape += squareTriangle(sg1, sg2, sg3, pTriangle)
            ++i
        }
        return sShape
    }

    private fun perimeterTriangle(sg1: Float, sg2: Float, sg3: Float): Float{
        return (sg1 + sg2 + sg3) / 2
    }

    private fun squareTriangle(sg1: Float, sg2: Float, sg3: Float, p: Float): Float{
        return sqrt(p * (p - sg1) * (p - sg2) * (p - sg3))
    }

    fun isIntersectPolygons(p: Polygon): ArrayList<Point>{
        var i = 0
        var intersectsPoints = ArrayList<Point>()
        while(i < arr.size - 1){
            var j = 0
            var countIntersectShape2 = 0
            val line1 = Directional(arr[i], arr[i+1])
            while(j < p.arr.size - 1){
                val line2 = Directional(p.arr[j], p.arr[j+1])
                val intersection = line1.getIntersectionPoint(line2)
                if (intersection != null) {
                    val sect1 = Section("sect1", arr[i], arr[i+1])
                    val sect2 = Section("sect2", p.arr[j], p.arr[j+1])
                    if(sect1.isIntersectionSection(sect2, intersection)){
                        intersectsPoints.add(intersection)
                    }
                }else{
                    println("Direct is parallel")
                }
                j += 1
            }
            i += 1
        }
        return intersectsPoints
    }

}