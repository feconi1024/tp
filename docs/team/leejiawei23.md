---
layout: page
title: Jia Wei's Project Portfolio Page
---

### Project: NUSConnect

NUSConnect - It helps NUS students efficiently organise and manage the contact details of peers they meet during
tutorials and group projects. It is designed for users who prefer a CLI. It allows for custom labeling, reminders, easy
updates, and is optimised for building and maintaining academic connections throughout their university journey.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java.

Given below are my contributions to the project.

* **New Feature**:
  * Created a mockup Ui of NUSConnect using figma
  * Added a new `view` command to show all details of a contact 
  * Implemented group functionality
    * Added the `group create` command to create new groups
    * Added the `group delete` command to delete existing groups
    * Added the `group add` command to add contacts to groups

* **Code contributed**:
  * Change all instances of Tag to Module in all classes
  * Modified the Ui to match the mock Ui
  * Added PersonDetail and Group Ui classes
  * Implemented GroupCommand, GroupCreateCommand, GroupDeleteCommand, and GroupAddCommand classes
  * Implemented GroupPanel and GroupCard components for displaying groups in the UI

* **Testing**:
  * Modified tests to change from Tag to Module
  * Wrote and updated Junit tests to verify that the `view` command works
  * Added tests for group-related commands and functionality  

* **Documentation**:
    * User Guide:
      * Updated the user guide to include the new `view` command
      * Added documentation for all group-related commands (`group create`, `group delete`, `group add`)
