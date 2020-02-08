# **ReactJava Plugin for IntelliJ IDEA**
Plugin that implements ReactJava language support in IntelliJ IDEA.

## **Setting up the project**
### **Prerequisites**
In order to develop for the ReactJava plugin you need:

1. IntelliJ IDEA Ultimate 2019.1 or higher (recommended: 2019.2)
2. JDK 8 (recommended: [JetBrains JDK])

### **Recommended**
1. [PsiViewer] plugin
2. IntelliJ IDEA Community Sources (see Setup)

### **Setup**
#### **Creating the IntelliJ Platform Plugin Project**
1.  Launch IntelliJ Ultimate Edition.
2.  In the startup dialog, 'Create New Project'.
3.  In the 'New Project' dialog,  choose 'IntelliJ Platform Plugin'.
4.  In the 'Project SDK' field, choose an existing Plugin Project SDK and go to step 8.
5.  If no Plugin Project SDK is in the list, create one by pressing the 'New...' button.
6.  'Open' the IntelliJ Ultimate Edition application folder ('Contents' on Mac).
7.  In the subsequent 'Select Internal Java Platform' dialog, select '1.8'.
8.  On return to the 'New Project' dialog, '[No library selected]' is okay, press Next.
9.  Assign the project name 'ReactJavaIntelliJPlugin' and press 'Finish'.
10. The project will open and configure itself.
11. Download the repository and copy the files into the new project folder.

#### **Configuring a New Plugin Project SDK**
If the selected Plugin Project SDK above was not a new one, project creation is complete. On the other hand, if the selected Plugin Project SDK above was a new one, it needs to be configured in the following steps:

1. Ensure the JetBrians Grammar-Kit plugin is installed and enabled.
2. Ensure the CSS plugin is enabled.
3. Download the IntelliJ IDEA Community Sources zip file and decompress (https://github.com/JetBrains/intellij-community).
4. Move the decompressed folder to the IntelliJ Ultimate application folder (under 'Contents' on Mac).
5. File -> Project Structure -> Platform Settings -> SDKs -> IntelliJUltimatePluginSDK -> Sourcepath.
6. Add ('+') the decompressed folder
7. Press 'OK'.
8. The project will configure itself and then all is ready.

### **Targeting IU-2019.1**
To target IntelliJ IDEA Ultimate 2019.1 and later, two changes must be made to the `plugin.xml` configuration file.

1. In the `extensions` tag, comment out `<fileType name="ReactJava Language" implementationClass="com.giavaneers.reactjava.file.ReactJavaFileType" fieldName="INSTANCE"
                                                       language="ReactJava" order="first" />`.
2. Directly underneath this, uncomment ` <fileTypeFactory order="first" implementation="com.giavaneers.reactjava.file.ReactJavaFileTypeFactory" />`.

## **Deploying the plugin**
1. Make sure the correct IntelliJ Platform Plugin SDK is selected for the project and module,
depending on the starting version of IntelliJ IDEA Ultimate you wish to target.
2. Navigate to `resources/META-INF/plugin.xml` and right click. Select `Prepare Plugin Module '[MODULE_NAME]' For Deployment`.

This will generate a .jar file (or a .zip file if your plugin has dependencies) that can be distributed 
to end users directly for them to install, or published on the [JetBrain Plugin Repository].

[JetBrains JDK]: https://bintray.com/jetbrains/intellij-jdk
[PsiViewer]: https://plugins.jetbrains.com/plugin/227-psiviewer
[here]: https://github.com/JetBrains/intellij-community
[JetBrain Plugin Repository]: https://plugins.jetbrains.com/