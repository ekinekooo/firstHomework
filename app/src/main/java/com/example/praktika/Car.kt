package com.example.praktika

open class Car(val brand: String, val model: String, val year: Int) {
    open fun printInfo() {
        println("Brand: $brand, Model: $model, Year: $year")
    }
}