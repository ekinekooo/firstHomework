package com.example.praktika

class Sedan(brand: String, model: String, year: Int, val doors: Int) : Car(brand, model, year) {
    override fun printInfo() {
        super.printInfo()
        println("Doors: $doors")
    }
}