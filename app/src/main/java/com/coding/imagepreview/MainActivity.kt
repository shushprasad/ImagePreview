package com.coding.imagepreview


import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.coding.image_fetcher.ImageFetcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var dogImageLibrary: ImageFetcher
    private lateinit var imageView: ImageView
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        nextButton = findViewById(R.id.nextButton)
        previousButton = findViewById(R.id.previousButton)

        dogImageLibrary = ImageFetcher()

        lifecycleScope.launch {
            val images: List<String> = dogImageLibrary.getRandomDogImages(10) // Fetch 10 dog images
            dogImageLibrary.initialize(10) // Pass the number of images to initialize
            showNextImage()
        }


        nextButton.setOnClickListener {
            lifecycleScope.launch {
                showNextImage()
            }
        }

        previousButton.setOnClickListener {
            showPreviousImage()
        }

        val submitButton: Button = findViewById(R.id.submitButton)
        val numberInput: EditText = findViewById(R.id.numberInput)

        submitButton.setOnClickListener {
            val number = numberInput.text.toString().toIntOrNull()
            if (number != null && number in 1..10) {
                lifecycleScope.launch {
                    dogImageLibrary.initialize(number)
                    showNextImage()
                }
            } else {
                // Handle invalid input
                Toast.makeText(this, "Please enter a number between 1 and 10", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private suspend fun showNextImage() {
        withContext(Dispatchers.Main) {
            nextButton.isEnabled = false
            previousButton.isEnabled = false
        }

        dogImageLibrary.getNextImage() // Update the current index

        val image: Bitmap? = dogImageLibrary.getImage()

        withContext(Dispatchers.Main) {
            if (image != null) {
                imageView.setImageBitmap(image)
                previousButton.isEnabled = true
            } else {
                imageView.setImageResource(R.drawable.placeholder)
            }
            nextButton.isEnabled = true
        }
    }

    private fun showPreviousImage() {
        dogImageLibrary.getPreviousImage()
        lifecycleScope.launch {
            val image: Bitmap? = dogImageLibrary.getImage()
            withContext(Dispatchers.Main) {
                if (image != null) {
                    imageView.setImageBitmap(image)
                    nextButton.isEnabled = true
                } else {
                    imageView.setImageResource(R.drawable.placeholder)
                    nextButton.isEnabled = false
                }
            }
        }
    }
}

