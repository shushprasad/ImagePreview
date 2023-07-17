# ImagePreview
**About Library**
The Dog Image Viewer is an Android app that allows you to fetch and display random dog images using the Dog API.
It utilizes the ImageFetcher library to handle the fetching and caching of dog images.

## Features

- Fetches random dog images from the Dog API
- Allows you to navigate between images using next and previous buttons
- Provides an input field to specify the number of dog images to fetch and display

## Architecture

The Dog Image Viewer app follows the Model-View-ViewModel (MVVM) architecture pattern. The key components are:

- **Model:** The `ImageFetcher` class handles the fetching and caching of dog images. It uses Retrofit to make API calls to the Dog API and Gson for JSON parsing. The fetched images are stored in memory using a mutable list.

- **View:** The `MainActivity` class is responsible for displaying the app's UI. It utilizes `ImageView`, `Button`, and `EditText` components to show the dog images, navigate between images, and accept user input, respectively.

- **ViewModel:** The `MainActivity` class also serves as the ViewModel, as it handles the interaction between the View and the Model. It utilizes Kotlin coroutines and the `lifecycleScope` to perform asynchronous operations and update the UI.

## Usage

To use the Dog Image Viewer library in your own Android project, follow these steps:

1. Add the following dependency to your app-level build.gradle file:

   ```groovy
   implementation 'com.squareup.retrofit2:retrofit:2.9.0'
   implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

## Implementation of Library into your Project

Add Dependency you build.gradle file
  ```groovy
maven { url 'https://jitpack.io' } - settings.gradle
implementation 'com.github.shushprasad:TestLibrary:1.0.0' - build.gradle

1. Copy the ImageFetcher class into your project's source code.
2. Create an instance of ImageFetcher in your activity or fragment.
   

private lateinit var dogImageLibrary: ImageFetcher
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    dogImageLibrary = ImageFetcher()
    // ...
}


3. Use the library methods to fetch and display dog images. Here's an example of how to fetch and display 10 random dog images.

 
lifecycleScope.launch {
    val images: List<String> = dogImageLibrary.getRandomDogImages(10) // Fetch 10 dog images
    dogImageLibrary.initialize(10) // Pass the number of images to initialize
    // Display the first image
    showNextImage()
}
4. Customize the UI components in your layout file to suit your app's design.

Note:
This project was created using Kotlin and follows the MVVM architecture pattern. The app fetches dog images using the Dog API and provides a simple interface to navigate through the images. The ImageFetcher library handles the API calls and image caching.

**Happy dog image viewing!**
