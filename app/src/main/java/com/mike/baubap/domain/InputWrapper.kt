package com.mike.baubap.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputWrapper(
    val value: String? = null
) : Parcelable

internal fun InputWrapper.isInitialValue() = value == null