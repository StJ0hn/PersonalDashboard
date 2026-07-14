# PersonalDashboard

## Description

The **PersonalDashboard** is a minimalist, high-performance ecosystem designed to manage tasks and study goals. Built on the Java 21 ecosystem and structured around a decoupled MVC architecture, the system provides both a command-line interface (CLI) and a desktop interface (GUI), operating simultaneously over the same business logic.

---

## Overview

Developed under an engineering philosophy centered on manually building components ("doing it the hard way to learn the right way"), this project addresses a common challenge faced by engineering students: the fragmentation between analog planning and digital productivity tracking.

The application serves as a lightweight digital companion for study routines. Through an integrated dashboard, users can monitor their daily consistency in real time by tracking study streaks, pending tasks, and academic goals.

---

## Features

### Consolidated Dashboard

- **Streak Calculation** — Dynamically analyzes previous study sessions to calculate consecutive productive days.
- **Real-Time Metrics** — Displays pending tasks and active goals directly on the dashboard.

### Task Management (CLI & GUI)

- Rich task model including:
  - Title
  - Category
  - Priority (`TaskPriority`)
  - Deadline
- Advanced filtering by priority, status, and category.
- Modal-based task creation through the graphical interface.

### Study Goal Tracking (CLI & GUI)

- Responsive card-based visualization using `FlowPane`.
- Interactive study session logging directly from each goal card.
- Custom programmatically rendered progress bar (`StackPane + Rectangle`) designed to ensure visual consistency across Linux GTK themes.

### Backup and Export

- CSV export for completed or pending tasks.
- Local JSON persistence with asynchronous read/write operations through a centralized repository.

---

## Technologies

The project is built using modern technologies from the Java ecosystem.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-FF7800?style=for-the-badge&logo=openjfx&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![JSON](https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=json&logoColor=white)

---

## Requirements

Before running the project, ensure the following software is installed:

- Java Development Kit (JDK) 21 or newer
- Gradle 8.x (optional, since the project includes the Gradle Wrapper)
- Git

---

## Installation

Clone the repository:

```bash
git clone https://github.com/StJ0hn/PersonalDashboard.git

cd PersonalDashboard
```

Build the project:

```bash
./gradlew build
```

Run the automated tests:

```bash
./gradlew test
```

---

## Usage

The application supports two independent interfaces that share the same JSON persistence layer.

### Graphical Interface (GUI)

Recommended for everyday use.

```bash
./gradlew run
```

If your IDE has JavaFX module loading issues, execute the application through:

```
view.gui.Launcher
```

### Command-Line Interface (CLI)

For users who prefer terminal-based interaction.

```bash
java -cp build/classes/java/main view.cli.Main
```

---

## Architecture

## Architecture

The application follows the **Model–View–Controller (MVC)** architectural pattern with a dedicated persistence layer shared by both user interfaces.

```text
                                    ┌──────────────────────────────┐
                                    │            Model             │
                                    │ (Entities & Business Rules)  │
                                    └──────────────┬───────────────┘
                                                   │
                                                   │
                                    ┌──────────────▼───────────────┐
                                    │         Controller           │
                                    │ (Application Logic & Flow)   │
                                    └───────┬───────────┬──────────┘
                                            │           │
                                            │           │
                           ┌────────────────▼───┐   ┌──▼────────────────┐
                           │     View (CLI)     │   │    View (GUI)     │
                           └────────────────────┘   └───────────────────┘
                                            │
                                            │
                           ┌────────────────▼───────────────┐
                           │         Repository             │
                           │  GoalJsonRepository            │
                           │  TaskJsonRepository            │
                           └────────────────┬───────────────┘
                                            │
                                            ▼
                                   Local JSON Files
```

### Architectural Decisions

#### Decoupled Views

The CLI and GUI are independent presentation layers that share the same controllers and business logic.

#### Programmatic GUI

The JavaFX interface was entirely implemented in Java without FXML or Scene Builder, providing greater control over rendering and eliminating platform-specific styling inconsistencies.

#### Repository Pattern

Persistence is isolated behind dedicated repositories (`TaskJsonRepository` and `GoalJsonRepository`), allowing controllers to remain independent of file system operations.

#### Transparent Persistence

Repository operations perform JSON serialization transparently, keeping the persistence mechanism separated from the application's business logic.

### Architectural Decisions

#### Decoupled Views

The CLI and GUI are independent presentation layers that share the same controllers and business logic.

#### Programmatic GUI

The JavaFX interface was entirely implemented in Java without FXML or Scene Builder, providing greater control over rendering and eliminating platform-specific styling inconsistencies.

#### Transparent Persistence

Persistence is handled asynchronously through `TaskJsonRepository` and `GoalJsonRepository`, preventing file operations from blocking the JavaFX application thread.

---

## Project Structure

```text
src/main/java/
├── controller/
│   ├── DashboardController.java
│   ├── GoalController.java
│   └── TaskController.java
│
├── exception/
│
├── model/
│   ├── Goal.java
│   ├── Task.java
│   ├── TaskPriority.java
│   └── TaskStatus.java
│
├── repository/
│   ├── GoalJsonRepository.java
│   └── TaskJsonRepository.java
│
├── util/
│   ├── CsvExporter.java
│   └── InputUtils.java
│
└── view/
    ├── cli/
    └── gui/
        ├── AddGoalDialog.java
        ├── AddSessionDialog.java
        ├── AddTaskDialog.java
        ├── DashboardPanel.java
        ├── GoalPanel.java
        └── TaskPanel.java
```

---

## Documentation

Additional documentation is available in the repository.

- **System Design Notes** -> Engineering decisions and design rationale documented throughout development.
- **Use Cases** -> Functional requirements and traceability documented in `CasosDeUso.md`.

---

## Testing

The project includes automated unit tests focused on business rules and controller behavior.

Run all tests with:

```bash
./gradlew test
```

For more detailed execution logs:

```bash
./gradlew test --info
```

---

## Contributing

Contributions are welcome.

If you identify bugs, architectural improvements, or opportunities for new features:

1. Fork the repository.
2. Create a feature branch.

```bash
git checkout -b feature/my-feature
```

3. Commit your changes.

```bash
git commit -m "feat: add my feature"
```

4. Push your branch.

```bash
git push origin feature/my-feature
```

5. Open a Pull Request.

---

## License

This project is distributed under the MIT License.

See the `LICENSE` file for more information.
