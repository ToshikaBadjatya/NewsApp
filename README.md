# News App

A production-ready Android news application built with modern MVVM architecture and cutting-edge Jetpack Compose.

---

##  Why This App Rocks

- **Instant Gratification**: Load and read news articles instantly, even without internet
- **Search Like a Pro**: Lightning-fast search and powerful filters to find exactly what you need
- **Read Anywhere**: Seamless offline caching means your saved articles travel with you
- **Built for Scale**: Clean MVVM architecture that scales without breaking a sweat
- **Modern Android**: Jetpack Compose + Kotlin = less boilerplate, more joy

---

##  Features at a Glance

###  **Top News Feed**
Browse the latest headlines with beautiful, image-rich cards. Swipe through trending stories as they break in real-time.

###  **Smart Search & Filters**
Find the exact story you're looking for with instant search. Filter by category, publication date, and source. No more scrolling through irrelevant articles.

###  **Offline Reading**
Saved articles stay with you. Room Database keeps everything synced and ready, even when you're in airplane mode.

###  **In-App Web Reading**
Read full articles directly in the app with WebView. No need to jump between browsers—everything stays in one place.


###  **Instant Image Loading**
Coil handles image caching and loading efficiently. Articles load fast, images render beautifully.

---

##  Architecture

This app follows **MVVM** principles with **Clean Architecture**. Every component has a single responsibility, making the codebase maintainable and testable.

```
├── common/                    # Shared utilities & exceptions
│   ├── CustomExceptions.kt
│   ├── Extensions.kt
│   └── GlobalState.kt
│
├── data/                      # Data layer (local + remote)
│   ├── impl/                  # Repository implementations
│   │   ├── NewsDatabaseImpl.kt
│   │   └── NewsNetworkImpl.kt
│   ├── local/                 # Room Database setup
│   │   ├── dao/
│   │   │   └── NewsDao.kt
│   │   └── database/
│   │       ├── ArticleEntity.kt
│   │       └── NewsDatabase.kt
│   ├── paging/                # Pagination logic
│   │   └── NewsPagingSource.kt
│   └── remote/                # API integration
│       ├── api/
│       │   └── NewsApi.kt
│       ├── models/
│       │   ├── Articles.kt
│       │   ├── Filters.kt
│       │   └── News.kt
│       └── network/
│           └── ApiKeyInterceptor.kt
│
├── di/                        # Dependency Injection (Hilt)
│   ├── module/
│   │   ├── ActivityModule.kt
│   │   ├── ApplicationModule.kt
│   │   ├── DatabaseModule.kt
│   │   ├── DataModule.kt
│   │   └── NetworkModule.kt
│   └── qualifier.kt
│
├── domain/                    # Domain layer (business logic)
│   └── NewsRepository.kt
│
├── interfaces/                # Abstraction layer
│   ├── DispatchersProvider.kt
│   └── Logger.kt
│
├── navigation/                # Jetpack Navigation
│   ├── Destination.kt
│   ├── MainNavGraph.kt
│   └── NavGraph.kt
│
├── ui/                        # UI layer (Compose)
│   ├── commonUi/              # Reusable components
│   │   ├── BottomNavigationBar.kt
│   │   ├── NewsItem.kt
│   │   ├── NewsPaginationList.kt
│   │   ├── StateUi.kt
│   │   └── TopHeadline.kt
│   ├── screens/               # Feature screens
│   │   ├── FilterScreen.kt
│   │   ├── MainLauncherScreen.kt
│   │   ├── NewsDescriptionScreen.kt
│   │   ├── SavedItemsScreen.kt
│   │   ├── SearchScreen.kt
│   │   ├── TopHeadlineScreen.kt
│   │   └── WebViewScreen.kt
│   ├── theme/                 # Design tokens
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── UiState.kt
│
├── utils/                     # Utility functions
│   ├── constants/
│   │   ├── Constants.kt
│   │   └── NetworkConstants.kt
│   └── others/
│       ├── ErrorUtils.kt
│       └── Utils.kt
│
├── viewmodels/                # State management
│   ├── DatabaseNewsViewmodel.kt
│   ├── NetworkNewsViewmodel.kt
│   └── NewsViewModel.kt
│
├── state/                     # UI state holders
│   └── SavedArticlesState.kt
│
└── Application & Activity Files
    ├── NewsApplication.kt
    └── NewsActivity.kt
```

---

##  Tech Stack

| Layer | Technology | Why It's Great |
|-------|-----------|-----------------|
| **UI** | Jetpack Compose | Modern, declarative, less XML boilerplate |
| **Architecture** | MVVM + Clean Architecture | Scalable, testable, maintainable |
| **Language** | Kotlin + Kotlin DSL | Concise, safe, expressive |
| **DI** | Dagger Hilt | Type-safe, compile-time checked |
| **Networking** | Retrofit + OkHttp | Fast, simple, battle-tested |
| **Local Storage** | Room Database | Type-safe SQL, offline-first |
| **Async** | Coroutines + Flow | Non-blocking, elegant async code |
| **State** | StateFlow | Reactive, lifecycle-aware |
| **Pagination** | Paging 3 | Efficient infinite scroll |
| **Image Loading** | Coil | Lightweight, Kotlin-first |
| **Navigation** | Jetpack Navigation | Type-safe routing |
| **Background Jobs** | WorkManager | Reliable background tasks |
| **Testing** | Unit Tests + UI Tests | Robust code coverage |

---

##  Images

<img width="460" height="772" alt="Screenshot 2026-07-05 at 8 46 31 PM" src="https://github.com/user-attachments/assets/c8c9d938-ef7a-4fe9-8f97-266a09f984fd" />
<img width="441" height="773" alt="Screenshot 2026-07-05 at 8 46 47 PM" src="https://github.com/user-attachments/assets/23d3938d-4213-4eb5-9062-f27295c2e69c" />
<img width="437" height="774" alt="Screenshot 2026-07-05 at 8 47 32 PM" src="https://github.com/user-attachments/assets/9b129325-a061-479f-a231-9e1d27f378b6" />
<img width="463" height="775" alt="Screenshot 2026-07-05 at 8 47 13 PM" src="https://github.com/user-attachments/assets/3aa4fee7-9a88-4702-820a-0161d0622896" />

**Made with ❤️ by developers, for developers.**
