# Pokedex

This is a Pokémon List app built with Jetpack Compose that allows users to browse a list of Pokémon and mark their favorite ones. The app retrieves Pokémon data from an API and manages user favorites through a local database, providing a seamless experience for users to track their favorite Pokémon.

#### Screenshots

<p float="left"> 
	<img src="/screenshots/Screenshot_1725822053.png" alt="Login" width="250" /> 
	<img src="/screenshots/Screenshot_1725822111.png" alt="SignUp" width="250" /> 
  <img src="/screenshots/Screenshot_1725822161.png" alt="SignUp" width="250" /> 
</p>

#### Preview

<p float="left"> 
	<img src="/screenshots/demo1.gif"  width="250"/>
	<img src="/screenshots/demo2.gif"  width="250"/>
	<img src="/screenshots/demo3.gif"  width="250"/>
</p>



### Features
- Pokémon List: Displays a list of Pokémon fetched from an external API, with detailed information about each Pokémon.
- Favorites Management: Allows users to mark Pokémon as favorites. The favorite status is stored locally and persisted between sessions.
- Live Update of Favorite Status: The list dynamically updates to reflect changes in the favorite status of Pokémon.
- Jetpack Compose UI: The app leverages Jetpack Compose to create a modern, declarative UI with smooth interactions.
- State Management: Uses StateFlow and LiveData for efficient state management and UI updates.
- Clean Architecture: Follows Clean Architecture principles, separating concerns into different layers such as data, domain, and presentation.

### Technologies Used
- Jetpack Compose: Modern Android UI toolkit for building responsive and interactive UIs.
- Kotlin: The primary programming language for the project.
- Coroutines & Flow: For asynchronous operations and managing data streams efficiently.
- StateFlow: For state management, ensuring the UI responds to data changes in a lifecycle-aware manner.
- Dependency Injection (Hilt): To manage dependencies and promote testable, maintainable code.
- Repository Pattern: A layer that abstracts data access from local or remote sources.
- Retrofit: For network operations and API calls to fetch Pokémon data.
- Room: For local data storage, persisting user favorites across app sessions.


### Project Structure

The project follows the Clean Architecture pattern:

- Data Layer: Manages data sources like API and local database (Room).
- Domain Layer: Contains business logic and use cases.
- Presentation Layer: Handles UI using Jetpack Compose and state management with StateFlow.

