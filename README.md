## Kingdom Fantasy Game (Java OOP Project)

This project is a text-based fantasy game built with object-oriented programming concepts in Java. It simulates a small RPG-style kingdom world where players can manage heroes, battle enemies, and explore different areas while interacting with dynamic game elements.

### Overview

The game focuses on simulating a fantasy world through structured object-oriented design. Each component represents a key part of the game, such as heroes, enemies, weapons, and the world itself. Players can perform actions like attacking, leveling up, and managing resources while progressing through the kingdom.

### Core Components

* **`Character`** – The base class for all entities (heroes and enemies), defining common attributes such as name, health, attack power, and defense.
* **`Hero`** – Represents the player’s character. Includes methods for leveling up, managing inventory, and performing special attacks.
* **`Enemy`** – Represents hostile characters with their own stats and AI-driven behaviors during battles.
* **`Weapon` / `Armor`** – Define equipment that boosts a character’s stats and plays a role in combat outcomes.
* **`Kingdom`** – Acts as the central environment, managing characters, battles, and progression through different regions.
* **`GameEngine`** – Controls the main gameplay loop, interactions, and event logic.

### Features

* Turn-based battle mechanics between heroes and enemies
* Experience and leveling system with stat growth
* Equipment system (weapons and armor) affecting combat performance
* Object-oriented structure demonstrating inheritance, encapsulation, and polymorphism
* Expandable architecture for adding new classes, abilities, or story elements

### Highlights

This project showcases how OOP principles can be used to design a modular and extensible game system. Each class serves a specific role, promoting reusability and clean separation of logic within the game world.
