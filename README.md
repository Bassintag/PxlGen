# PxlGen
Procedural sprites generation engine

Heavily inspired by [this tool](https://chilly-durango.itch.io/level-generator-toolkit). 
But it can be used as a library in an actual project at runtime.

The GUI is just a small project on top to demonstrate what this tool is capable of.

![Screenshot](https://i.imgur.com/U7ngKws.png)

This tool is highly customizable as all the functions used by it are imported from plugins.
You can create your own plugin to add more capabilities to the editor (The GUI loads them from the ./plugin folder by default).

See the [common plugin](https://github.com/Bassintag/PxlGen/tree/master/CommonPlugin/src/pxlgen/plugin/common) for many examples of functions.

Functions are loaded using reflection which allows for fast creation and clean plugin structure.

Make sure to tag your function class with @FunctionHandler (each of them will be instantiated 1 time when loaded) and your functions with @Function (They must be inside a @FunctionHandler)

## Note

As this tool is currently in developpement, the GUI isn't final and I use it mainly for testing. 
For now it will load the "island" script by default, you can either change it in code or just change the island script if you want to actually use the GUI.
I'll improve it later but I'm trying to add more functions first.
