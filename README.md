# Yoga app

## About the App
This Android App that allow users to see list of Asanas.

On initial app launch the user is presented with a grid of Asanas.

When an Asana is tapped on the Asanas grid a new screen is displayed that displays the details about that particular Asana.

## The Endpoint
The endpoint is an "API returning yoga categories and poses with images" as per the [GitHub page](https://github.com/alexcumplido/yoga-api), belonging to "alexcumplido".

## UI
This app is simple to use and work on both phones and tablets however, it only makes a difference between Compat size and the rest and only for the detail screen.

## The Tech Stack
It has been written in Kotlin using the latest Jetpack compose tools (Composable, type-safe navigation, instrumentation tests, ...)

The main 3rd party libraries are:
- Coil for handling images.  It is the recommended by Google in the official documentation.
- Hilt for dependency injection as Dagger simplification developed by Google
- Moshi for JSON deserialization.  As the author said it is "Gson v3", the version they couldn't develop
- Mockk for mocking objects in tests since it is Kotlin first and easy to use

## Testing
This sample also provides a big set of tests (in accordance with the size of the app) where ViewModels, repository and mapper have been unit tested.
Some screens have been tested with unit and integrations.
Not all the screens have been tested, either the navigation and, regardless there are screens that have been tested, instrumentation tests are a tool for behavioral testing so navigation and screen feel weren't tested.  Snapshot testing with a end to end suit would have been much more beneficial but, it is in the wish list for the time being.

## Principles
The code has been organised following Clean Code, Clean Architecture principles -with KISS, SOLID, YAGNI, ... principles included in them- in a mixture of layers and features:
- There is a common domain layer with the models and the repository interface for dependency inversion. There was not any business logic to include in usecases/interactors;
- There are two feature packages, both with a presentation and a ui layer;
- There is a data layer with the repository and a mapper plus the injection model;
- There is a framework layer (network); and
- There is also a main ui package with common screens (loading and error), navigation, theme, ui tools, ...

## Strategy
This app assumes the endpoint will always return a non-nullable id and it is not handling cases where a field is missing in the UI, only when mapping for avoiding a crash.

## Things to Fix
- Coil tests throw fake success results so, they have been marked with a TODO()

## The wishlist
- Snapshot testing
