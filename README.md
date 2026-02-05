# Jackaroo Game Project

## 📋 Project Overview

**Jackaroo: A New Game Spin** is a Java-based strategic board game developed as part of the Computer Programming Lab course at the German University in Cairo, Spring 2025.

---

## 🎮 Game Description

### Introduction
Jackaroo is a beloved strategic board/card game with deep roots in Middle Eastern culture, particularly in the Gulf region. Our project reimagines this classic as a single-player experience where one human player competes against 3 CPU opponents.

### 🎯 Game Objective
The goal is to move all four of your colored marbles from your Home Zone to your Safe Zone before your opponents do. Marbles must first be fielded onto the board and then navigate the track clockwise to reach safety.

### 🃏 Game Setup
- **Players**: 1 human player vs 3 CPU players
- **Marbles**: 4 unique colored marbles per player
- **Deck**: 102 custom cards with 15 different types
- **Board**: 100-cell track with special zones for each player
- **Cards per Round**: 4 cards dealt to each player at round start

### 🏁 Board Elements

#### Special Zones & Cells
1. **Home Zone**
   - Starting position for all marbles
   - Marbles are inactive here
   - Can only leave using Ace or King cards

2. **Safe Zone**
   - Final destination for marbles
   - Immune to opponent interference
   - Entry requires exact move count

3. **Base Cell**
   - Initial track position
   - Blocks other marbles when occupied
   - 25 cells apart for each player

4. **Safe Zone Entry**
   - Single cell before Safe Zone
   - Blocks Safe Zone entry when occupied

5. **Track**
   - 100-cell circular track
   - Marbles move clockwise
   - Includes Normal, Base, and Entry cells

6. **Trap Cells**
   - 8 randomly placed cells
   - Destroy marbles that land on them
   - Relocate after each activation

7. **Fire Pit**
   - Discard pile for played cards
   - Used to refill empty deck

### ♠️ Card System
The game uses a custom deck of 102 cards with the following structure:

#### Standard Cards (Rank-based)
- **Ace (1)**: Field marble OR standard movement
- **2-6, 8-9**: Move marble forward (rank = steps)
- **7**: Split move between 2 marbles (total 7 steps)
- **10**: Discard from next player's hand OR standard
- **Jack (11)**: Swap marbles OR standard  
- **Queen (12)**: Discard from random player OR standard
- **King (13)**: Field marble OR move 13 steps (destroys all in path)

#### Wild Cards
- **Burner (14)**: Destroy opponent's marble on track
- **Saver (15)**: Save own marble to Safe Zone

### ⚙️ Game Rules

#### Marble Movement
- Move based on card rank number
- Must have exact move count to target position
- Cannot pass your own marbles
- Path blocked by more than one marble
- King card bypasses blocking rules and destroys all in path

#### Collision Rules
- Landing on opponent's marble destroys it (returns to Home Zone)
- King card destroys ALL marbles in its path
- Trap cells destroy any marble that lands on them

#### Fielding Marbles
- Requires Ace or King card
- Home Zone must have available marbles
- Base cell must be unoccupied by your own marble

#### Special Actions
- **Swapping**: Jack card swaps your marble with opponent's (not in Base cell)
- **Burning**: Burner wild card destroys opponent's marble (not in Base/Safe Zone)
- **Saving**: Saver wild card moves your marble to random empty Safe cell
- **Discarding**: 10/Queen cards remove opponent's card and skip their turn

### 🔄 Game Flow
1. **Round Structure**: Each player gets 4 turns (one per card)
2. **Turn Order**: Human player → CPU 1 → CPU 2 → CPU 3
3. **Card Play**: Select card → Select marble(s) → Execute action
4. **Round End**: All cards played → New hand dealt from deck/fire pit
5. **Game End**: First player with all marbles in Safe Zone wins

### 🎲 Game Dynamics
- **Strategic Planning**: Consider card options, marble positions, opponent moves
- **Risk Management**: Balance offensive moves with marble safety
- **Adaptive Play**: Adjust strategy based on card draws and board state
- **CPU Opponents**: Make random but rule-compliant decisions

### 🏆 Winning Condition
The first player to successfully move all four of their marbles into their own Safe Zone wins the game. If no player achieves this, rounds continue until a winner emerges.

---

## 💻 Technical Implementation

### Key Features
- Object-oriented design with clear separation of concerns
- Custom exception handling for game rule violations
- JavaFX-based graphical interface with animations
- CSV-based card configuration system
- CPU AI with random but valid decision making

### Requirements
- Java 8 or higher
- JavaFX SDK

---

## 📚 Learning Outcomes

This project demonstrates:
- Object-oriented programming principles
- Game development patterns and architecture
- JavaFX GUI development with animations
- Exception handling and validation
- File I/O operations with CSV data
- Multi-threading for game animations
- Algorithm design for game mechanics

---

## 👥 Team Members
- **Rana Walid** - [LinkedIn](https://www.linkedin.com/in/rana-walid-1b082331a)
- **Jana Mohammed** - [LinkedIn](https://www.linkedin.com/in/jana-mohamed-853907260)  
- **Salma Elghoroury** - [LinkedIn](https://www.linkedin.com/in/salma-elghroury-4a50b8363)
- **Rodaina Soliman** - [LinkedIn](https://www.linkedin.com/in/rodaina-soliman-290288333)

---

## 📝 Notes

The frontend JavaFX GUI was implemented by team member Rodaina Soliman while the backend was implemented across two milestones by all the team members under academic supervision.

## 📜 License & Usage

This project was created for educational and academic purposes.  
Visuals are used under fair use and free licenses.

© 2025 Jackaroo Team 12 – All rights reserved.

*This project was developed as part of the Computer Programming Lab course requirements at the German University in Cairo, Spring 2025.*