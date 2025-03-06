## Minimal reproduction of a bug with talkback when navigating between 2 screens

### Steps to reproduce
- Running the app and activating Talkback on Screen A
- Select the red Square that is part of a LazyColumn and select it
- Expectation would be focus on the navigation icon on screen B, but the Button B gets focus

Further Information:
- Removing the fillMaxSize on the LazyColumn and restarting the previous steps solves the issue temporarily
- When fillMaxSize is not set, increasing the item amount will at some point(seems like 50% screen height?) break the behavior again
- Migrating to a scrollable column can solve the issue for some cases
- Removing one of the Buttons also solves the issue

This issue is reproducible in at least these compose versions: 1.7.8 - 1.8.0-beta03