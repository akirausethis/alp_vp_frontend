package com.example.alp_front.uiStates

data class PanitiaFormUIState (
    val statusDropdownExpandedValue: Boolean = false,
    val priorityDropdownExpandedValue: Boolean = false,
    val statusDropdownItems: List<String> = listOf("To Do", "On Going", "Finished"),
    val priorityDropdownItems: List<String> = listOf("High", "Medium", "Low"),
    val showDatePickerDialog: Boolean = false,
    val saveButtonEnabled: Boolean = false
)