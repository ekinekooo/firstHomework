package com.example.praktika

class CarBuilder {
    private var brand: String = ""
    private var model: String = ""
    private var year: Int = 0

    fun setBrand(brand: String) = apply { this.brand = brand }
    fun setModel(model: String) = apply { this.model = model }
    fun setYear(year: Int) = apply { this.year = year }
    fun build() = Car(brand, model, year)
}