# COFFEE BLISS MEMBERSHIP APP

## COMPLETE PRODUCT CONTEXT

### Product Information

Product Name:
Coffee Bliss Membership

Version:
1.0

Platform:
Android

### Technology Stack

* Kotlin
* Jetpack Compose
* Room Database
* MVVM Architecture
* Navigation Compose
* StateFlow
* Material Design 3

---

# Background

Coffee shops currently use physical membership cards for customer loyalty programs.

Problems with physical cards:

* Easily lost
* Difficult to update
* Printing costs are required
* Cannot display transaction history

Therefore, a digital membership application is needed that allows customers to:

* Own a digital membership card
* Collect points automatically
* Redeem rewards
* Track transaction history

---

# Product Goals

Build an Android application that supports:

* Member Registration
* Local Member Data Storage
* Digital Membership Card
* Point Collection
* Transaction History
* Reward Redemption

---

# Problem Statement

Customer Side:

* Customers often lose physical membership cards.
* Points become difficult to track and use.

Coffee Shop Side:

* Manual transaction recording is inefficient.
* Loyalty management is difficult.

---

# Success Metrics

## Functional Metrics

* Member registration works
* Data stored in Room Database
* Points calculated automatically
* Transaction history displayed
* Rewards can be redeemed

## Technical Metrics

* Crash rate below 2%
* Local data persistence
* Loading time under 2 seconds

---

# User Personas

## Persona 1

Name: Andi

Age: 22

Occupation: Student

Needs:

* Collect loyalty points
* View rewards
* Avoid carrying physical cards

---

## Persona 2

Name: Rina

Age: 30

Occupation: Barista

Needs:

* Add customer transactions
* Check member status

---

# Product Scope

## In Scope

* Member Registration
* Member Dashboard
* Membership Card
* Point System
* Transaction History
* Reward Redemption
* Room Database

## Out Of Scope

* Online Payment
* Cloud Database
* Google Login
* Push Notification
* Multi Device Synchronization

Do NOT implement out-of-scope features.

---

# User Flow

1. User opens application
2. User registers as member
3. Data stored in Room Database
4. User views digital membership card
5. User performs transaction
6. System calculates points
7. Points increase automatically
8. User redeems rewards

---

# Functional Requirements

## FR-01 Member Registration

Inputs:

* Name
* Email
* Phone Number

Validation:

* All fields required
* Email must be valid

Output:

* Member data saved

Acceptance Criteria:

* Registration successful
* Data stored in Room

---

## FR-02 Member List

Display all members.

Acceptance Criteria:

* Data loaded from Room Database
* UI updates automatically when database changes

Implementation Note:

Use Flow or StateFlow.

---

## FR-03 Membership Card

Display:

* Member Name
* Member ID
* Member Level
* Total Points

Acceptance Criteria:

* Data matches database

Note:

Current database does not contain membership level.
Create a simple derived level:

0-49 points = Bronze
50-99 points = Silver
100-149 points = Gold
150+ points = Platinum

This should be computed dynamically, not stored in database.

---

## FR-04 Add Transaction

Input:

* Purchase Amount

Formula:

1 Point = Rp10.000

Examples:

Rp50.000 = 5 Points
Rp100.000 = 10 Points
Rp150.000 = 15 Points

Acceptance Criteria:

* Points calculated automatically
* Transaction history stored

---

## FR-05 Transaction History

Display:

* Date
* Amount
* Earned Points

Acceptance Criteria:

* History accessible at any time

Sort by newest first.

---

## FR-06 Redeem Reward

Rewards:

* Espresso = 50 Points
* Cappuccino = 100 Points
* Latte = 150 Points

Acceptance Criteria:

* Points deducted after redemption
* Redemption blocked if points are insufficient

---

# Non Functional Requirements

## Performance

* App startup < 3 seconds
* Database query < 500ms

## Reliability

* Data remains available after app restart

## Usability

* Simple UI
* Material Design 3

## Maintainability

* MVVM Architecture
* Repository Pattern

---

# Database Design

## Members Table

id: Integer
name: Text
email: Text
phone: Text
points: Integer

---

## Transactions Table

id: Integer
memberId: Integer
amount: Double
pointEarned: Integer
date: String

Relationship:

One Member
→ Many Transactions

Implement proper Room Foreign Key relationship.

---

# Screen Requirements

## Splash Screen

Purpose:

Display Coffee Bliss logo.

Auto navigate after 2 seconds.

---

## Home Screen

Display:

* Total member count
* Member list
* Add Member button

---

## Add Member Screen

Display:

* Registration form

Fields:

* Name
* Email
* Phone Number

Button:

* Save

---

## Member Card Screen

Display:

* Member Name
* Member ID
* Member Level
* QR Code
* Current Points

Actions:

* Open Transaction Screen
* Open Reward Screen
* Open Transaction History

---

## Transaction Screen

Display:

* Amount input

Actions:

* Calculate points
* Save transaction

---

## Reward Screen

Display:

* Reward list

Actions:

* Redeem reward

---

# Navigation Structure

Splash Screen
↓
Home Screen
↓
Member Detail

Inside Member Detail:

├── Membership Card
├── Transaction History
└── Reward Screen

---

# MVVM Architecture

Presentation Layer

* Jetpack Compose
* State Management

↓

ViewModel Layer

* Business Logic
* StateFlow

↓

Repository Layer

* Data Access

↓

Room Database

* Member Table
* Transaction Table

---

# Deliverables

The final project must include:

* Kotlin Source Code
* Room Database Implementation
* Jetpack Compose UI
* MVVM Architecture
* APK File
* User Manual
* Demo Presentation

---

# Definition Of Done

Project is complete only when:

✓ Member registration works

✓ Data saved to Room Database

✓ Membership card displayed

✓ Point calculation works

✓ Transaction history displayed

✓ Reward redemption works

✓ Navigation works correctly

✓ No runtime errors during testing
