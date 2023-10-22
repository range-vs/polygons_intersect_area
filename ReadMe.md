The simple application to calculate the intersection area  of two polygons.
Instruction:
1. Create two polygons. Example: ```var p1 = Polygon("name poly", Point(), ... )```
2. Call lambda named checkIntersect for check of intersections: ```var points = checkIntersect(p1, p2, false)```
3. Call lambda named calcSquare to calculate  areas of p1, p2 and intersection area: ```calcSquare(p1, p2, points)```

Restrictions:
1. Minimum  amount of points in polygon is three.
2. The polygon must be non-degenerate