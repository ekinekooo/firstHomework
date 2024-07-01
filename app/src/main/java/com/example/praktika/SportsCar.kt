package com.example.praktika

class SportsCar(brand: String, model: String, year: Int, val topSpeed: Int) : Car(brand, model, year) {
    override fun printInfo() {
        super.printInfo()
        println("Top Speed: $topSpeed")
    }
}