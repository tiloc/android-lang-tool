android-lang-tool
=================

Clone of [DeltaAskii/android-lang-tool](https://github.com/deltaaskii/android-lang-tool) which is a 
Clone of [TomasKypta/android-lang-tool](https://github.com/TomasKypta/android-lang-tool)

* [DeltaAskii] Solved some issues, like NullPointerException when you have multiple language xml files (like ar) and added the auto-creation of output files if they don't exists.
* [tiloc] Solved an issue with missing keys being improperly reported. Made to compile on modern environment. Added some tests.

## Original Description:

Tool for exporting and importing Android string resources for translation.

Supported resources:
* strings
* string arrays
* plurals

It's [AndroidLangTool](https://github.com/hamsterksu/AndroidLangTool) on steroids.

The tool exports Android string resources to Excel and imports them back to the project after translation.
It scans Android project and exports strings, by default from strings.xml. Additional resources can be specified.
All the resources are concatenated in a single Excel file.
The tool allows many additional operations on the strings. See the command line arguments for more details.

Xml comments are supported 
Missing traslations have red background in the xls file.

To build the application execute: `mvn package`
To run the application execute: `java -jar langtools-VERSION-jar-with-dependencies.jar`

Tool has 2 modes:
* exporting to xls
* importing from xls
 
## Exporting
```
params: -e <project dir> 
    [-o <output file>] 
    [--additional-resources <list of additional resources>]
    [--ignore-list <ingored list file>] 
```

* **project dir** - Path to the Android project 
* **output file** - Name of the generated Excel file
* **list of additional resources** - Optional list of additional resources, values are separated by ':'
* **ingored list file** - Optional file for defining keys that are ignored

## Importing

```
params: -i <input file> 
    [-s <splitting config file>] 
    [-m <mapping file>] 
    [--escaping-config <escaping config file>] 
    [--unescape-first] 
    [--ignore-list <ingored list file>] 
    [--extra-transformations <transformations config file>]
    [--mixed-content <mixed list file>]
```

* **input file** - Name of the Excel file for importing into the project
* **splitting config file** - Optinal Excel file containing splitting info
* **mapping file** - Optional file for changing resource qualifiers onto another. Typically used for omitting country 
specifiers (e.g. convert 'cs-rCZ' into 'cs'). 
* **escaping config file** - Optional file for defining string keys that should be escaped (with quotes) in the final 
output.
* **unescape-first** - Optional flag to denote that we want to unescape the strings before importing (and before 
optional escaping).
* **ingored list file** - Optional file for defining string keys that are ignored.
* **transformations config file** - Optional file for defining import tranformations on strings for each key.
* **mixed list file** - Optional file containing keys of string which will be handled as mixed xml content when importing (strings are by default handled as text content).

#### Format of splitting configuration file

* The first column contains row index of the beginning of a subfile.
* The second column contains name of the output subfile. 
* The third optional column contains name of the output resource file. The default name is "strings.xml".

#### Format of mapping file

* The first column contains 'from value'
* The second column contains 'to value'

#### Format of escaping config file

* The first column contains string keys values of which will be escaped with quotes

#### Format of ingored list file

* The first column contains string keys

#### Format of transformations config file

* The first column contains string keys
* The second column contains matching regex (can contain capture groups) as defined by Java Pattern documentation
* The third column contains replacement (can contain capture groups)
* The optional fourth column can constain comma separated list of allowed languages (the transformation is applied 
only for these languages).

#### Format of mixed list file

* The first column contains string keys
