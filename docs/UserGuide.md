---
layout: page
title: User Guide
---

NUSConnect is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, NUSConnect can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/AY2425S2-CS2103T-T16-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar nusconnect.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ALIAS c/COURSE no/NOTE tele/TELEGRAM w/WEBSITE [m/MODULE]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of modules (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/JDoe c/CompSci no/Enjoys programming tele/@johndoe w/https://johndoe.com m/CS2103T`
* `add n/Betsy Crowe m/CS2106 e/betsycrowe@example.com a/BCrowe p/1234567 c/Business no/Hackathon peer tele/@bcrowe w/https://linkedin.com/betsycrowe`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ALIAS] [c/COURSE] [no/NOTE] [tele/TELEGRAM] [w/WEBSITE] [m/MODULE]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing modules, the existing modules of the person will be removed i.e adding of modules is not cumulative.
* You can remove all the person’s modules by typing `m/` without
    specifying any modules after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower m/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing modules.

### Locating persons by name or modules: `find`

Finds persons whose names or modules contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Both the name and the module are searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword in either the name or module will be returned (i.e. OR search). e.g. `Hans Bo CS2103T` will return `Hans Gruber` (matching `Hans`), `Bo Yang` (matching `Bo`), and `John Sim` (if module matches).

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
* `find doe CS2106` returns `John Doe` (matching name `Doe`) and `John Sim` (matching module `CS2106`)
  ![result for 'find doe CS2106'](images/findDoeCs2106.png)

### Deleting people : `delete`

Deletes the specified person or a list of people from the address book.

Format: `delete INDEX [MORE_INDICES]`

* Deletes the person at the specified `INDEX`, or all people at the specified `INDICES`
* The index refers to the index number shown in the displayed person list.
* The indices **must be a positive integer** 1, 2, 3, ...
* The indices must not be larger than the size of the address book.

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `list` followed by `delete 2 4` deletes the 2nd and the 4th person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Viewing contact details: `view`

Shows the details of the specified person from the address  book.

Format: `view INDEX`

* Shows the full details of the person at the specified `INDEX` on the right panel.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, ...
* The index must not be larger than the size of the address book.

Examples:
* `list` followed by `view 2` shows the full details of the 2nd person in the address book.
* `find Betsy` followed by `view 1` shows the full details of the 1st person in the results of the `find` command.

### Creating a group : `group create`

Creates a new group with the specified name.

Format: `group create NAME`

* The group name should be alphanumeric.

Examples:
* `group create CS2103T Team` Creates a new group named "CS2103T Team"
* `group create Grey Hats` Creates a new group named "Grey Hats"

### Deleting a group : `group delete`

Deletes the group at the specified index.

Format: `group delete INDEX`

* Deletes the group at the specified `INDEX`.
* The index refers to the index number shown in the displayed group list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
* `group delete 2` Deletes the 2nd group in the group list.

### Adding a person to a group : `group add`

Adds a person to a group.

Format: `group add PERSON_INDEX to GROUP_INDEX`

* Adds the person at the specified `PERSON_INDEX` to the group at the specified `GROUP_INDEX`.
* Both indices refer to the index numbers shown in the displayed person list and group list respectively.
* Both indices **must be positive integers** 1, 2, 3, ...

Examples:
* `group add 1 to 2` Adds the 1st person in the person list to the 2nd group in the group list.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [m/MODULE]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 m/CS2103T`
**Clear** | `clear`
**Delete** | `delete INDEX [MORE_INDICES]`<br> e.g., `delete 3` `delete 1 3 5`
**View** | `view INDEX` <br> e.g., `view 1`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [m/MODULE]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Group create** | `group create NAME`<br> e.g., `group create CS2103T Team`
**Group delete** | `group delete INDEX`<br> e.g., `group delete 2`
**Group add** | `group add PERSON_INDEX to GROUP_INDEX`<br> e.g. `group add 1 to 2`
**List** | `list`
**Help** | `help`
