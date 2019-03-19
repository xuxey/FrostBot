# FrostBot
FrostBot Repo

Installation
1) Build your jar after adding bot token in Ref. Include dependencies while doing so.  
2) Drag it to anywhere on your server PC where it can access the scripts you want
3) in the same directory, make a text document called config.properties
4) now, open up the config.properies and follow these steps to add a script path.
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
5) double click the jar file
6) the bot should now be Online in about 5 seconds or less.
7) use 

```>>start [name]```

to run the batch file.

You can add as many paths as you require to the config.properties as long as no 2 names coincide
