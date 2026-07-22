<div align="center">

# Run Appium Tests with Java on pCloudy

[![pCloudy](https://img.shields.io/badge/Made%20by-pCloudy-1a73e8?style=for-the-badge)](https://www.pcloudy.com)
[![Appium Java Client version](https://img.shields.io/maven-central/v/io.appium/java-client.svg?style=for-the-badge&label=Appium%20Java%20Client)](https://mvnrepository.com/artifact/io.appium/java-client)

</div>


## Getting Started

[pCloudy](https://www.pcloudy.com) is a mobile app testing platform that gives you single-click access to real Android and iOS devices directly from your browser. Use these real devices to perform manual and automated testing for your app, and integrate the platform with your CI pipeline for continuous testing across multiple real devices with every build.

pCloudy is fully integrated with Appium, letting you run your existing Appium test scripts against real devices on the pCloudy cloud with minimal changes.

This project is a plain Java + Maven Appium sample (no test framework) showing how to run Java Appium tests on the pCloudy Real Device Cloud.

## Prerequisites

- Java 8 or higher
- Maven
- A [pCloudy account](https://www.pcloudy.com) with your registered email ID and API key (find your API key under **Settings > API Key** on the pCloudy dashboard)
- Your app (`.apk`/`.ipa`) uploaded to your pCloudy Cloud Drive

## Getting Started

Clone the project and install dependencies:

```bash
git clone https://github.com/Pcloudy-Sample/PC-appium-java
cd PC-appium-java
mvn clean install
```

### Configure your credentials and app

`AndroidRunner.java` and `IOSRunner.java` read your pCloudy credentials from environment variables (falling back to placeholder text if unset):

**macOS / Linux:**

```bash
export PCLOUDY_USERNAME="YOUR_EMAIL_ID"
export PCLOUDY_APIKEY="YOUR_API_KEY"
```

**Windows:**

```bat
set PCLOUDY_USERNAME=YOUR_EMAIL_ID
set PCLOUDY_APIKEY=YOUR_API_KEY
```

## Running the Sample

Each platform has its own runner class (`AndroidRunner` / `IOSRunner`) and its own Maven profile, which binds `exec-maven-plugin` to run that class's `main` method:

### Run tests

```bash
mvn test -P android
```

For iOS:

```bash
mvn test -P ios
```


Alternatively, open [`AndroidRunner.java`](src/com/pcloudy/appium/AndroidRunner.java) / [`IOSRunner.java`](src/com/pcloudy/appium/IOSRunner.java) and hardcode the values directly.

The `pCloudy_ApplicationName` capability must match the name of the app already uploaded to your pCloudy Cloud Drive. This repo bundles a sample Android app, `pCloudyAppiumDemo.apk`, for reference — `IOSRunner.java` expects you to upload your own `.ipa` and update `pCloudy_ApplicationName`/`bundleId` accordingly, since no iOS sample app ships with this repo.

### Capabilities used in this sample

| Capability | Description |
|---|---|
| `pCloudy_Username` | Your pCloudy account email ID |
| `pCloudy_ApiKey` | Your pCloudy API key |
| `pCloudy_ApplicationName` | Name of the app already uploaded to your pCloudy Cloud Drive |
| `pCloudy_DurationInMinutes` | Booking duration for the device session |
| `pCloudy_DeviceManafacturer` | (Android) Device manufacturer to run the test on, e.g. `Samsung` |
| `pCloudy_DeviceVersion` | (Android) OS version of the target device |
| `pCloudy_DeviceFullName` | Book a specific device by its full name, e.g. `SAMSUNG_GalaxyA10s_Android_11.0.0_99cde` |
| `automationName` | Appium automation engine — `uiautomator2` (Android) or `XCUITest` (iOS) |
| `appPackage` / `appActivity` | (Android) Package and launch activity of the app under test |
| `bundleId` | (iOS) Bundle identifier of the app under test |

> **Note:** `pCloudy_DeviceFullName` alone is enough to book a specific device. When it's set, `pCloudy_DeviceManafacturer` and `pCloudy_DeviceVersion` aren't required — you can comment them out, as done in `AndroidRunner.java`. Use `pCloudy_DeviceManafacturer`/`pCloudy_DeviceVersion` instead only if you want pCloudy to pick any available device matching that manufacturer/version rather than one specific device.

The driver connects to the pCloudy Appium hub at:

```
https://device.pcloudy.com/appiumcloud/wd/hub
```

Test results are visible on your [pCloudy dashboard](https://www.pcloudy.com) once the session starts.

### Screenshots

Each step in [`AndroidRunner.java`](src/com/pcloudy/appium/AndroidRunner.java) (and `IOSRunner.java` once you add your steps) calls `captureScreenShots()`, which saves a timestamped screenshot into a local `screenshot/` folder after every action.

## Project Structure

```
PC-appium-java/
├── pom.xml                          # Maven build, Appium Java Client dependency, android/ios profiles
├── pCloudyAppiumDemo.apk             # Sample Android app used by the demo test
└── src/com/pcloudy/appium/
    ├── AndroidRunner.java           # Android sample: capabilities, test flow, screenshot capture
    └── IOSRunner.java               # iOS runner scaffold: capabilities set up, add your own test steps
```

## API Documentation

For the full pCloudy REST API reference (device booking, app upload, reports, and more), see:

- [pCloudy API Documentation](https://content.pcloudy.com/apidocs/index.html)

## Learn More

- [pCloudy Website](https://www.pcloudy.com)
- [Appium Integration on pCloudy](https://www.pcloudy.com/appium-integration-architecture-redefined-appium-runs-become-simpler-with-pcloudy/)

## Contributions

Contributions are welcome. Please open an issue to discuss your idea before submitting a pull request.
