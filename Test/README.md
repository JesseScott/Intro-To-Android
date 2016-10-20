README
================

##Testing Notes

* Unzip the _HelloAndroid-TestApp.zip_ file
* Open *Android Studio* (2.2 or higher)
* Select 'Open existing Android Studio project' and navigate to the unzipped project
* Wait until the project compiles...
  * If there are NO errors (you should be notified if there are)
    * Press the green play icon to compile and deploy the app
    * Assuming you don't have a device connected and set for USB Debugging...
      * the ADB (Android Debug Bridge) screen should prompt you to make an AVD (Android Virtual Device)
      * Follow the steps in the wizard - feel free to customize the AVD however you like, as long as it has an API > 17 (OS 4.2+)
      * the emulator should boot up (might take a while)
      * you should see a single page app with a white screen and the text "Hello World!"
  * If there ARE errors
    * Follow any obvious prompts from the IDE (install something, etc.)
    * Verify that you installed the recommended SDK build tools and API versions as per the instructions
    * Document the error and submit it to me
