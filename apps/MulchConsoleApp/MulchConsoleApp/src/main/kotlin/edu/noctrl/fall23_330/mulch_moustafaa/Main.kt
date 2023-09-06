package edu.noctrl.fall23_330.mulch_moustafaa

fun main(args: Array<String>) {
    val order1 = MulchOrder(PlantingBedDimensions(5.0, 3.0, 6.0))
    order1.addPlantingBed(PlantingBedDimensions(6.0, 4.0, 6.0))
    order1.addPlantingBed(PlantingBedDimensions(7.0, 5.0, 6.0))

    order1.pricer = CubicYardMulchPricer()
    order1.printOrderDetails()

    println("------------")

    val order2 = MulchOrder(PlantingBedDimensions(5.0, 3.0, 6.0))
    order2.addPlantingBed(PlantingBedDimensions(6.0, 4.0, 6.0))
    order2.addPlantingBed(PlantingBedDimensions(7.0, 5.0, 6.0))

    order2.pricer = CubicFootMulchPricer()
    order2.printOrderDetails()

    println("------------")

    val order3 = MulchOrder(PlantingBedDimensions(30.0, 10.0, 5.0))
    order3.addPlantingBed(PlantingBedDimensions(43.0, 14.0, 4.0))

    order3.pricer = CubicFootMulchPricer()
    order3.printOrderDetails()
}
