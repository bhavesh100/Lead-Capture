# Config-Driven Lead Capture Form

This project implements a config-driven lead capture form using Kotlin and Jetpack Compose, built entirely from scratch with foundation/ui primitives.

## Build Instructions
1. Open the project in Android Studio (Ladybug or later recommended).
2. Ensure you have SDK 36 installed (or change `compileSdk` in `build.gradle.kts` to your installed version).
3. Run the following command to build the debug APK:
   ```bash
   ./gradlew :app:assembleDebug
   ```
4. Run the unit tests to verify form validation logic:
   ```bash
   ./gradlew :app:testDebugUnitTest
   ```

## Package Layout
Strict layering constraints were enforced during development.
* `designsystem/tokens/`: Contains UI tokens (`AppColors`, `AppSpacing`, `AppTypography`, `Breakpoints`) and `AppTheme`. No hard-coded `dp`, `sp`, or `Color` literals exist outside this package.
* `designsystem/atoms/`: Contains highly reusable UI primitives (`AppButton`, `AppTextField`, `AppSelectField`, `AppCheckboxField`, `AppFieldError`). These components have zero knowledge of the lead form models or validation logic.
* `designsystem/form/model/`: Contains pure Kotlin data models for defining a config-driven form (`FieldConfig`, `FieldType`, `Rule`, `FieldValue`, etc.).
* `designsystem/form/molecules/`: Contains layout and field rendering components (`FormLayout`, `FieldRenderer`). This is the only place where branching on `FieldType` occurs.
* `lead/config/`: Contains the actual configuration definition for the Lead feature (`LeadFormConfig`).
* `lead/validation/`: Contains the pure Kotlin validation logic (`FormValidator`). No Android or Compose imports are present here.
* `lead/state/`: Contains the `LeadViewModel` which manages form values, errors, and validation state agnostically via `StateFlow`.
* `lead/ui/`: Contains the screen-level compose UI (`LeadScreen`) that ties the ViewModel and Molecules together.

## How to Add a Field
Adding a new field is designed to be trivial. You **do not** need to write a new composable.
1. Open `lead/config/LeadFormConfig.kt`.
2. Add a new `FieldConfig` entry to the `defaultConfig` list.
3. Example:
   ```kotlin
   FieldConfig(
       name = "jobTitle",
       label = "Job Title",
       type = FieldType.TEXT,
       span = FieldSpan.FULL,
       rules = listOf(Rule.Required("Job Title is required"))
   )
   ```
4. The field will automatically render, adapt to wide screens if `span == FieldSpan.HALF` (and adjacent to another HALF), validate properly, and update the global state.

## Design Decisions
1. **Material 3 / Third-Party Libraries**: Explicitly avoided as per the brief constraints. All UI components (Atoms) were built using Jetpack Compose `foundation` and `ui` primitives (`Box`, `Row`, `Column`, `BasicText`, `BasicTextField`, `Popup`).
2. **Responsive Layout**: Instead of hard-coding screen orientations or checking `Build.MODEL`, responsive design is handled via `BoxWithConstraints` inside `FormLayout`. The layout gracefully shifts 2-column configurations into a single column on compact screens strictly based on `TWO_COLUMN_MIN_WIDTH` (600.dp).
3. **Touched-On-Blur Validation**: To provide the best UX, fields trigger a `onBlur` callback precisely when their focus transitions from `true` to `false`. Validation is run across the form, but only the error for the blurred field is revealed before clicking submit.
4. **Insets & Padding**: `safeDrawingPadding()` is applied strictly at the root `Modifier` inside `MainActivity.kt`. Stacking `imePadding` or system bars padding elsewhere was completely avoided.
5. **Viewmodel Lifecycle**: Used standard `androidx.compose.runtime.collectAsState()` and `androidx.activity.viewModels()` to adhere to the strict list of dependencies allowed in the brief without pulling in extra viewmodel-compose artifacts.
