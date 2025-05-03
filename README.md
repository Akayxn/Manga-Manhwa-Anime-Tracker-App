# Random Anime App


## Overview

AnimePicker is a lightweight desktop application built with Java that enables users to discover random anime titles from the extensive MyAnimeList database through the Jikan API. The application features a user authentication system and an intuitive graphical interface designed to provide a seamless anime discovery experience.


## Features

- **User Authentication**: Secure login system utilizing the DummyJSON Users API
- **Anime Discovery**: Random anime generator with detailed information display
- **Responsive UI**: Clean, intuitive interface built with Java Swing
- **API Integration**: Seamless connection with Jikan API for anime data retrieval

## Installation

### Prerequisites
- Java JDK 11 or higher
- Internet connection for API functionality


## Usage

1. Launch the application
2. Log in using the following credentials:
   - Username: `emilys`
   - Password: `emilyspass`
3. Click the "Random Anime" button to fetch and display a new anime title
4. View details about the selected anime

> Note: Additional test credentials can be found at [DummyJSON Users API](https://dummyjson.com/users)

## Technical Architecture

AnimePicker is built using the following technologies:

- **Java**: Core programming language
- **Swing**: GUI framework for the user interface
- **HttpClient**: For making API requests
- **Jackson**: JSON parsing library for handling API responses

## Project Structure

```
src/
├── main/
│   └── java/
│       └── org/animepicker/
│           ├── ui/
│           │   ├── LoginFrame.java
│           │   └── AnimeFrame.java
│           ├── service/
│           │   ├── AuthService.java
│           │   └── AnimeService.java
│           ├── model/
│           │   ├── User.java
│           │   └── Anime.java
│           └── Main.java
└── resources/
    └── images/
        ├── logo.png
        └── background.jpg
```

## API Integration

AnimePicker integrates with two primary APIs:

1. **Jikan API** - An unofficial MyAnimeList API
   - Endpoint: `https://api.jikan.moe/v4/random/anime`
   - Used for retrieving random anime information

2. **DummyJSON** - A fake REST API for testing
   - Endpoint: `https://dummyjson.com/users`
   - Used for simulating user authentication

## Development

### Contributing

Contributions are welcome! If you'd like to contribute to this project:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request


## Acknowledgements

- [Jikan API](https://jikan.moe/) for providing anime data
- [DummyJSON](https://dummyjson.com/) for the test user authentication
- [MyAnimeList](https://myanimelist.net/) as the source of anime information

