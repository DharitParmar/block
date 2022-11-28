## Build tools & versions used
minSdk 21
targetSdk 32

## Steps to run the app
1] Open Android studio build project, build apk and install apk on device/emulator
2] Open project in Android studio, then click "Run" from the toolbar.
Once Gradle finishes building, Android Studio should install the app on your connected device/emulator.

## What areas of the app did you focus on?
My main focus was on:
1] architecture(3 layered architecture: Data, Domain Presentation with clean software design)
2] packaging(is in a nutshell is a mixture of package by layer and package by feature).

## What was the reason for your focus? What problems were you trying to solve?
The reason of my focus was to scale, modularize and organize teams around Mobile Development.
Also, I have learned over the period of time that Code/package structure/organization is the very first thing encountered by a programmer when browsing source code.

Thus, the idea was to come up with a solution which is:
Easy to maintain.
Easy to test.
Very cohesive.
Decoupled.
easy to code organize/navigation
can Minimizes Scope.
can have Isolation and Encapsulation.

learned over the period of time that Code/package structure/organization is the very first thing encountered by a programmer when browsing source code.


## How long did you spend on this project?
4.5 - 5 hour

## Did you make any trade-offs for this project? What would you have done differently with more time?
Given more time I would have prefer to separate out core module and make it more more abstract to:
make it Independent of Frameworks.
make it Independent of any external agency.

secondly, Unit test coverage can be improved!
Third, given more time I would have explored UI in compose.


## What do you think is the weakest part of your project?
- I still feel packaging and modularity can be improved. 
- did not write unit test for viewmodel yet.

## Did you copy any code or dependencies? Please make sure to attribute them here!
As a learning, I have tried to adopt a functional error handling following:
https://danielwestheide.com/blog/the-neophytes-guide-to-scala-part-6-error-handling-with-try/
https://danielwestheide.com/blog/the-neophytes-guide-to-scala-part-7-the-either-type/
https://proandroiddev.com/kotlins-nothing-type-946de7d464fb

## Is there any other information you’d like us to know?
- used a mockk for the first time as I like it and wanted to learn, so you may find some inconsistency there.