package com.example.praktika2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textField = findViewById<EditText>(R.id.text_field)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val numCars = textField.text.toString().toInt()
            raceCars(numCars)
        }
    }

    private fun raceCars(numCars: Int) {
        val cars = mutableListOf<Car>()

        
        for (i in 1..numCars) {
            cars.add(
                CarBuilder()
                    .setBrand(getRandomBrand())
                    .setModel(getRandomModel())
                    .setYear(Random().nextInt(2022 - 2000) + 2000)
                    .setHorsepower(Random().nextInt(400) + 100)
                    .build()
            )
        }

        
        while (cars.size > 1) {
            val racePair = getRandomRacePair(cars)
            println("Race between ${racePair.first.brand} ${racePair.first.model} and ${racePair.second.brand} ${racePair.second.model}")
            val winner = race(racePair.first, racePair.second)
            println("Winner: ${winner.brand} ${winner.model}")

            
            cars.removeAll { it == racePair.second || it == racePair.first }
            cars.add(winner)
        }

        
        println("Winner: ${cars[0].brand} ${cars[0].model}")
    }

    private fun getRandomRacePair(cars: List<Car>): Pair<Car, Car> {
        val random1 = Random().nextInt(cars.size)
        val random2 = Random().nextInt(cars.size)

        
        return if (random1 == random2) {
            Pair(cars[random1], cars[(random1 + 1) % cars.size])
        } else {
            Pair(cars[random1], cars[random2])
        }
    }

    private fun getRandomBrand(): String {
        val brands = listOf("Toyota", "Ford", "Honda", "Nissan", "Volkswagen", "BMW", "Mercedes", "Audi")
        return brands[Random().nextInt(brands.size)]
    }

    private fun getRandomModel(): String {
        val models = listOf("Corolla", "Focus", "Civic", "Altima", "Golf", "3 Series", "C-Class", "A4")
        return models[Random().nextInt(models.size)]
    }

    private fun race(car1: Car, car2: Car): Car {
        
        return if (car1.horsepower > car2.horsepower) car1 else car2
    }
}
