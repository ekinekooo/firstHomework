package com.example.praktika


class Crossover(brand: String, model: String, year: Int, val driveType: String, val enginePower: Int) : Car(brand, model, year) {
    override fun printInfo() {
        super.printInfo()
        println("Drive Type: $driveType, Engine Power: $enginePower")
    }
}