package com.example.praktika

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.text_field)
        val button = findViewById<Button>(R.id.button)
        textView = findViewById<TextView>(R.id.result_text_view)

        button.setOnClickListener {
            val numCars = editText.text.toString().toInt()
            raceCars(numCars)
        }
    }

    private fun raceCars(numCars: Int) {
        val cars = mutableListOf<Car>()

        for (i in 1..numCars) {
            cars.add(createRandomCar())
        }

        while (cars.size > 1) {
            val racePair = getRandomRacePair(cars)
            println("Race between ${racePair.first.brand} ${racePair.first.model} and ${racePair.second.brand} ${racePair.second.model}")
            val winner = race(racePair.first, racePair.second)
            println("Winner: ${winner.brand} ${winner.model}")

            cars.removeAll { it == racePair.second || it == racePair.first }
            cars.add(winner)
        }

        println("Final Winner: ${cars[0].brand} ${cars[0].model}")
        textView.text = "Final Winner: ${cars[0].brand} ${cars[0].model}"
    }

    private fun createRandomCar(): Car {
        val randomBrand = getRandomBrand()
        val randomModel = getRandomModel()
        val randomYear = Random.nextInt(2022 - 2000) + 2000

        return when (Random.nextInt(4)) {
            0 -> Crossover(randomBrand, randomModel, randomYear, getRandomDriveType(), Random.nextInt(400) + 100)
            1 -> Sedan(randomBrand, randomModel, randomYear, Random.nextInt(5) + 2)
            2 -> Truck(randomBrand, randomModel, randomYear, Random.nextInt(1000) + 500)
            3 -> SportsCar(randomBrand, randomModel, randomYear, Random.nextInt(250) + 150)
            else -> Car(randomBrand, randomModel, randomYear)
        }
    }

    private fun getRandomBrand(): String {
        val brands = listOf("Toyota", "Ford", "Honda", "Nissan", "Volkswagen", "BMW", "Mercedes", "Audi")
        return brands[Random.nextInt(brands.size)]
    }

    private fun getRandomModel(): String {
        val models = listOf("Corolla", "Focus", "Civic", "Altima", "Golf", "3 Series", "C-Class", "A4")
        return models[Random.nextInt(models.size)]
    }

    private fun getRandomDriveType(): String {
        val driveTypes = listOf("Front-Wheel Drive", "Rear-Wheel Drive", "All-Wheel Drive")
        return driveTypes[Random.nextInt(driveTypes.size)]
    }

    private fun race(car1: Car, car2: Car): Car {
        return if (car1.year > car2.year) car1 else car2
    }

    private fun getRandomRacePair(cars: MutableList<Car>): Pair<Car, Car> {
        val index1 = Random.nextInt(cars.size)
        var index2 = Random.nextInt(cars.size - 1)
        if (index2 >= index1) index2++
        return Pair(cars[index1], cars[index2])
    }
}
