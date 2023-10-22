package system

interface ITypeble {

    enum class TypeShape{
        NONE,
        SECTION,
        POLYGON,
        CIRCLE
    }

    var type: TypeShape

}