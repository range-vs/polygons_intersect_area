package geometry.shapes

import geometry.Point
import system.ITypeble

class Section(name: String, p0: Point, p1: Point): Shape(name, ITypeble.TypeShape.SECTION) {

    init{
        arr.add(p0)
        arr.add(p1)
    }

    override fun testPointIn(p: Point): Boolean {
        val px3 = p.x
        val py3 = p.y
        val fx1 = arr[0].x
        val fy1 = arr[0].y
        val fx2 = arr[1].x
        val fy2 = arr[1].y
        val d = (px3 - fx1) * (fy2 - fy1) - (py3 - fy1) * (fx2 - fx1)
        val rangeX = if (fx1 <= fx2) {
            fx1 .. fx2
        }else{
            fx2 .. fx1
        }
        val rangeY = if (fy1 <= fy2) {
            fy1 .. fy2
        }else{
            fy2 .. fy1
        }
        return (d in 0f..eps && ((px3 in rangeX) || (py3 in rangeY)))
    }

    override fun printInfo() {
        super.printInfo()
        arr[0].printInfo()
        arr[1].printInfo()
    }

    override fun getInfo(): String {
        var out = super.getInfo()
        out += arr[0].getInfo()
        out += arr[1].getInfo()
        return out
    }

    override fun square(): Float {
        return 0f
    }

    fun isIntersectionSection(sect: Section, p: Point):Boolean{
        val checkPointIsRange = {x1: Float, y1: Float, x2: Float, y2: Float, x: Float, y: Float ->
            val rangeX = if(x1 >= x2){
                x2..x1
            }else{
                x1..x2
            }
            val rangeY = if(y1 >= y2){
                y2..y1
            }else{
                y1..y2
            }
            x in rangeX && y in rangeY
        }
        return checkPointIsRange(arr[0].x, arr[0].y, arr[1].x, arr[1].y, p.x, p.y) &&
                checkPointIsRange(sect.arr[0].x, sect.arr[0].y, sect.arr[1].x, sect.arr[1].y, p.x, p.y)
    }
}