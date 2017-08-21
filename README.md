# Couch Potato Server

Server application for the [Couch Potato](https://play.google.com/store/apps/details?id=codes.soloware.couchpotato.free)
Android app. Runs as a daemon, and converts client commands into mouse and keyboard events on the local system.

## Preparing the Development Environment

System Requirements:
* [ANT](https://ant.apache.org) 1.9.3 or higher
* [Eclipse Platform](https://www.eclipse.org/downloads/eclipse-packages) 4.3 (Kepler) or higher
* [Eclipse EGit](https://www.eclipse.org/egit) 1.3 or higher (usually bundled with Eclipse Platform)
* [Eclipse Java Development Tools](https://www.eclipse.org/jdt) 3.8 or higher (usually bundled with Eclipse Platform)
* [Git](https://git-scm.com) 1.8.3.2 or higher (must be available on the command-line, which is not the default on Windows)
* [Java Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 1.7 or higher
* [WiX Toolset](http://wixtoolset.org) 3.9 or higher

Recommended for Development Builds:
* [Java Development Kit 1.6](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase6-419409.html) (any sub-version)
* [Java Development Kit 1.7](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html) (any sub-version)

To configure the ANT build:
1. Open a command-line shell, with the root of the git repository set as its working directory.
2. Enter the following commands, where `$YOURNAME` is your own full name and `$YOURMAIL` is your own e-mail address:

```
git config user.name "$YOURNAME"
git config user.email $YOURMAIL
```

To configure the IDE, first configure the ANT build and then:
1. Start Eclipse with the root directory of the git repository as its workspace.
2. Click `Workbench`.
3. Open the `File` > `Import...` wizard.
4. Navigate to `Git` > `Projects from Git` and click `Next >`.
5. Select `Existing local repository` and click `Next >`.
6. Click `Add...`.
7. Enter the root directory of the git repository (or equivalently, the current Eclipse workspace directory) in the `Directory:` field and click `Finish`.
8. Select the git repository you just added from the list and press `Next >`.
9. Select `Import existing projects` and press `Next >`.
10. Select all projects that appear, and press `Finish`.
11. Open the `Window` > `Preferences` screen.
12. Navigate to `Java` > `Installed JREs` and click `Add...`
13. Select `Standard VM` and press `Next >`.
14. Enter the Java Development Kit installation directory in the `JRE home:` field and click `Finish`.
15. Repeat steps 12 through 14 for all Java Development Kit installations that you wish to use during compilation.
16. Click `OK`.
17. Open the `Window` > `Preferences` screen.
18. Navigate to `Ant` > `Runtime` and click `Ant Home...`
19. Select your system's ANT installation directory, and press `OK`.
20. Click `OK`.
