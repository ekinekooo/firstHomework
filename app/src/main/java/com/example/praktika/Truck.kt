package com.example.praktika

class Truck(brand: String, model: String, year: Int, val cargoCapacity: Int) : Car(brand, model, year) {
    override fun printInfo() {
        super.printInfo()
        println("Cargo Capacity: $cargoCapacity")
    }
}