# 🎬 Watchoo — Movie & TV Show App

A modern Android app built with **Jetpack Compose** that lets users explore trending **Movies** and **TV Shows** in one place.  
It features smooth UI transitions, a splash screen, shimmer loading effects, and error handling with a retry button.  

---

## 🚀 Features

✅ **Animated Splash Screen** — Beautiful custom splash with app logo animation.  
✅ **Dual API Integration** — Fetches both **Movies** and **TV Shows** simultaneously using `Single.zip`.  
✅ **Shimmer Loading Effect** — Displays shimmer placeholders while data loads for a smoother experience.  
✅ **Retry Button** — Appears automatically on failed network calls to allow users to retry fetching data.  
✅ **Material 3 UI** — Built using the latest **Jetpack Compose Material 3** components.  
✅ **Dark Background + White Icons** — Clean, modern, and cinematic design.

---

## 🧠 Tech Stack

- **Language:** Kotlin  
- **UI:** Jetpack Compose (Material 3)  
- **Architecture:** MVVM  
- **Networking:** Retrofit + RxJava  
- **Image Loading:** Coil  
- **Navigation:** Compose Navigation with Animated Transitions  
- **Dependency Injection:** Hilt (optional if implemented)  

---

## 🖼️ Screens

- **Splash Screen:** Displays app logo with animation.  
- **Home Screen:**  
  - Displays both Movies and TV Shows using two API calls combined with `Single.zip`.  
  - Uses shimmer placeholders while loading data.  
  - Each item card navigates to its detailed page on click.  
- **Details Screen:**  
  - Displays more information about the selected movie or TV show.  
  - Smooth zoom-in animation from Home to Details and zoom-out on back.

---

## ⚙️ API Used

This app uses **The Movie Database (TMDb)** API to fetch Movies and TV Show data.  
👉 [https://developer.themoviedb.org/](https://developer.themoviedb.org/)

---

## 🛠️ Future Enhancements

  - 🚧 Planned future updates and improvements:

  - ⭐ Add Movie & TV Show Ratings on Home Screen cards.

  - 🎭 Show Cast & Crew Details on the Details Screen.

  - 📺 Display Streaming Partners (e.g., Netflix, Prime Video) for each title.

  - 🔍 Add search functionality for Movies and TV Shows.

  - ❤️ Add Favorites section using Room database.

  - ♾️ Implement pagination for infinite scrolling.

  - 📡 Add offline caching and network awareness.

    # Note: Don't forget to add your own Watchmode API key in WatchModeRepository.kt to fetch data successfully.

## 🧩 Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/kdhyani200/Watchoo.git
