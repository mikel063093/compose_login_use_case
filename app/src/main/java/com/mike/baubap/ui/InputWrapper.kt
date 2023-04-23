package com.mike.baubap.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputWrapper(
    val value: String = ""
) : Parcelable