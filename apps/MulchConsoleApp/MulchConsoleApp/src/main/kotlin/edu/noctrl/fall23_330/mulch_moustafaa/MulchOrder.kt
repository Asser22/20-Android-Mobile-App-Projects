package edu.noctrl.fall23_330.mulch_moustafaa

import kotlin.math.ceil
data class PlantingBedDimensions(val length: Double, val width: Double, val depth: Double)

interface MulchPricer {
    fun calculatePrice(cubicYards: Int): Double
}

class CubicYardMulchPricer : MulchPricer {
    override fun calculatePrice(cubicYards: Int): Double {
        return when {
            cubicYards <= 3 -> 33.50 * cubicYards
            cubicYards in 4..9 -> 31.50 * cubicYards
            else -> 29.50 * cubicYards
        }
    }
}

class CubicFootMulchPricer : MulchPricer {
    override fun calculatePrice(cubicYards: Int): Double {
        val cubicFeet = cubicYards * 27
        val bags = Math.ceil(cubicFeet / 2.0).toInt()

        return when {
            bags <= 5 -> 3.97 * bags
            bags in 6..9 -> 3.47 * bags
            else -> 2.97 * bags
        }
    }
}

class MulchOrder(initialBed: PlantingBedDimensions) {
    private val plantingBeds = mutableListOf(initialBed)
    var pricer: MulchPricer? = null

    fun addPlantingBed(bed: PlantingBedDimensions) {
        plantingBeds.add(bed)
    }

    private fun totalCubicYards(): Int {
        val total = plantingBeds.sumOf { it.length * it.width * (it.depth / 12) }
        return ceil(total / 27).toInt()
    }

    private fun totalCubicFeet(): Int {
        return ceil(totalCubicYards() * 27.0).toInt()
    }

    fun printOrderDetails() {
        println("Order Details:")
        plantingBeds.forEach {
            println("Planting bed: ${it.length}ft x ${it.width}ft x ${it.depth}in")
        }
        println("Total cubic yards: ${totalCubicYards()}")
        println("Total cubic feet: ${totalCubicFeet()}")

        pricer?.let {
            val totalPrice = it.calculatePrice(totalCubicYards())
            println("Total price: $${String.format("%.2f", totalPrice)}")
        } ?: run {
            println("Pricing not set")
        }
    }
}