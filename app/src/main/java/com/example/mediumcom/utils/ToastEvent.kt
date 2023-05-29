package com.example.mediumcom.utils

sealed class ToastEvent {
    data class Success(val message: String) : ToastEvent()
    data class Error(val message: String) : ToastEvent()

}