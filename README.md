# 📱 Betssom Group - Android Coding Challenge

This project is a complete refactoring of a legacy app that calculates the real-time status of odds during sporting events. The goal was to fix bugs, improve the user experience, and apply modern Android architecture and development best practices.

---

## ✅ What was done

- ✅ Fixed item layout size and content
- ✅ Added image, odds, and sell-in per item
- ✅ Implemented sorting by `sellIn` (descending)
- ✅ Added a button to update odds in real-time
- ✅ Added a button to reset odds to initial state
- ✅ Refactored to use **MVVM** architecture
- ✅ Integrated **Koin** for dependency injection
- ✅ Used **Glide** with ProgressBar for image loading
- ✅ Loaded odds data from local **JSON**
- ✅ Added unit tests using **JUnit** and **KoinTest**

---

## 🧰 Technologies Used

| Technology           | Reason for Use                                                             |
|----------------------|----------------------------------------------------------------------------|
| **Kotlin**           | Modern, official language for Android development                         |
| **MVVM**             | Clear separation between UI and business logic                             |
| **Koin**             | Simple and effective dependency injection for ViewModel and Repository     |
| **LiveData**         | Reactive UI updates                                                        |
| **Glide**            | Efficient image loading with caching and listener support                  |
| **ConstraintLayout** | Flexible and performant layout system                                      |
| **RecyclerView**     | Efficient display of scrollable lists                                      |
| **JSON + Gson**      | Simulated API-like data source                                             |
| **JUnit**            | Unit testing for ViewModel and dependency injection                        |
| **Mockk** (optional) | Can be used for mocking dependencies if needed                            |

---

## 🔧 Project Structure

```plaintext
com.betsson.interviewtest
├── data
│   ├── model           → Bet.kt
│   ├── repository      → BetRepository.kt + BetRepositoryImpl.kt
│   └── util            → JsonUtils.kt
├── di                  → AppModule.kt (Koin config)
├── presentation
│   └── BetViewModel.kt
├── ui
│   ├── adapter         → ItemAdapter.kt
│   ├── betlist         → BetListFragment.kt
│   └── MainActivity.kt
└── MyApplication.kt
```

---

## 🧪 Added Tests

- Injection of `BetViewModel` using Koin
- Loading and parsing data from mocked JSON
- Validation of initial list (`size == 6`, expected names)

---

## 📸 Visual Result

> List showing image, name, odds, and sell-in + buttons for updating/resetting odds with loading indicator.

---

## 👨‍💻 How to Run

```bash
# Clone the repository
$ git clone https://github.com/your-username/android-betssom-test
$ cd android-betssom-test

# Open in Android Studio
```

> Run on an emulator or physical device with internet access to fetch images using Glide.

---

## 💡 Final Thoughts

The project was restructured with a focus on modern Android development practices, clear architecture, and separation of concerns. The solution is scalable and ready for integration with real APIs in the future.

---

