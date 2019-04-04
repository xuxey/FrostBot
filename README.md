# FrostBot
FrostBot Repo

Installation
Download Frostbot [here](https://drive.google.com/file/d/13_Rdzm7uV3kti_c0UTcge7BvsSc_SkUv/view?usp=sharing)
2) Drag it to anywhere on your server PC where it can access the scripts you want
3) in the same directory, make a text document called config.properties
4) Make a bot application [here](https://discordapp.com/developers/applications/)
5) in config.properties, add the following:
```
token=<bot token>
ownerID=<your ID>
prefix=<bot's prefix>
display=<bot's presence>
```
6) now follow these steps to add a script path.
Decide a name for the path. In the example above, I have chosen "server" I will refer to this name as [name] below.
add 2 lines to your config.properties: 
 ```css
[name]name=<filename.bat>
[name]path=<directory>
```
Save the properties file
Following the above, heres an example with name "server":
```css
servername=hello.bat
serverpath=D:
```
Do NOT use quotes in the properties file.
7) use cmd or GIT Bash in that directory to run `java -jar frostbot-<version>.jar`
8) the bot should now be Online in about 5 seconds or less.
9) use help command to learn how to use the bot

`prefix+start <name>` is used to run the batch file.

You can add as many paths as you require to the config.properties as long as no 2 names coincide
